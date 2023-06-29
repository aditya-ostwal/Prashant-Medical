package androidx.core.location;

import android.content.Context;
import android.location.GnssStatus;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationRequest;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.provider.Settings;
import android.text.TextUtils;
import androidx.collection.SimpleArrayMap;
import androidx.core.location.GnssStatusCompat;
import androidx.core.location.LocationManagerCompat;
import androidx.core.os.CancellationSignal;
import androidx.core.os.ExecutorCompat;
import androidx.core.util.Consumer;
import androidx.core.util.ObjectsCompat;
import androidx.core.util.Preconditions;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.WeakHashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* loaded from: classes.dex */
public final class LocationManagerCompat {
    private static final long GET_CURRENT_LOCATION_TIMEOUT_MS = 30000;
    private static final long MAX_CURRENT_LOCATION_AGE_MS = 10000;
    private static final long PRE_N_LOOPER_TIMEOUT_S = 5;
    private static Field sContextField;
    static final WeakHashMap<LocationListenerKey, WeakReference<LocationListenerTransport>> sLocationListeners = new WeakHashMap<>();

    public static boolean isLocationEnabled(LocationManager locationManager) {
        if (Build.VERSION.SDK_INT >= 28) {
            return Api28Impl.isLocationEnabled(locationManager);
        }
        if (Build.VERSION.SDK_INT <= 19) {
            try {
                if (sContextField == null) {
                    Field declaredField = LocationManager.class.getDeclaredField("mContext");
                    sContextField = declaredField;
                    declaredField.setAccessible(true);
                }
                Context context = (Context) sContextField.get(locationManager);
                if (context != null) {
                    if (Build.VERSION.SDK_INT == 19) {
                        return Settings.Secure.getInt(context.getContentResolver(), "location_mode", 0) != 0;
                    }
                    return !TextUtils.isEmpty(Settings.Secure.getString(context.getContentResolver(), "location_providers_allowed"));
                }
            } catch (ClassCastException e) {
            } catch (IllegalAccessException e2) {
            } catch (NoSuchFieldException e3) {
            } catch (SecurityException e4) {
            }
        }
        return locationManager.isProviderEnabled("network") || locationManager.isProviderEnabled("gps");
    }

    public static boolean hasProvider(LocationManager locationManager, String provider) {
        if (Build.VERSION.SDK_INT >= 31) {
            return Api31Impl.hasProvider(locationManager, provider);
        }
        if (locationManager.getAllProviders().contains(provider)) {
            return true;
        }
        try {
            return locationManager.getProvider(provider) != null;
        } catch (SecurityException e) {
            return false;
        }
    }

    public static void getCurrentLocation(LocationManager locationManager, String provider, CancellationSignal cancellationSignal, Executor executor, final Consumer<Location> consumer) {
        if (Build.VERSION.SDK_INT >= 30) {
            Api30Impl.getCurrentLocation(locationManager, provider, cancellationSignal, executor, consumer);
            return;
        }
        if (cancellationSignal != null) {
            cancellationSignal.throwIfCanceled();
        }
        final Location location = locationManager.getLastKnownLocation(provider);
        if (location != null) {
            long locationAgeMs = SystemClock.elapsedRealtime() - LocationCompat.getElapsedRealtimeMillis(location);
            if (locationAgeMs < MAX_CURRENT_LOCATION_AGE_MS) {
                executor.execute(new Runnable() { // from class: androidx.core.location.LocationManagerCompat$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        Consumer.this.accept(location);
                    }
                });
                return;
            }
        }
        final CancellableLocationListener listener = new CancellableLocationListener(locationManager, executor, consumer);
        locationManager.requestLocationUpdates(provider, 0L, 0.0f, listener, Looper.getMainLooper());
        if (cancellationSignal != null) {
            Objects.requireNonNull(listener);
            cancellationSignal.setOnCancelListener(new CancellationSignal.OnCancelListener() { // from class: androidx.core.location.LocationManagerCompat$$ExternalSyntheticLambda0
                @Override // androidx.core.os.CancellationSignal.OnCancelListener
                public final void onCancel() {
                    LocationManagerCompat.CancellableLocationListener.this.cancel();
                }
            });
        }
        listener.startTimeout(GET_CURRENT_LOCATION_TIMEOUT_MS);
    }

    public static void requestLocationUpdates(LocationManager locationManager, String provider, LocationRequestCompat locationRequest, Executor executor, LocationListenerCompat listener) {
        if (Build.VERSION.SDK_INT >= 31) {
            Api31Impl.requestLocationUpdates(locationManager, provider, locationRequest.toLocationRequest(), executor, listener);
        } else if (Build.VERSION.SDK_INT >= 30 && Api30Impl.tryRequestLocationUpdates(locationManager, provider, locationRequest, executor, listener)) {
        } else {
            LocationListenerTransport transport = new LocationListenerTransport(new LocationListenerKey(provider, listener), executor);
            if (Build.VERSION.SDK_INT >= 19 && Api19Impl.tryRequestLocationUpdates(locationManager, provider, locationRequest, transport)) {
                return;
            }
            synchronized (sLocationListeners) {
                locationManager.requestLocationUpdates(provider, locationRequest.getIntervalMillis(), locationRequest.getMinUpdateDistanceMeters(), transport, Looper.getMainLooper());
                registerLocationListenerTransport(locationManager, transport);
            }
        }
    }

    static void registerLocationListenerTransport(LocationManager locationManager, LocationListenerTransport transport) {
        WeakReference<LocationListenerTransport> oldRef = sLocationListeners.put(transport.getKey(), new WeakReference<>(transport));
        LocationListenerTransport oldTransport = oldRef != null ? oldRef.get() : null;
        if (oldTransport != null) {
            oldTransport.unregister();
            locationManager.removeUpdates(oldTransport);
        }
    }

    public static void requestLocationUpdates(LocationManager locationManager, String provider, LocationRequestCompat locationRequest, LocationListenerCompat listener, Looper looper) {
        if (Build.VERSION.SDK_INT >= 31) {
            Api31Impl.requestLocationUpdates(locationManager, provider, locationRequest.toLocationRequest(), ExecutorCompat.create(new Handler(looper)), listener);
        } else if (Build.VERSION.SDK_INT >= 19 && Api19Impl.tryRequestLocationUpdates(locationManager, provider, locationRequest, listener, looper)) {
        } else {
            locationManager.requestLocationUpdates(provider, locationRequest.getIntervalMillis(), locationRequest.getMinUpdateDistanceMeters(), listener, looper);
        }
    }

    public static void removeUpdates(LocationManager locationManager, LocationListenerCompat listener) {
        WeakHashMap<LocationListenerKey, WeakReference<LocationListenerTransport>> weakHashMap = sLocationListeners;
        synchronized (weakHashMap) {
            ArrayList<LocationListenerKey> cleanup = null;
            for (WeakReference<LocationListenerTransport> transportRef : weakHashMap.values()) {
                LocationListenerTransport transport = transportRef.get();
                if (transport != null) {
                    LocationListenerKey key = transport.getKey();
                    if (key.mListener == listener) {
                        if (cleanup == null) {
                            cleanup = new ArrayList<>();
                        }
                        cleanup.add(key);
                        transport.unregister();
                        locationManager.removeUpdates(transport);
                    }
                }
            }
            if (cleanup != null) {
                Iterator<LocationListenerKey> it = cleanup.iterator();
                while (it.hasNext()) {
                    sLocationListeners.remove(it.next());
                }
            }
        }
        locationManager.removeUpdates(listener);
    }

    public static String getGnssHardwareModelName(LocationManager locationManager) {
        if (Build.VERSION.SDK_INT >= 28) {
            return Api28Impl.getGnssHardwareModelName(locationManager);
        }
        return null;
    }

    public static int getGnssYearOfHardware(LocationManager locationManager) {
        if (Build.VERSION.SDK_INT >= 28) {
            return Api28Impl.getGnssYearOfHardware(locationManager);
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class GnssLazyLoader {
        static final SimpleArrayMap<Object, Object> sGnssStatusListeners = new SimpleArrayMap<>();

        private GnssLazyLoader() {
        }
    }

    public static boolean registerGnssStatusCallback(LocationManager locationManager, GnssStatusCompat.Callback callback, Handler handler) {
        if (Build.VERSION.SDK_INT >= 30) {
            return registerGnssStatusCallback(locationManager, ExecutorCompat.create(handler), callback);
        }
        return registerGnssStatusCallback(locationManager, new InlineHandlerExecutor(handler), callback);
    }

    public static boolean registerGnssStatusCallback(LocationManager locationManager, Executor executor, GnssStatusCompat.Callback callback) {
        if (Build.VERSION.SDK_INT >= 30) {
            return registerGnssStatusCallback(locationManager, null, executor, callback);
        }
        Looper looper = Looper.myLooper();
        if (looper == null) {
            looper = Looper.getMainLooper();
        }
        return registerGnssStatusCallback(locationManager, new Handler(looper), executor, callback);
    }

    private static boolean registerGnssStatusCallback(final LocationManager locationManager, Handler baseHandler, Executor executor, GnssStatusCompat.Callback callback) {
        GpsStatusTransport transport;
        if (Build.VERSION.SDK_INT >= 30) {
            return Api30Impl.registerGnssStatusCallback(locationManager, baseHandler, executor, callback);
        }
        if (Build.VERSION.SDK_INT >= 24) {
            return Api24Impl.registerGnssStatusCallback(locationManager, baseHandler, executor, callback);
        }
        Preconditions.checkArgument(baseHandler != null);
        synchronized (GnssLazyLoader.sGnssStatusListeners) {
            try {
            } catch (Throwable th) {
                th = th;
            }
            try {
                GpsStatusTransport transport2 = (GpsStatusTransport) GnssLazyLoader.sGnssStatusListeners.get(callback);
                if (transport2 == null) {
                    transport = new GpsStatusTransport(locationManager, callback);
                } else {
                    transport2.unregister();
                    transport = transport2;
                }
                transport.register(executor);
                final GpsStatusTransport myTransport = transport;
                FutureTask<Boolean> task = new FutureTask<>(new Callable() { // from class: androidx.core.location.LocationManagerCompat$$ExternalSyntheticLambda2
                    @Override // java.util.concurrent.Callable
                    public final Object call() {
                        Boolean valueOf;
                        valueOf = Boolean.valueOf(locationManager.addGpsStatusListener(myTransport));
                        return valueOf;
                    }
                });
                if (Looper.myLooper() == baseHandler.getLooper()) {
                    task.run();
                } else if (!baseHandler.post(task)) {
                    throw new IllegalStateException(baseHandler + " is shutting down");
                }
                boolean interrupted = false;
                try {
                    long remainingNanos = TimeUnit.SECONDS.toNanos(PRE_N_LOOPER_TIMEOUT_S);
                    long end = System.nanoTime() + remainingNanos;
                    while (task.get(remainingNanos, TimeUnit.NANOSECONDS).booleanValue()) {
                        try {
                            GnssLazyLoader.sGnssStatusListeners.put(callback, myTransport);
                            if (interrupted) {
                                Thread.currentThread().interrupt();
                            }
                            return true;
                        } catch (InterruptedException e) {
                            interrupted = true;
                            remainingNanos = end - System.nanoTime();
                        }
                    }
                    if (interrupted) {
                        Thread.currentThread().interrupt();
                    }
                    return false;
                } catch (ExecutionException e2) {
                    if (e2.getCause() instanceof RuntimeException) {
                        throw ((RuntimeException) e2.getCause());
                    }
                    if (e2.getCause() instanceof Error) {
                        throw ((Error) e2.getCause());
                    }
                    throw new IllegalStateException(e2);
                } catch (TimeoutException e3) {
                    throw new IllegalStateException(baseHandler + " appears to be blocked, please run registerGnssStatusCallback() directly on a Looper thread or ensure the main Looper is not blocked by this thread", e3);
                }
            } catch (Throwable th2) {
                th = th2;
                throw th;
            }
        }
    }

    public static void unregisterGnssStatusCallback(LocationManager locationManager, GnssStatusCompat.Callback callback) {
        if (Build.VERSION.SDK_INT >= 24) {
            synchronized (GnssLazyLoader.sGnssStatusListeners) {
                Object transport = GnssLazyLoader.sGnssStatusListeners.remove(callback);
                if (transport != null) {
                    Api24Impl.unregisterGnssStatusCallback(locationManager, transport);
                }
            }
            return;
        }
        synchronized (GnssLazyLoader.sGnssStatusListeners) {
            GpsStatusTransport transport2 = (GpsStatusTransport) GnssLazyLoader.sGnssStatusListeners.remove(callback);
            if (transport2 != null) {
                transport2.unregister();
                locationManager.removeGpsStatusListener(transport2);
            }
        }
    }

    private LocationManagerCompat() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class LocationListenerKey {
        final LocationListenerCompat mListener;
        final String mProvider;

        LocationListenerKey(String provider, LocationListenerCompat listener) {
            this.mProvider = (String) ObjectsCompat.requireNonNull(provider, "invalid null provider");
            this.mListener = (LocationListenerCompat) ObjectsCompat.requireNonNull(listener, "invalid null listener");
        }

        public boolean equals(Object o) {
            if (o instanceof LocationListenerKey) {
                LocationListenerKey that = (LocationListenerKey) o;
                return this.mProvider.equals(that.mProvider) && this.mListener.equals(that.mListener);
            }
            return false;
        }

        public int hashCode() {
            return ObjectsCompat.hash(this.mProvider, this.mListener);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class LocationListenerTransport implements LocationListener {
        final Executor mExecutor;
        volatile LocationListenerKey mKey;

        LocationListenerTransport(LocationListenerKey key, Executor executor) {
            this.mKey = key;
            this.mExecutor = executor;
        }

        public LocationListenerKey getKey() {
            return (LocationListenerKey) ObjectsCompat.requireNonNull(this.mKey);
        }

        public void unregister() {
            this.mKey = null;
        }

        @Override // android.location.LocationListener
        public void onLocationChanged(final Location location) {
            if (this.mKey == null) {
                return;
            }
            this.mExecutor.execute(new Runnable() { // from class: androidx.core.location.LocationManagerCompat$LocationListenerTransport$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    LocationManagerCompat.LocationListenerTransport.this.m21xa8d50b3d(location);
                }
            });
        }

        /* renamed from: lambda$onLocationChanged$0$androidx-core-location-LocationManagerCompat$LocationListenerTransport  reason: not valid java name */
        public /* synthetic */ void m21xa8d50b3d(Location location) {
            LocationListenerKey key = this.mKey;
            if (key == null) {
                return;
            }
            key.mListener.onLocationChanged(location);
        }

        @Override // android.location.LocationListener
        public void onLocationChanged(final List<Location> locations) {
            if (this.mKey == null) {
                return;
            }
            this.mExecutor.execute(new Runnable() { // from class: androidx.core.location.LocationManagerCompat$LocationListenerTransport$$ExternalSyntheticLambda5
                @Override // java.lang.Runnable
                public final void run() {
                    LocationManagerCompat.LocationListenerTransport.this.m22x2b1fc01c(locations);
                }
            });
        }

        /* renamed from: lambda$onLocationChanged$1$androidx-core-location-LocationManagerCompat$LocationListenerTransport  reason: not valid java name */
        public /* synthetic */ void m22x2b1fc01c(List locations) {
            LocationListenerKey key = this.mKey;
            if (key == null) {
                return;
            }
            key.mListener.onLocationChanged(locations);
        }

        @Override // android.location.LocationListener
        public void onFlushComplete(final int requestCode) {
            if (this.mKey == null) {
                return;
            }
            this.mExecutor.execute(new Runnable() { // from class: androidx.core.location.LocationManagerCompat$LocationListenerTransport$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    LocationManagerCompat.LocationListenerTransport.this.m20xf04cfe9d(requestCode);
                }
            });
        }

        /* renamed from: lambda$onFlushComplete$2$androidx-core-location-LocationManagerCompat$LocationListenerTransport  reason: not valid java name */
        public /* synthetic */ void m20xf04cfe9d(int requestCode) {
            LocationListenerKey key = this.mKey;
            if (key == null) {
                return;
            }
            key.mListener.onFlushComplete(requestCode);
        }

        @Override // android.location.LocationListener
        public void onStatusChanged(final String provider, final int status, final Bundle extras) {
            if (this.mKey == null) {
                return;
            }
            this.mExecutor.execute(new Runnable() { // from class: androidx.core.location.LocationManagerCompat$LocationListenerTransport$$ExternalSyntheticLambda4
                @Override // java.lang.Runnable
                public final void run() {
                    LocationManagerCompat.LocationListenerTransport.this.m25xdbe6a717(provider, status, extras);
                }
            });
        }

        /* renamed from: lambda$onStatusChanged$3$androidx-core-location-LocationManagerCompat$LocationListenerTransport  reason: not valid java name */
        public /* synthetic */ void m25xdbe6a717(String provider, int status, Bundle extras) {
            LocationListenerKey key = this.mKey;
            if (key == null) {
                return;
            }
            key.mListener.onStatusChanged(provider, status, extras);
        }

        @Override // android.location.LocationListener
        public void onProviderEnabled(final String provider) {
            if (this.mKey == null) {
                return;
            }
            this.mExecutor.execute(new Runnable() { // from class: androidx.core.location.LocationManagerCompat$LocationListenerTransport$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    LocationManagerCompat.LocationListenerTransport.this.m24x5a2a7b08(provider);
                }
            });
        }

        /* renamed from: lambda$onProviderEnabled$4$androidx-core-location-LocationManagerCompat$LocationListenerTransport  reason: not valid java name */
        public /* synthetic */ void m24x5a2a7b08(String provider) {
            LocationListenerKey key = this.mKey;
            if (key == null) {
                return;
            }
            key.mListener.onProviderEnabled(provider);
        }

        @Override // android.location.LocationListener
        public void onProviderDisabled(final String provider) {
            if (this.mKey == null) {
                return;
            }
            this.mExecutor.execute(new Runnable() { // from class: androidx.core.location.LocationManagerCompat$LocationListenerTransport$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    LocationManagerCompat.LocationListenerTransport.this.m23x442abc92(provider);
                }
            });
        }

        /* renamed from: lambda$onProviderDisabled$5$androidx-core-location-LocationManagerCompat$LocationListenerTransport  reason: not valid java name */
        public /* synthetic */ void m23x442abc92(String provider) {
            LocationListenerKey key = this.mKey;
            if (key == null) {
                return;
            }
            key.mListener.onProviderDisabled(provider);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class GnssStatusTransport extends GnssStatus.Callback {
        final GnssStatusCompat.Callback mCallback;

        GnssStatusTransport(GnssStatusCompat.Callback callback) {
            Preconditions.checkArgument(callback != null, "invalid null callback");
            this.mCallback = callback;
        }

        @Override // android.location.GnssStatus.Callback
        public void onStarted() {
            this.mCallback.onStarted();
        }

        @Override // android.location.GnssStatus.Callback
        public void onStopped() {
            this.mCallback.onStopped();
        }

        @Override // android.location.GnssStatus.Callback
        public void onFirstFix(int ttffMillis) {
            this.mCallback.onFirstFix(ttffMillis);
        }

        @Override // android.location.GnssStatus.Callback
        public void onSatelliteStatusChanged(GnssStatus status) {
            this.mCallback.onSatelliteStatusChanged(GnssStatusCompat.wrap(status));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class PreRGnssStatusTransport extends GnssStatus.Callback {
        final GnssStatusCompat.Callback mCallback;
        volatile Executor mExecutor;

        PreRGnssStatusTransport(GnssStatusCompat.Callback callback) {
            Preconditions.checkArgument(callback != null, "invalid null callback");
            this.mCallback = callback;
        }

        public void register(Executor executor) {
            Preconditions.checkArgument(executor != null, "invalid null executor");
            Preconditions.checkState(this.mExecutor == null);
            this.mExecutor = executor;
        }

        public void unregister() {
            this.mExecutor = null;
        }

        @Override // android.location.GnssStatus.Callback
        public void onStarted() {
            final Executor executor = this.mExecutor;
            if (executor == null) {
                return;
            }
            executor.execute(new Runnable() { // from class: androidx.core.location.LocationManagerCompat$PreRGnssStatusTransport$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    LocationManagerCompat.PreRGnssStatusTransport.this.m28x7ba12b9c(executor);
                }
            });
        }

        /* renamed from: lambda$onStarted$0$androidx-core-location-LocationManagerCompat$PreRGnssStatusTransport  reason: not valid java name */
        public /* synthetic */ void m28x7ba12b9c(Executor executor) {
            if (this.mExecutor != executor) {
                return;
            }
            this.mCallback.onStarted();
        }

        @Override // android.location.GnssStatus.Callback
        public void onStopped() {
            final Executor executor = this.mExecutor;
            if (executor == null) {
                return;
            }
            executor.execute(new Runnable() { // from class: androidx.core.location.LocationManagerCompat$PreRGnssStatusTransport$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    LocationManagerCompat.PreRGnssStatusTransport.this.m29x80a5cd6f(executor);
                }
            });
        }

        /* renamed from: lambda$onStopped$1$androidx-core-location-LocationManagerCompat$PreRGnssStatusTransport  reason: not valid java name */
        public /* synthetic */ void m29x80a5cd6f(Executor executor) {
            if (this.mExecutor != executor) {
                return;
            }
            this.mCallback.onStopped();
        }

        @Override // android.location.GnssStatus.Callback
        public void onFirstFix(final int ttffMillis) {
            final Executor executor = this.mExecutor;
            if (executor == null) {
                return;
            }
            executor.execute(new Runnable() { // from class: androidx.core.location.LocationManagerCompat$PreRGnssStatusTransport$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    LocationManagerCompat.PreRGnssStatusTransport.this.m26x4191f1e(executor, ttffMillis);
                }
            });
        }

        /* renamed from: lambda$onFirstFix$2$androidx-core-location-LocationManagerCompat$PreRGnssStatusTransport  reason: not valid java name */
        public /* synthetic */ void m26x4191f1e(Executor executor, int ttffMillis) {
            if (this.mExecutor != executor) {
                return;
            }
            this.mCallback.onFirstFix(ttffMillis);
        }

        @Override // android.location.GnssStatus.Callback
        public void onSatelliteStatusChanged(final GnssStatus status) {
            final Executor executor = this.mExecutor;
            if (executor == null) {
                return;
            }
            executor.execute(new Runnable() { // from class: androidx.core.location.LocationManagerCompat$PreRGnssStatusTransport$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    LocationManagerCompat.PreRGnssStatusTransport.this.m27xdecf6cdb(executor, status);
                }
            });
        }

        /* renamed from: lambda$onSatelliteStatusChanged$3$androidx-core-location-LocationManagerCompat$PreRGnssStatusTransport  reason: not valid java name */
        public /* synthetic */ void m27xdecf6cdb(Executor executor, GnssStatus status) {
            if (this.mExecutor != executor) {
                return;
            }
            this.mCallback.onSatelliteStatusChanged(GnssStatusCompat.wrap(status));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class GpsStatusTransport implements GpsStatus.Listener {
        final GnssStatusCompat.Callback mCallback;
        volatile Executor mExecutor;
        private final LocationManager mLocationManager;

        GpsStatusTransport(LocationManager locationManager, GnssStatusCompat.Callback callback) {
            Preconditions.checkArgument(callback != null, "invalid null callback");
            this.mLocationManager = locationManager;
            this.mCallback = callback;
        }

        public void register(Executor executor) {
            Preconditions.checkState(this.mExecutor == null);
            this.mExecutor = executor;
        }

        public void unregister() {
            this.mExecutor = null;
        }

        @Override // android.location.GpsStatus.Listener
        public void onGpsStatusChanged(int event) {
            final Executor executor = this.mExecutor;
            if (executor == null) {
                return;
            }
            switch (event) {
                case 1:
                    executor.execute(new Runnable() { // from class: androidx.core.location.LocationManagerCompat$GpsStatusTransport$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() {
                            LocationManagerCompat.GpsStatusTransport.this.m16x75e92221(executor);
                        }
                    });
                    return;
                case 2:
                    executor.execute(new Runnable() { // from class: androidx.core.location.LocationManagerCompat$GpsStatusTransport$$ExternalSyntheticLambda1
                        @Override // java.lang.Runnable
                        public final void run() {
                            LocationManagerCompat.GpsStatusTransport.this.m17xc3a89a22(executor);
                        }
                    });
                    return;
                case 3:
                    GpsStatus gpsStatus = this.mLocationManager.getGpsStatus(null);
                    if (gpsStatus != null) {
                        final int ttff = gpsStatus.getTimeToFirstFix();
                        executor.execute(new Runnable() { // from class: androidx.core.location.LocationManagerCompat$GpsStatusTransport$$ExternalSyntheticLambda2
                            @Override // java.lang.Runnable
                            public final void run() {
                                LocationManagerCompat.GpsStatusTransport.this.m18x11681223(executor, ttff);
                            }
                        });
                        return;
                    }
                    return;
                case 4:
                    GpsStatus gpsStatus2 = this.mLocationManager.getGpsStatus(null);
                    if (gpsStatus2 != null) {
                        final GnssStatusCompat gnssStatus = GnssStatusCompat.wrap(gpsStatus2);
                        executor.execute(new Runnable() { // from class: androidx.core.location.LocationManagerCompat$GpsStatusTransport$$ExternalSyntheticLambda3
                            @Override // java.lang.Runnable
                            public final void run() {
                                LocationManagerCompat.GpsStatusTransport.this.m19x5f278a24(executor, gnssStatus);
                            }
                        });
                        return;
                    }
                    return;
                default:
                    return;
            }
        }

        /* renamed from: lambda$onGpsStatusChanged$0$androidx-core-location-LocationManagerCompat$GpsStatusTransport  reason: not valid java name */
        public /* synthetic */ void m16x75e92221(Executor executor) {
            if (this.mExecutor != executor) {
                return;
            }
            this.mCallback.onStarted();
        }

        /* renamed from: lambda$onGpsStatusChanged$1$androidx-core-location-LocationManagerCompat$GpsStatusTransport  reason: not valid java name */
        public /* synthetic */ void m17xc3a89a22(Executor executor) {
            if (this.mExecutor != executor) {
                return;
            }
            this.mCallback.onStopped();
        }

        /* renamed from: lambda$onGpsStatusChanged$2$androidx-core-location-LocationManagerCompat$GpsStatusTransport  reason: not valid java name */
        public /* synthetic */ void m18x11681223(Executor executor, int ttff) {
            if (this.mExecutor != executor) {
                return;
            }
            this.mCallback.onFirstFix(ttff);
        }

        /* renamed from: lambda$onGpsStatusChanged$3$androidx-core-location-LocationManagerCompat$GpsStatusTransport  reason: not valid java name */
        public /* synthetic */ void m19x5f278a24(Executor executor, GnssStatusCompat gnssStatus) {
            if (this.mExecutor != executor) {
                return;
            }
            this.mCallback.onSatelliteStatusChanged(gnssStatus);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class CancellableLocationListener implements LocationListener {
        private Consumer<Location> mConsumer;
        private final Executor mExecutor;
        private final LocationManager mLocationManager;
        private final Handler mTimeoutHandler = new Handler(Looper.getMainLooper());
        Runnable mTimeoutRunnable;
        private boolean mTriggered;

        CancellableLocationListener(LocationManager locationManager, Executor executor, Consumer<Location> consumer) {
            this.mLocationManager = locationManager;
            this.mExecutor = executor;
            this.mConsumer = consumer;
        }

        public void cancel() {
            synchronized (this) {
                if (this.mTriggered) {
                    return;
                }
                this.mTriggered = true;
                cleanup();
            }
        }

        public void startTimeout(long timeoutMs) {
            synchronized (this) {
                if (this.mTriggered) {
                    return;
                }
                Runnable runnable = new Runnable() { // from class: androidx.core.location.LocationManagerCompat$CancellableLocationListener$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        LocationManagerCompat.CancellableLocationListener.this.m15x40ccd759();
                    }
                };
                this.mTimeoutRunnable = runnable;
                this.mTimeoutHandler.postDelayed(runnable, timeoutMs);
            }
        }

        /* renamed from: lambda$startTimeout$0$androidx-core-location-LocationManagerCompat$CancellableLocationListener  reason: not valid java name */
        public /* synthetic */ void m15x40ccd759() {
            this.mTimeoutRunnable = null;
            onLocationChanged((Location) null);
        }

        @Override // android.location.LocationListener
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        @Override // android.location.LocationListener
        public void onProviderEnabled(String provider) {
        }

        @Override // android.location.LocationListener
        public void onProviderDisabled(String p) {
            onLocationChanged((Location) null);
        }

        @Override // android.location.LocationListener
        public void onLocationChanged(final Location location) {
            synchronized (this) {
                if (this.mTriggered) {
                    return;
                }
                this.mTriggered = true;
                final Consumer<Location> consumer = this.mConsumer;
                this.mExecutor.execute(new Runnable() { // from class: androidx.core.location.LocationManagerCompat$CancellableLocationListener$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        Consumer.this.accept(location);
                    }
                });
                cleanup();
            }
        }

        private void cleanup() {
            this.mConsumer = null;
            this.mLocationManager.removeUpdates(this);
            Runnable runnable = this.mTimeoutRunnable;
            if (runnable != null) {
                this.mTimeoutHandler.removeCallbacks(runnable);
                this.mTimeoutRunnable = null;
            }
        }
    }

    /* loaded from: classes.dex */
    private static final class InlineHandlerExecutor implements Executor {
        private final Handler mHandler;

        InlineHandlerExecutor(Handler handler) {
            this.mHandler = (Handler) Preconditions.checkNotNull(handler);
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable command) {
            if (Looper.myLooper() == this.mHandler.getLooper()) {
                command.run();
            } else if (!this.mHandler.post((Runnable) Preconditions.checkNotNull(command))) {
                throw new RejectedExecutionException(this.mHandler + " is shutting down");
            }
        }
    }

    /* loaded from: classes.dex */
    private static class Api31Impl {
        private Api31Impl() {
        }

        static boolean hasProvider(LocationManager locationManager, String provider) {
            return locationManager.hasProvider(provider);
        }

        static void requestLocationUpdates(LocationManager locationManager, String provider, LocationRequest locationRequest, Executor executor, LocationListener listener) {
            locationManager.requestLocationUpdates(provider, locationRequest, executor, listener);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class Api30Impl {
        private static Class<?> sLocationRequestClass;
        private static Method sRequestLocationUpdatesExecutorMethod;

        private Api30Impl() {
        }

        static void getCurrentLocation(LocationManager locationManager, String provider, CancellationSignal cancellationSignal, Executor executor, final Consumer<Location> consumer) {
            android.os.CancellationSignal cancellationSignal2;
            if (cancellationSignal != null) {
                cancellationSignal2 = (android.os.CancellationSignal) cancellationSignal.getCancellationSignalObject();
            } else {
                cancellationSignal2 = null;
            }
            Objects.requireNonNull(consumer);
            locationManager.getCurrentLocation(provider, cancellationSignal2, executor, new java.util.function.Consumer() { // from class: androidx.core.location.LocationManagerCompat$Api30Impl$$ExternalSyntheticLambda0
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    Consumer.this.accept((Location) obj);
                }
            });
        }

        public static boolean tryRequestLocationUpdates(LocationManager locationManager, String provider, LocationRequestCompat locationRequest, Executor executor, LocationListenerCompat listener) {
            if (Build.VERSION.SDK_INT >= 30) {
                try {
                    if (sLocationRequestClass == null) {
                        sLocationRequestClass = Class.forName("android.location.LocationRequest");
                    }
                    if (sRequestLocationUpdatesExecutorMethod == null) {
                        Method declaredMethod = LocationManager.class.getDeclaredMethod("requestLocationUpdates", sLocationRequestClass, Executor.class, LocationListener.class);
                        sRequestLocationUpdatesExecutorMethod = declaredMethod;
                        declaredMethod.setAccessible(true);
                    }
                    Object request = locationRequest.toLocationRequest(provider);
                    if (request != null) {
                        sRequestLocationUpdatesExecutorMethod.invoke(locationManager, request, executor, listener);
                        return true;
                    }
                } catch (ClassNotFoundException e) {
                } catch (IllegalAccessException e2) {
                } catch (NoSuchMethodException e3) {
                } catch (UnsupportedOperationException e4) {
                } catch (InvocationTargetException e5) {
                }
            }
            return false;
        }

        public static boolean registerGnssStatusCallback(LocationManager locationManager, Handler baseHandler, Executor executor, GnssStatusCompat.Callback callback) {
            synchronized (GnssLazyLoader.sGnssStatusListeners) {
                GnssStatusTransport transport = (GnssStatusTransport) GnssLazyLoader.sGnssStatusListeners.get(callback);
                if (transport == null) {
                    transport = new GnssStatusTransport(callback);
                }
                if (locationManager.registerGnssStatusCallback(executor, transport)) {
                    GnssLazyLoader.sGnssStatusListeners.put(callback, transport);
                    return true;
                }
                return false;
            }
        }
    }

    /* loaded from: classes.dex */
    private static class Api28Impl {
        private Api28Impl() {
        }

        static boolean isLocationEnabled(LocationManager locationManager) {
            return locationManager.isLocationEnabled();
        }

        static String getGnssHardwareModelName(LocationManager locationManager) {
            return locationManager.getGnssHardwareModelName();
        }

        static int getGnssYearOfHardware(LocationManager locationManager) {
            return locationManager.getGnssYearOfHardware();
        }
    }

    /* loaded from: classes.dex */
    static class Api19Impl {
        private static Class<?> sLocationRequestClass;
        private static Method sRequestLocationUpdatesLooperMethod;

        private Api19Impl() {
        }

        static boolean tryRequestLocationUpdates(LocationManager locationManager, String provider, LocationRequestCompat locationRequest, LocationListenerTransport transport) {
            if (Build.VERSION.SDK_INT >= 19) {
                try {
                    if (sLocationRequestClass == null) {
                        sLocationRequestClass = Class.forName("android.location.LocationRequest");
                    }
                    if (sRequestLocationUpdatesLooperMethod == null) {
                        Method declaredMethod = LocationManager.class.getDeclaredMethod("requestLocationUpdates", sLocationRequestClass, LocationListener.class, Looper.class);
                        sRequestLocationUpdatesLooperMethod = declaredMethod;
                        declaredMethod.setAccessible(true);
                    }
                    LocationRequest request = locationRequest.toLocationRequest(provider);
                    if (request != null) {
                        synchronized (LocationManagerCompat.sLocationListeners) {
                            sRequestLocationUpdatesLooperMethod.invoke(locationManager, request, transport, Looper.getMainLooper());
                            LocationManagerCompat.registerLocationListenerTransport(locationManager, transport);
                        }
                        return true;
                    }
                } catch (ClassNotFoundException e) {
                } catch (IllegalAccessException e2) {
                } catch (NoSuchMethodException e3) {
                } catch (UnsupportedOperationException e4) {
                } catch (InvocationTargetException e5) {
                }
            }
            return false;
        }

        static boolean tryRequestLocationUpdates(LocationManager locationManager, String provider, LocationRequestCompat locationRequest, LocationListenerCompat listener, Looper looper) {
            if (Build.VERSION.SDK_INT >= 19) {
                try {
                    if (sLocationRequestClass == null) {
                        sLocationRequestClass = Class.forName("android.location.LocationRequest");
                    }
                    if (sRequestLocationUpdatesLooperMethod == null) {
                        Method declaredMethod = LocationManager.class.getDeclaredMethod("requestLocationUpdates", sLocationRequestClass, LocationListener.class, Looper.class);
                        sRequestLocationUpdatesLooperMethod = declaredMethod;
                        declaredMethod.setAccessible(true);
                    }
                    LocationRequest request = locationRequest.toLocationRequest(provider);
                    if (request != null) {
                        sRequestLocationUpdatesLooperMethod.invoke(locationManager, request, listener, looper);
                        return true;
                    }
                } catch (ClassNotFoundException e) {
                } catch (IllegalAccessException e2) {
                } catch (NoSuchMethodException e3) {
                } catch (UnsupportedOperationException e4) {
                } catch (InvocationTargetException e5) {
                }
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class Api24Impl {
        private Api24Impl() {
        }

        static boolean registerGnssStatusCallback(LocationManager locationManager, Handler baseHandler, Executor executor, GnssStatusCompat.Callback callback) {
            Preconditions.checkArgument(baseHandler != null);
            synchronized (GnssLazyLoader.sGnssStatusListeners) {
                PreRGnssStatusTransport transport = (PreRGnssStatusTransport) GnssLazyLoader.sGnssStatusListeners.get(callback);
                if (transport == null) {
                    transport = new PreRGnssStatusTransport(callback);
                } else {
                    transport.unregister();
                }
                transport.register(executor);
                if (locationManager.registerGnssStatusCallback(transport, baseHandler)) {
                    GnssLazyLoader.sGnssStatusListeners.put(callback, transport);
                    return true;
                }
                return false;
            }
        }

        static void unregisterGnssStatusCallback(LocationManager locationManager, Object callback) {
            if (callback instanceof PreRGnssStatusTransport) {
                ((PreRGnssStatusTransport) callback).unregister();
            }
            locationManager.unregisterGnssStatusCallback((GnssStatus.Callback) callback);
        }
    }
}
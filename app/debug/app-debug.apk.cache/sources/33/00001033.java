package com.google.android.gms.maps.internal;

import android.location.Location;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzed;
import com.google.android.gms.internal.zzef;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.android.gms.maps.model.internal.IPolylineDelegate;

/* loaded from: classes.dex */
public final class zzg extends zzed implements IGoogleMapDelegate {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzg(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.internal.IGoogleMapDelegate");
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final com.google.android.gms.maps.model.internal.zzd addCircle(CircleOptions circleOptions) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, circleOptions);
        Parcel zza = zza(35, zzaz);
        com.google.android.gms.maps.model.internal.zzd zzbf = com.google.android.gms.maps.model.internal.zze.zzbf(zza.readStrongBinder());
        zza.recycle();
        return zzbf;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final com.google.android.gms.maps.model.internal.zzg addGroundOverlay(GroundOverlayOptions groundOverlayOptions) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, groundOverlayOptions);
        Parcel zza = zza(12, zzaz);
        com.google.android.gms.maps.model.internal.zzg zzbg = com.google.android.gms.maps.model.internal.zzh.zzbg(zza.readStrongBinder());
        zza.recycle();
        return zzbg;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final com.google.android.gms.maps.model.internal.zzp addMarker(MarkerOptions markerOptions) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, markerOptions);
        Parcel zza = zza(11, zzaz);
        com.google.android.gms.maps.model.internal.zzp zzbj = com.google.android.gms.maps.model.internal.zzq.zzbj(zza.readStrongBinder());
        zza.recycle();
        return zzbj;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final com.google.android.gms.maps.model.internal.zzs addPolygon(PolygonOptions polygonOptions) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, polygonOptions);
        Parcel zza = zza(10, zzaz);
        com.google.android.gms.maps.model.internal.zzs zzbk = com.google.android.gms.maps.model.internal.zzt.zzbk(zza.readStrongBinder());
        zza.recycle();
        return zzbk;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final IPolylineDelegate addPolyline(PolylineOptions polylineOptions) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, polylineOptions);
        Parcel zza = zza(9, zzaz);
        IPolylineDelegate zzbl = IPolylineDelegate.zza.zzbl(zza.readStrongBinder());
        zza.recycle();
        return zzbl;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final com.google.android.gms.maps.model.internal.zzw addTileOverlay(TileOverlayOptions tileOverlayOptions) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, tileOverlayOptions);
        Parcel zza = zza(13, zzaz);
        com.google.android.gms.maps.model.internal.zzw zzbm = com.google.android.gms.maps.model.internal.zzx.zzbm(zza.readStrongBinder());
        zza.recycle();
        return zzbm;
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void animateCamera(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, iObjectWrapper);
        zzb(5, zzaz);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void animateCameraWithCallback(IObjectWrapper iObjectWrapper, zzc zzcVar) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, iObjectWrapper);
        zzef.zza(zzaz, zzcVar);
        zzb(6, zzaz);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void animateCameraWithDurationAndCallback(IObjectWrapper iObjectWrapper, int i, zzc zzcVar) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, iObjectWrapper);
        zzaz.writeInt(i);
        zzef.zza(zzaz, zzcVar);
        zzb(7, zzaz);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void clear() throws RemoteException {
        zzb(14, zzaz());
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final CameraPosition getCameraPosition() throws RemoteException {
        Parcel zza = zza(1, zzaz());
        CameraPosition cameraPosition = (CameraPosition) zzef.zza(zza, CameraPosition.CREATOR);
        zza.recycle();
        return cameraPosition;
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final com.google.android.gms.maps.model.internal.zzj getFocusedBuilding() throws RemoteException {
        Parcel zza = zza(44, zzaz());
        com.google.android.gms.maps.model.internal.zzj zzbh = com.google.android.gms.maps.model.internal.zzk.zzbh(zza.readStrongBinder());
        zza.recycle();
        return zzbh;
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void getMapAsync(zzap zzapVar) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, zzapVar);
        zzb(53, zzaz);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final int getMapType() throws RemoteException {
        Parcel zza = zza(15, zzaz());
        int readInt = zza.readInt();
        zza.recycle();
        return readInt;
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final float getMaxZoomLevel() throws RemoteException {
        Parcel zza = zza(2, zzaz());
        float readFloat = zza.readFloat();
        zza.recycle();
        return readFloat;
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final float getMinZoomLevel() throws RemoteException {
        Parcel zza = zza(3, zzaz());
        float readFloat = zza.readFloat();
        zza.recycle();
        return readFloat;
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final Location getMyLocation() throws RemoteException {
        Parcel zza = zza(23, zzaz());
        Location location = (Location) zzef.zza(zza, Location.CREATOR);
        zza.recycle();
        return location;
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final IProjectionDelegate getProjection() throws RemoteException {
        IProjectionDelegate zzbrVar;
        Parcel zza = zza(26, zzaz());
        IBinder readStrongBinder = zza.readStrongBinder();
        if (readStrongBinder == null) {
            zzbrVar = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.maps.internal.IProjectionDelegate");
            zzbrVar = queryLocalInterface instanceof IProjectionDelegate ? (IProjectionDelegate) queryLocalInterface : new zzbr(readStrongBinder);
        }
        zza.recycle();
        return zzbrVar;
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final IUiSettingsDelegate getUiSettings() throws RemoteException {
        IUiSettingsDelegate zzbxVar;
        Parcel zza = zza(25, zzaz());
        IBinder readStrongBinder = zza.readStrongBinder();
        if (readStrongBinder == null) {
            zzbxVar = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
            zzbxVar = queryLocalInterface instanceof IUiSettingsDelegate ? (IUiSettingsDelegate) queryLocalInterface : new zzbx(readStrongBinder);
        }
        zza.recycle();
        return zzbxVar;
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final boolean isBuildingsEnabled() throws RemoteException {
        Parcel zza = zza(40, zzaz());
        boolean zza2 = zzef.zza(zza);
        zza.recycle();
        return zza2;
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final boolean isIndoorEnabled() throws RemoteException {
        Parcel zza = zza(19, zzaz());
        boolean zza2 = zzef.zza(zza);
        zza.recycle();
        return zza2;
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final boolean isMyLocationEnabled() throws RemoteException {
        Parcel zza = zza(21, zzaz());
        boolean zza2 = zzef.zza(zza);
        zza.recycle();
        return zza2;
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final boolean isTrafficEnabled() throws RemoteException {
        Parcel zza = zza(17, zzaz());
        boolean zza2 = zzef.zza(zza);
        zza.recycle();
        return zza2;
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void moveCamera(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, iObjectWrapper);
        zzb(4, zzaz);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void onCreate(Bundle bundle) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, bundle);
        zzb(54, zzaz);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void onDestroy() throws RemoteException {
        zzb(57, zzaz());
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void onEnterAmbient(Bundle bundle) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, bundle);
        zzb(81, zzaz);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void onExitAmbient() throws RemoteException {
        zzb(82, zzaz());
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void onLowMemory() throws RemoteException {
        zzb(58, zzaz());
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void onPause() throws RemoteException {
        zzb(56, zzaz());
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void onResume() throws RemoteException {
        zzb(55, zzaz());
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void onSaveInstanceState(Bundle bundle) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, bundle);
        Parcel zza = zza(60, zzaz);
        if (zza.readInt() != 0) {
            bundle.readFromParcel(zza);
        }
        zza.recycle();
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void onStart() throws RemoteException {
        zzb(101, zzaz());
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void onStop() throws RemoteException {
        zzb(102, zzaz());
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void resetMinMaxZoomPreference() throws RemoteException {
        zzb(94, zzaz());
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setBuildingsEnabled(boolean z) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, z);
        zzb(41, zzaz);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setContentDescription(String str) throws RemoteException {
        Parcel zzaz = zzaz();
        zzaz.writeString(str);
        zzb(61, zzaz);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final boolean setIndoorEnabled(boolean z) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, z);
        Parcel zza = zza(20, zzaz);
        boolean zza2 = zzef.zza(zza);
        zza.recycle();
        return zza2;
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setInfoWindowAdapter(zzh zzhVar) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, zzhVar);
        zzb(33, zzaz);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setLatLngBoundsForCameraTarget(LatLngBounds latLngBounds) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, latLngBounds);
        zzb(95, zzaz);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setLocationSource(ILocationSourceDelegate iLocationSourceDelegate) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, iLocationSourceDelegate);
        zzb(24, zzaz);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final boolean setMapStyle(MapStyleOptions mapStyleOptions) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, mapStyleOptions);
        Parcel zza = zza(91, zzaz);
        boolean zza2 = zzef.zza(zza);
        zza.recycle();
        return zza2;
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setMapType(int i) throws RemoteException {
        Parcel zzaz = zzaz();
        zzaz.writeInt(i);
        zzb(16, zzaz);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setMaxZoomPreference(float f) throws RemoteException {
        Parcel zzaz = zzaz();
        zzaz.writeFloat(f);
        zzb(93, zzaz);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setMinZoomPreference(float f) throws RemoteException {
        Parcel zzaz = zzaz();
        zzaz.writeFloat(f);
        zzb(92, zzaz);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setMyLocationEnabled(boolean z) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, z);
        zzb(22, zzaz);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnCameraChangeListener(zzl zzlVar) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, zzlVar);
        zzb(27, zzaz);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnCameraIdleListener(zzn zznVar) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, zznVar);
        zzb(99, zzaz);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnCameraMoveCanceledListener(zzp zzpVar) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, zzpVar);
        zzb(98, zzaz);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnCameraMoveListener(zzr zzrVar) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, zzrVar);
        zzb(97, zzaz);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnCameraMoveStartedListener(zzt zztVar) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, zztVar);
        zzb(96, zzaz);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnCircleClickListener(zzv zzvVar) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, zzvVar);
        zzb(89, zzaz);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnGroundOverlayClickListener(zzx zzxVar) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, zzxVar);
        zzb(83, zzaz);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnIndoorStateChangeListener(zzz zzzVar) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, zzzVar);
        zzb(45, zzaz);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnInfoWindowClickListener(zzab zzabVar) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, zzabVar);
        zzb(32, zzaz);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnInfoWindowCloseListener(zzad zzadVar) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, zzadVar);
        zzb(86, zzaz);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnInfoWindowLongClickListener(zzaf zzafVar) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, zzafVar);
        zzb(84, zzaz);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnMapClickListener(zzaj zzajVar) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, zzajVar);
        zzb(28, zzaz);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnMapLoadedCallback(zzal zzalVar) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, zzalVar);
        zzb(42, zzaz);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnMapLongClickListener(zzan zzanVar) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, zzanVar);
        zzb(29, zzaz);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnMarkerClickListener(zzar zzarVar) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, zzarVar);
        zzb(30, zzaz);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnMarkerDragListener(zzat zzatVar) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, zzatVar);
        zzb(31, zzaz);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnMyLocationButtonClickListener(zzav zzavVar) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, zzavVar);
        zzb(37, zzaz);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnMyLocationChangeListener(zzax zzaxVar) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, zzaxVar);
        zzb(36, zzaz);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnMyLocationClickListener(zzaz zzazVar) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, zzazVar);
        zzb(107, zzaz);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnPoiClickListener(zzbb zzbbVar) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, zzbbVar);
        zzb(80, zzaz);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnPolygonClickListener(zzbd zzbdVar) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, zzbdVar);
        zzb(85, zzaz);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setOnPolylineClickListener(zzbf zzbfVar) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, zzbfVar);
        zzb(87, zzaz);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setPadding(int i, int i2, int i3, int i4) throws RemoteException {
        Parcel zzaz = zzaz();
        zzaz.writeInt(i);
        zzaz.writeInt(i2);
        zzaz.writeInt(i3);
        zzaz.writeInt(i4);
        zzb(39, zzaz);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setTrafficEnabled(boolean z) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, z);
        zzb(18, zzaz);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void setWatermarkEnabled(boolean z) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, z);
        zzb(51, zzaz);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void snapshot(zzbs zzbsVar, IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, zzbsVar);
        zzef.zza(zzaz, iObjectWrapper);
        zzb(38, zzaz);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void snapshotForTest(zzbs zzbsVar) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, zzbsVar);
        zzb(71, zzaz);
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final void stopAnimation() throws RemoteException {
        zzb(8, zzaz());
    }

    @Override // com.google.android.gms.maps.internal.IGoogleMapDelegate
    public final boolean useViewLifecycleWhenInFragment() throws RemoteException {
        Parcel zza = zza(59, zzaz());
        boolean zza2 = zzef.zza(zza);
        zza.recycle();
        return zza2;
    }
}
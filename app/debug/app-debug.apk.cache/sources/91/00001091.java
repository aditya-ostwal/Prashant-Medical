package com.google.android.gms.maps.model.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.maps.model.Tile;

/* loaded from: classes.dex */
public interface zzz extends IInterface {
    Tile getTile(int i, int i2, int i3) throws RemoteException;
}
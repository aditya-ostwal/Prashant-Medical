package com.shoeARstore;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

/* loaded from: classes9.dex */
public class localdatabase {
    private static final String CREATE_TABLO_CART = "CREATE TABLE cart (_id INTEGER PRIMARY KEY AUTOINCREMENT , stockcode TEXT NOT NULL , piece TEXT NOT NULL , size TEXT NOT NULL);";
    private static final String CREATE_TABLO_FAV = "CREATE TABLE fav (_id INTEGER PRIMARY KEY AUTOINCREMENT , stockcode TEXT NOT NULL , size TEXT NOT NULL);";
    private static final String DATABASE_ISIM = "cartlist";
    private static final String DATABASE_TABLO = "cart";
    private static final String DATABASE_TABLO2 = "fav";
    private static final int DATABASE_VERSION = 1;
    public static final String KEY_ROW_ID = "_id";
    public static final String KEY_ROW_PIECE = "piece";
    public static final String KEY_ROW_SHOESIZE = "size";
    public static final String KEY_ROW_STOCKCODE = "stockcode";
    private final Context contextim;
    private VeritabaniHelper veritabaniHelper;
    private SQLiteDatabase veritabanim;

    public localdatabase(Context c) {
        this.contextim = c;
    }

    public localdatabase baglantiAc() {
        VeritabaniHelper veritabaniHelper = new VeritabaniHelper(this.contextim);
        this.veritabaniHelper = veritabaniHelper;
        this.veritabanim = veritabaniHelper.getWritableDatabase();
        return this;
    }

    public void baglantiKapa() {
        this.veritabaniHelper.close();
    }

    public void favKaydet(String stockcode, String size) {
        ContentValues cv = new ContentValues();
        cv.put(KEY_ROW_STOCKCODE, stockcode);
        cv.put(KEY_ROW_SHOESIZE, size);
        this.veritabanim.insert(DATABASE_TABLO2, null, cv);
    }

    public ArrayList<String> favoritesKayitListele() {
        ArrayList<String> veriler = new ArrayList<>();
        String[] sutunlar = {KEY_ROW_ID, KEY_ROW_STOCKCODE, KEY_ROW_SHOESIZE};
        Cursor c = this.veritabanim.query(DATABASE_TABLO2, sutunlar, null, null, null, null, null);
        int id = c.getColumnIndex(KEY_ROW_ID);
        int stockcodeid = c.getColumnIndex(KEY_ROW_STOCKCODE);
        int sizeid = c.getColumnIndex(KEY_ROW_SHOESIZE);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            veriler.add(c.getString(id) + "/" + c.getString(stockcodeid) + "/" + c.getString(sizeid));
            c.moveToNext();
        }
        return veriler;
    }

    public boolean favSil(String stockcode) {
        if (!stockcode.equals("")) {
            this.veritabanim.delete(DATABASE_TABLO2, "stockcode=" + stockcode, null);
            return true;
        }
        return false;
    }

    public int itemKontrol(String stockcode) {
        Cursor cursor = this.veritabanim.rawQuery("SELECT * FROM fav WHERE stockcode = " + stockcode, null);
        return cursor.getCount();
    }

    public int favItemCount() {
        long count = DatabaseUtils.queryNumEntries(this.veritabanim, DATABASE_TABLO2);
        return (int) count;
    }

    public int cartItemCount() {
        long count = DatabaseUtils.queryNumEntries(this.veritabanim, DATABASE_TABLO);
        return (int) count;
    }

    public void cartKaydet(String stockcode, String piece, String size) {
        ContentValues cv = new ContentValues();
        cv.put(KEY_ROW_STOCKCODE, stockcode);
        cv.put(KEY_ROW_PIECE, piece);
        cv.put(KEY_ROW_SHOESIZE, size);
        this.veritabanim.insert(DATABASE_TABLO, null, cv);
    }

    public ArrayList<String> cartKayitlariListele() {
        ArrayList<String> veriler = new ArrayList<>();
        String[] sutunlar = {KEY_ROW_ID, KEY_ROW_STOCKCODE, KEY_ROW_PIECE, KEY_ROW_SHOESIZE};
        Cursor c = this.veritabanim.query(DATABASE_TABLO, sutunlar, null, null, null, null, null);
        int id = c.getColumnIndex(KEY_ROW_ID);
        int stockcodeid = c.getColumnIndex(KEY_ROW_STOCKCODE);
        int pieceid = c.getColumnIndex(KEY_ROW_PIECE);
        int sizeid = c.getColumnIndex(KEY_ROW_SHOESIZE);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            veriler.add(c.getString(id) + "/" + c.getString(stockcodeid) + "/" + c.getString(pieceid) + "/" + c.getString(sizeid));
            c.moveToNext();
        }
        return veriler;
    }

    public boolean sil(int itemId) {
        if (itemId != -1) {
            this.veritabanim.delete(DATABASE_TABLO, "_id=" + itemId, null);
            return true;
        }
        return false;
    }

    public void veriGuncelle(String stockcode, String piece, int index) {
        ContentValues cv = new ContentValues();
        cv.put(KEY_ROW_STOCKCODE, stockcode);
        cv.put(KEY_ROW_PIECE, piece);
        this.veritabanim.update(DATABASE_TABLO, cv, "_id=" + index, null);
    }

    public void tabloSil() {
        this.veritabanim.delete(DATABASE_TABLO, null, null);
    }

    /* loaded from: classes9.dex */
    private static class VeritabaniHelper extends SQLiteOpenHelper {
        public VeritabaniHelper(Context context) {
            super(context, localdatabase.DATABASE_ISIM, (SQLiteDatabase.CursorFactory) null, 1);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(localdatabase.CREATE_TABLO_CART);
            sqLiteDatabase.execSQL(localdatabase.CREATE_TABLO_FAV);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS cart");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS fav");
            onCreate(sqLiteDatabase);
        }
    }
}
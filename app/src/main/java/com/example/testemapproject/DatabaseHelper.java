package com.example.testemapproject;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    //private static final String TAG = "DatabaseHelper";

    private static final String TABLE_NAME = "locale_configs";
    // declaração de colunas da tabela:
    private static final String COL0 = "ID";
    private static final String COL1 = "CONFIG_NAME";
    private static final String COL2 = "LAT";
    private static final String COL3 = "LONGI";
    private static final String COL4 = "ZOOM";
    private static final String COL5 = "WIFI";
    private static final String COL6 = "MEDIA";
    private static final String COL7 = "RING";
    private static final String COL8 = "ALARM";

    public DatabaseHelper (Context context){  super(context, TABLE_NAME, null, 1); }

    @Override
    public void onCreate(SQLiteDatabase db){
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL1 + " TEXT,"
                + COL2 + " REAL,"
                + COL3 + " REAL,"
                + COL4 + " INTEGER,"
                + COL5 + " INTEGER,"
                + COL6 + " INTERGER,"
                + COL7 + " INTERGER,"
                + COL8 + " INTEGER)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int il){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String configName, Double lat, Double longi,
                           int zoom, int wifi, int media, int ring, int alarm){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, configName);
        contentValues.put(COL2, lat);
        contentValues.put(COL3, longi);
        contentValues.put(COL4, zoom);
        contentValues.put(COL5, wifi);
        contentValues.put(COL6, media);
        contentValues.put(COL7, ring);
        contentValues.put(COL8, alarm);

        long result = db.insert(TABLE_NAME,null, contentValues);

        if (result == -1){
            return false;
        }else{
            return true;
        }
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }
    /*TODO
     getConfig()
     updateConfig()
     deleteConfig()
    */
}

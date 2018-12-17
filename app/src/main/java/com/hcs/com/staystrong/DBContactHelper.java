package com.hcs.com.staystrong;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBContactHelper extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "stay_strong";

    // Contacts table name
    private static final String TABLE_CONTACTS = "stay_strong";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String YEAR = "year";
    private static final String MONTH = "month";
    private static final String DAY = "day";

    public DBContactHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // 테이블 생성
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " VARCHAR(255),"
                + YEAR + " VARCHAR(4), " + MONTH + " VARCHAR(2), " + DAY + " VARCHAR(2) )";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    //DB의 버전이 올라갈때 호출된다.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }



    //데이터를 DB에 입력
    public void insert(String name, String year, String month, String day) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name);
        values.put(YEAR, year);
        values.put(MONTH, month);
        values.put(DAY, day);

        db.insert(TABLE_CONTACTS, null, values);
        db.close();
    }

    // 해당 날짜의 대한 데이터를 가져온다.
    public  List<String> get(String year, String month, String day) {
        List<String> results = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACTS, new String[] {
                        KEY_NAME}, YEAR + "=? AND " + MONTH + "=? AND " + DAY + "=?",
                new String[] { year, month, day }, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                //가져오는 항목은 name 하나뿐이기 때문에 0번째 index값을 가져온다.
                results.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return results;
    }
}

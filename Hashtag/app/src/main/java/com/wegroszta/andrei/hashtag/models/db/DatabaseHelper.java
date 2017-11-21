package com.wegroszta.andrei.hashtag.models.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.wegroszta.andrei.hashtag.models.db.tables.HashtagTable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "database.db";
    public static final int DB_VERSION = 1;
    private static final String CREATE_TABLE = "create table " + HashtagTable.class.getSimpleName() + "(" + HashtagTable.ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + HashtagTable.HASHTAG_NAME + " TEXT NOT NULL);";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + HashtagTable.class.getSimpleName());
        onCreate(sqLiteDatabase);
    }
}

package com.wegroszta.andrei.hashtag.models.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.wegroszta.andrei.hashtag.entities.Hashtag;
import com.wegroszta.andrei.hashtag.models.HashtagStorage;
import com.wegroszta.andrei.hashtag.models.db.tables.HashtagTable;

import java.util.ArrayList;
import java.util.List;

public class SqliteHashtagStorage implements HashtagStorage {

    private DatabaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public SqliteHashtagStorage(Context context) {
        this.context = context;
    }

    private void open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    private void close() {
        if (database != null) {
            dbHelper.close();
        }
    }

    @Override
    public long createHashtag(Hashtag hashtag) {
        open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(HashtagTable.HASHTAG_NAME.toString(), hashtag.getName());
        long id = database.insert(HashtagTable.class.getSimpleName(), null, contentValues);
        close();
        return id;
    }

    @Override
    public List<Hashtag> readHashtags() {
        open();
        String[] columns = new String[]{HashtagTable.ID.toString(), HashtagTable.HASHTAG_NAME.toString()};
        Cursor cursor = database.query(HashtagTable.class.getSimpleName(), columns, null, null, null, null, null);

        List<Hashtag> hashtags = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                Hashtag hashtag = new Hashtag();
                hashtag.setId(cursor.getInt((cursor.getColumnIndex(HashtagTable.ID.toString()))));
                hashtag.setName((cursor.getString(cursor.getColumnIndex(HashtagTable.HASHTAG_NAME.toString()))));
                hashtags.add(hashtag);
            } while (cursor.moveToNext());
        }

        cursor.close();
        close();
        return hashtags;
    }

    @Override
    public long updateHashtag(Hashtag hashtag) {
        open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(HashtagTable.HASHTAG_NAME.toString(), hashtag.getName());
        long id = database.update(HashtagTable.class.getSimpleName(), contentValues,
                HashtagTable.ID + " = " + hashtag.getId(), null);
        close();
        return id;
    }

    @Override
    public long deleteHashtag(Hashtag hashtag) {
        open();
        long id = database.delete(HashtagTable.class.getSimpleName(), HashtagTable.ID + "=" + hashtag.getId(), null);
        close();
        return id;
    }
}

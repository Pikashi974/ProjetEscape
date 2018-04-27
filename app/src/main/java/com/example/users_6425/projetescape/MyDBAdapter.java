package com.example.users_6425.projetescape;

import android.content.*;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class MyDBAdapter {
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "score_database.db";
    public static final String COL_ID = "_id";
    public static final String COL_NAME = "name";
    public static final String COL_SCORE = "score";
    private static final String TABLE_SCORES = "table_scores";
    private static final String CREATE_DB = "CREATE TABLE if not EXISTS " + TABLE_SCORES + " ("
            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_NAME + " TEXT NOT NULL, "
            + COL_SCORE + " INTEGER);";
    private SQLiteDatabase mDB;
    private MyOpenHelper mOpenHelper;

    public MyDBAdapter(Context context) {
        mOpenHelper = new MyOpenHelper(context, DB_NAME,
                null, DB_VERSION);
    }

    public void open() {
        mDB = mOpenHelper.getWritableDatabase();
    }

    public void close() {
        mDB.close();
    }

    public Scores getScore(long id) throws SQLException {
        Scores cl = null;
        Cursor c = mDB.query(TABLE_SCORES,
                new String[]{COL_ID, COL_NAME, COL_SCORE},
                COL_ID + " = " + id, null, null, null, null);
        if (c.getCount() > 0) {
            c.moveToFirst();
            cl = new Scores(c.getLong(0), c.getString(1), c.getInt(2));
        }
        c.close();
        return cl;
    }

    public ArrayList<Scores> getAllScores() {
        ArrayList<Scores> clients = new ArrayList<>();
        Cursor c = mDB.query(TABLE_SCORES,
                new String[]{COL_ID, COL_NAME, COL_SCORE},
                null, null, null, null, null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            clients.add(new Scores(
                    c.getLong(0), c.getString(1), c.getInt(2)));
            c.moveToNext();
        }
        c.close();
        return clients;
    }

    public long insertScore(String name, int score) {
        ContentValues values = new ContentValues();
        values.put(COL_NAME, name);
        values.put(COL_SCORE, score);
        //values.put(COL_TAB, tab);
        return mDB.insert(TABLE_SCORES, null, values);
    }


    public int updateScore(long id, String name, int score) {
        ContentValues values = new ContentValues();
        values.put(COL_NAME, name);
        values.put(COL_SCORE, score);
        //values.put(COL_TAB, tab);
        return mDB.update(TABLE_SCORES, values, COL_ID + "=" + id, null);
    }


    public int removeScore(long id) {
        return mDB.delete(TABLE_SCORES, COL_ID + " = " + id, null);
    }


    private class MyOpenHelper extends SQLiteOpenHelper {
        public MyOpenHelper(Context context, String name,
                            SQLiteDatabase.CursorFactory cursorFactory, int version) {
            super(context, name, cursorFactory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_DB);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db,
                              int oldVersion, int newVersion) {
            db.execSQL("drop table " + TABLE_SCORES + ";");
            onCreate(db);
        }
    }
} // fin de la classe MyDBAdapter
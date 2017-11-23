package com.example.padster.betterpicross;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by padster on 06/11/17.
 */

public class LevelsDB {

    //database constraints
    public static final String DB_NAME = "levels.db";
    public static final int DB_VERSION = 1;


    //levels table constraints
    public static final String LEVELS_TABLE = "levels";

    public static final String LEVELS_ID = "_id";
    public static final int LEVELS_ID_COL = 0;

    public static final String LEVELS_CLUES = "clues";
    public static final int LEVELS_CLUES_COL = 1;

    //sets table constraints
    public static final String SETS_TABLE = "sets";

    public static final String SETS_ID = "_id";
    public static final int SETS_ID_COL = 0;

    //levelsets table constraints
    public static final String LEVELSETS_TABLE = "levelsets";

    public static final String LEVELSETS_ID = "_id";
    public static final int LEVELSETS_ID_COL = 0;

    public static final String LEVELSETS_LEVEL_ID = "level_id";
    public static final int LEVELSETS_LEVEL_ID_COL = 1;

    public static final String LEVELSETS_SET_ID = "set_id";
    public static final int LEVELSETS_SET_ID_COL = 2;

    //create and drop table statements

    public static final String CREATE_LEVELS_TABLE =
        "CREATE TABLE " + LEVELS_TABLE + " (" +
        LEVELS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
        LEVELS_CLUES + " TEXT NOT NULL UNIQUE);";

    public static final String CREATE_SETS_TABLE =
        "CREATE TABLE " + SETS_TABLE + " (" +
        SETS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT);";

    public static final String CREATE_LEVELSETS_TABLE =
        "CREATE TABLE " + LEVELSETS_TABLE + " (" +
        LEVELSETS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
        LEVELSETS_LEVEL_ID + " INTEGER NOT NULL UNIQUE, " +
        LEVELSETS_SET_ID + "INTEGER NOT NULL, " +
        "FOREIGN KEY(" + LEVELSETS_LEVEL_ID + ") REFERENCES " + LEVELS_TABLE + "(" + LEVELS_ID + "), " +
        "FOREIGN KEY(" + LEVELSETS_SET_ID + ") REFERENCES " + SETS_TABLE + "(" + SETS_ID + ");";

    public static final String DROP_LEVELS_TABLE =
        "DROP TABLE IF EXISTS " + LEVELS_TABLE;

    public static final String DROP_SETS_TABLE =
        "DROP TABLE IF EXISTS " + SETS_TABLE;

    public static final String DROP_LEVELSETS_TABLE =
        "DROP TABLE IF EXISTS " + LEVELSETS_TABLE;


    private static class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context, String name,
                        SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_LEVELS_TABLE);
            db.execSQL(CREATE_SETS_TABLE);
            db.execSQL(CREATE_LEVELSETS_TABLE);

            //TODO - insert default stuff...
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int i, int i1) {
            //TODO - this
        }
    }


    //database object and database helper object
    private SQLiteDatabase db;
    private DBHelper dbHelper;

    //constrcutor
    public LevelsDB(Context context) {
        dbHelper = new DBHelper(context, DB_NAME, null, DB_VERSION);
    }


    //private methods

    private void openReadableDB() {
        db = dbHelper.getReadableDatabase();
    }

    private void openWriteableDB() {
        db = dbHelper.getWritableDatabase();
    }

    private void closeDB() {
        if (db != null) {
            db.close();
        }
    }

    //TODO - left off on lecture 11 (ch 13) slide 25
    //should have stuff to read/write here? like the useful methods for the app?
}

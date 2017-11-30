package com.example.padster.betterpicross.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import java.util.List;


/**
 * Created by padster on 06/11/17.
 */

@Database(entities = {Level.class, Pack.class, Score.class},
version = 16, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract PackDao packDao();
    public abstract LevelDao levelDao();
    public abstract ScoreDao scoreDao();

    public static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, AppDatabase.class, "levelsdatabase")
                    //TODO - what to do with this stuff?
                //Room.inMemoryDatabaseBuilder(context.getApplicationContext(), AppDatabase.class)
                // To simplify the exercise, allow queries on the main thread.
                // Don't do this on a real app!
                .allowMainThreadQueries()
                // recreate the database if necessary
                .fallbackToDestructiveMigration()
                .build();
        }

        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}

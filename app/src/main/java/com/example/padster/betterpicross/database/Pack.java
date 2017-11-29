package com.example.padster.betterpicross.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;


/**
 * Created by padster on 06/11/17.
 */

@Entity
public class Pack {
    @PrimaryKey
    public final int id;
    public String name;

    public Pack(int id, String name) {
        this.id = id;
        this.name = name;
    }

}

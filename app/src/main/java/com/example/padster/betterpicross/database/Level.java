package com.example.padster.betterpicross.database;

import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.content.Context;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;

/**
 * Created by padster on 06/11/17.
 */

@Entity(tableName = "Level",
foreignKeys = {
        @ForeignKey(
                entity = Pack.class,
                parentColumns = "id",
                childColumns = "packId",
                onDelete = ForeignKey.CASCADE
        )},
    indices = { @Index(value = "id")}
)
public class Level {
    @PrimaryKey
    public final int id;
    public String clues;
    public int packId;

    public Level(int id, String clues, int packId) {
        this.id = id;
        this.clues = clues;
        this.packId = packId;
    }

}


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

@Entity(tableName = "Score",
foreignKeys = {
        @ForeignKey(
                entity = Level.class,
                parentColumns = "id",
                childColumns = "levelId",
                onDelete = ForeignKey.CASCADE
        )},
    indices = { @Index(value = "id")}
)
public class Score {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String time;
    public int levelId;

    public Score(String time, int levelId) {
        this.time = time;
        this.levelId = levelId;
    }

}


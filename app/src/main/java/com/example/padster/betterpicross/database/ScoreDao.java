package com.example.padster.betterpicross.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;


/**
 * Created by padster on 06/11/17.
 */

@Dao
public interface ScoreDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addScore(Score score);

    @Query("select * from score")
    public List<Score> getAllScores();

    @Query("select * from score where levelId = :levelId")
    public List<Score> getScoresForLevel(int levelId);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateLevel(Score score);

    @Query("delete from score where id = :id")
    void delete(int id);
}

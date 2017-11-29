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
public interface LevelDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addLevel(Level level);

    @Query("select * from level")
    public List<Level> getAllLevels();

    @Query("select * from level where id = :levelId")
    public List<Level> getLevel(int levelId);

    @Query("select * from level where packId = :packId")
    public List<Level> getLevelsInPack(int packId);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateLevel(Level level);

    @Query("delete from level where id = :id")
    void delete(int id);
}

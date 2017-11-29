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
public interface PackDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addPack(Pack pack);

    @Query("select * from pack")
    public List<Pack> getAllPacks();

    @Query("select * from pack where id = :packId")
    public List<Pack> getPack(int packId);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updatePack(Pack pack);

    @Query("delete from pack")
    void removeAllPacks();
}

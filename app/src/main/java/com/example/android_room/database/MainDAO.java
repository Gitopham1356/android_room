package com.example.android_room.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MainDAO {

    // Insert query
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(MainData mainData);

    // Delete query
    @Delete
    void delete(MainData mainData);

    //Delete all query
    @Delete
    void reset(List<MainData> mainData);

    // Update query
    @Query("Update table_name Set name = :sText, class = :sText2  Where id = :sID")
    void update(int sID, String sText, String sText2);

    // Get all data query
    @Query("Select * From table_name Order By id")
    List<MainData> getAllData();

}

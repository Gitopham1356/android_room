package com.example.android_room.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

// Add database entities
@Database(entities = {MainData.class}, version = 1 , exportSchema = true)
public abstract class RoomDB extends RoomDatabase {

    // Create database instance
    private static RoomDB database;

    // Define database name
    private static final String DATABASE_NAME ="room.db";

    public synchronized static RoomDB getInstance(Context context){

        // Check condition
        if (database == null){

            //When database is null
            // Initialized database
            database = Room.databaseBuilder(context.getApplicationContext()
                    , RoomDB.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();

        }
        //Return database
        return database;
    }
    public abstract MainDAO mainDAO();
}

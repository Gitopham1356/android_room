package com.example.android_room.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Define table
@Getter
@Setter
@NoArgsConstructor
@Entity(tableName = "table_name")
public class MainData implements Serializable{

        // Create id column
        @PrimaryKey(autoGenerate = true)
        private int id;

        // Create text column
        @ColumnInfo(name = "name")
        private String name;

        @ColumnInfo(name = "class")
        private String name_class;


}


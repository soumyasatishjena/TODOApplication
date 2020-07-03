package com.example.todoapplication.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.todoapplication.pojo.TodoData;

/* ------------------------------------------------------------- *
 * Used for creating the schema for the Room database
 * ------------------------------------------------------------- */

@Database(entities = TodoData.class, version = 1, exportSchema = false)
public abstract class ToDoDatabase extends RoomDatabase {
    public abstract TodoDao todoDao();
}

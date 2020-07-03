package com.example.todoapplication.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.todoapplication.pojo.TodoData;

import java.util.List;

/* ------------------------------------------------------------- *
 * Interface with function used to do operation Room database
 * ------------------------------------------------------------- */
@Dao
public interface TodoDao {

    /* ------------------------------------------------------------- *
     * Used for inserting data to the database
     * ------------------------------------------------------------- */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(TodoData toDoData);

    /* ------------------------------------------------------------- *
     * Used for fetching the value from the database
     * ------------------------------------------------------------- */
    @Query("SELECT * FROM todo Order By id ASC")
    LiveData<List<TodoData>> getToDoList();
}

package com.example.todoapplication.di.module;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.room.Room;

import com.example.todoapplication.database.ToDoDatabase;
import com.example.todoapplication.database.TodoDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/* ------------------------------------------------------------- *
 * It is use to crete the dependency for the database
 * ------------------------------------------------------------- */
@Module
public class DatabaseModule {

    /* ------------------------------------------------------------- *
     * Function is called once to crete the data base for the project
     * ------------------------------------------------------------- */
    @Provides
    @Singleton
    ToDoDatabase provideDatabase(@NonNull Application application) {
        return Room.databaseBuilder(application,
                ToDoDatabase.class, "todo_database")
                .allowMainThreadQueries().build();
    }

    /* ------------------------------------------------------------- *
     * Function used for returning the Dao for accessing the database
     * ------------------------------------------------------------- */
    @Provides
    @Singleton
    TodoDao provideUserDao(@NonNull ToDoDatabase toDoDatabase) {
        return toDoDatabase.todoDao();
    }
}

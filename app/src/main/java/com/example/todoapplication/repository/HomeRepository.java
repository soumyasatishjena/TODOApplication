package com.example.todoapplication.repository;

import androidx.lifecycle.LiveData;

import com.example.todoapplication.database.TodoDao;
import com.example.todoapplication.pojo.TodoData;

import java.util.List;

import javax.inject.Singleton;

/* ------------------------------------------------------------- *
 * Repository Class/ Model Class for Home
 * ------------------------------------------------------------- */
@Singleton
public class HomeRepository {

    /* ------------------------------------------------------------- *
     * Variable Declaration and initialization
     * ------------------------------------------------------------- */
    private TodoDao userDao;

    public HomeRepository(TodoDao toDoDao) {
        this.userDao = toDoDao;
    }

    /* ------------------------------------------------------------- *
     * Function is used to fetch the data from the Database
     * ------------------------------------------------------------- */
    public LiveData<List<TodoData>> getTodoList() {
        return userDao.getToDoList();
    }

}

package com.example.todoapplication.repository;

import android.os.AsyncTask;

import com.example.todoapplication.database.TodoDao;
import com.example.todoapplication.pojo.TodoData;

import javax.inject.Singleton;

/* ------------------------------------------------------------- *
 * Repository Class/ Model Class for AddNewData
 * ------------------------------------------------------------- */
@Singleton
public class AddNewRepository {

    /* ------------------------------------------------------------- *
     * Variable Declaration and initialization
     * ------------------------------------------------------------- */
    private TodoDao toDoDao;

    public AddNewRepository(TodoDao toDoDao) {
        this.toDoDao = toDoDao;
    }

    /* ------------------------------------------------------------- *
     * Function is used to store the data to the Database
     * ------------------------------------------------------------- */
    public void insert(TodoData userData) {
        new InsertAsyncTask(toDoDao).execute(userData);
    }

    private static class OperationsAsyncTask extends AsyncTask<TodoData, Void, Void> {

        TodoDao mAsyncTaskDao;

        OperationsAsyncTask(TodoDao dao) {
            this.mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(TodoData... toDoData) {
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }

    private static class InsertAsyncTask extends OperationsAsyncTask {

        InsertAsyncTask(TodoDao toDoDao) {
            super(toDoDao);
        }

        @Override
        protected Void doInBackground(TodoData... toDoData) {
            mAsyncTaskDao.insert(toDoData[0]);
            return null;
        }
    }
}

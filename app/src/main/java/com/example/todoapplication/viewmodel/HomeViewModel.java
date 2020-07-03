package com.example.todoapplication.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.todoapplication.database.TodoDao;
import com.example.todoapplication.pojo.TodoData;
import com.example.todoapplication.repository.HomeRepository;

import java.util.List;

import javax.inject.Inject;

/* ------------------------------------------------------------- *
 * View Model Class
 * ------------------------------------------------------------- */
public class HomeViewModel extends ViewModel {

    /* ------------------------------------------------------------- *
     * Variable Declaration
     * ------------------------------------------------------------- */
    private HomeRepository homeRepository;

    /* ------------------------------------------------------------- *
     * Injecting the dependency that is required
     * ------------------------------------------------------------- */
    @Inject
    HomeViewModel(TodoDao toDoDao) {
        homeRepository = new HomeRepository(toDoDao);
    }

    /* ------------------------------------------------------------- *
     * Function is used to fetch the data from the database.
     * ------------------------------------------------------------- */
    public LiveData<List<TodoData>> getTodoListData() {
        return homeRepository.getTodoList();
    }

    /* ------------------------------------------------------------- *
     * Dispose the home repository when view model is not active
     * ------------------------------------------------------------- */
    @Override
    public void onCleared() {
        homeRepository = null;
        super.onCleared();
    }
}

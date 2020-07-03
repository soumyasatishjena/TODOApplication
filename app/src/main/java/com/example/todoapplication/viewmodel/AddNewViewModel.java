package com.example.todoapplication.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.todoapplication.database.TodoDao;
import com.example.todoapplication.pojo.TodoData;
import com.example.todoapplication.repository.AddNewRepository;

import javax.inject.Inject;

/* ------------------------------------------------------------- *
 * View Model Class for the AddNewData
 * ------------------------------------------------------------- */
public class AddNewViewModel extends ViewModel {

    private AddNewRepository addNewRepository;

    @Inject
    AddNewViewModel(TodoDao toDoDao) {
        addNewRepository = new AddNewRepository(toDoDao);
    }

    public void insertData(TodoData toDoData) {
        addNewRepository.insert(toDoData);
    }

    /* ------------------------------------------------------------- *
     * dispose the add new repository when view model is not active
     * ------------------------------------------------------------- */
    @Override
    public void onCleared() {
        addNewRepository = null;
        super.onCleared();
    }
}

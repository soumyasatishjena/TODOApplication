package com.example.todoapplication.di.module;


import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.todoapplication.di.ViewModelKey;
import com.example.todoapplication.factory.ViewModelFactory;
import com.example.todoapplication.viewmodel.AddNewViewModel;
import com.example.todoapplication.viewmodel.HomeViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/* ------------------------------------------------------------- *
 * Abstract class used for creating the viewModel dependency through
 * out the project in the run time
 * ------------------------------------------------------------- */
@Module
public abstract class ViewModelModule {
    /* ------------------------------------------------------------- *
     * used for binding the view model factories for viewModels
     * ------------------------------------------------------------- */
    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);

    /* ------------------------------------------------------------- *
     * For creating the dependency of the HomeViewModel and bind it
     * to ViewModel Modules
     * ------------------------------------------------------------- */
    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel.class)
    protected abstract ViewModel homeViewModel(HomeViewModel homeViewModel);

    /* ------------------------------------------------------------- *
     * For Creating the dependency of the AddNewViewModel and bind
     * it to ViewModel Modules
     * ------------------------------------------------------------- */
    @Binds
    @IntoMap
    @ViewModelKey(AddNewViewModel.class)
    protected abstract ViewModel addNewViewModel(AddNewViewModel addNewViewModel);
}
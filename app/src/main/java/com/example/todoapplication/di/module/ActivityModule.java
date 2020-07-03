package com.example.todoapplication.di.module;

import com.example.todoapplication.view.activity.AddNewActivity;
import com.example.todoapplication.view.activity.HomeActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/* ------------------------------------------------------------- *
 * Abstract class used for creating the Activity dependency
 * ------------------------------------------------------------- */
@Module
public abstract class ActivityModule {

    /* ------------------------------------------------------------- *
     * It shows which activity should the dependency to be created.
     * ------------------------------------------------------------- */
    @ContributesAndroidInjector()
    abstract HomeActivity homeActivity();

    @ContributesAndroidInjector
    abstract AddNewActivity addNewActivity();
}
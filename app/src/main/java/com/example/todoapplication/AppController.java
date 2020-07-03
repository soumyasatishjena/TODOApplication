package com.example.todoapplication;

import android.app.Activity;
import android.app.Application;

import com.example.todoapplication.di.component.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/* ------------------------------------------------------------- *
 * Creating the dependency for the App
 * ------------------------------------------------------------- */
public class AppController extends Application implements HasActivityInjector {

    /* ------------------------------------------------------------- *
     * Used to crete the dependency for the activity and fragment
     *  associated in the App.
     * ------------------------------------------------------------- */
    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }

    /* ------------------------------------------------------------- *
     * Dagger is used to crete the application dependency on the
     * application is created and it is called only once.
     * ------------------------------------------------------------- */
    @Override
    public void onCreate() {
        super.onCreate();
        DaggerAppComponent.builder().application(this).build().inject(this);
    }
}

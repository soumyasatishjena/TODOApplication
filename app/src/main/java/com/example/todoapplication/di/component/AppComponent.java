package com.example.todoapplication.di.component;

import android.app.Application;

import com.example.todoapplication.AppController;
import com.example.todoapplication.di.module.ActivityModule;
import com.example.todoapplication.di.module.DatabaseModule;
import com.example.todoapplication.di.module.ViewModelModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;


/* ------------------------------------------------------------- *
 * Singleton class used to create the dependency for other classes present in the module
 * ------------------------------------------------------------- */
@Component(modules = {
        DatabaseModule.class,
        ViewModelModule.class,
        ActivityModule.class,
        AndroidSupportInjectionModule.class})
@Singleton
public interface AppComponent {

    /* ------------------------------------------------------------- *
     * Interface is used for building the dependency of the application
     * ------------------------------------------------------------- */

    /* ------------------------------------------------------------- *
     * Used for injecting the value to views
     * ------------------------------------------------------------- */
    void inject(AppController appController);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}

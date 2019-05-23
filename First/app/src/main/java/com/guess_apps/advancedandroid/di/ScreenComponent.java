package com.guess_apps.advancedandroid.di;

import com.guess_apps.advancedandroid.lifecycle.DisposableManager;

import dagger.android.AndroidInjector;

public interface ScreenComponent<T> extends AndroidInjector<T> {

    @ForScreen
    DisposableManager disposableManager();
}

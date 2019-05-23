package com.guess_apps.advancedandroid.base;

import com.guess_apps.advancedandroid.di.ForScreen;
import com.guess_apps.advancedandroid.di.ScreenScope;
import com.guess_apps.advancedandroid.lifecycle.DisposableManager;
import com.guess_apps.advancedandroid.lifecycle.ScreenLifecycleTask;

import java.util.Set;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.Multibinds;

@Module
public abstract class ScreenModule {

    @Provides
    @ScreenScope
    @ForScreen
    static DisposableManager provideDisposableManager() {
        return new DisposableManager();
    }

    @Multibinds
    abstract Set<ScreenLifecycleTask> screenLifecycleTasks();
}

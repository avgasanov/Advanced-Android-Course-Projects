package com.guess_apps.advancedandroid.base;

import com.guess_apps.advancedandroid.lifecycle.ScreenLifecycleTask;

import java.util.Set;

import dagger.Module;
import dagger.multibindings.Multibinds;

@Module
public abstract class ScreenModule {

    @Multibinds
    abstract Set<ScreenLifecycleTask> screenLifecycleTasks();
}

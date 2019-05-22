package com.guess_apps.advancedandroid.ui;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class TestActivityViewIntercepterModule {

    @Provides
    static ActivityViewInterceptor provideActivityViewInterceptor() {
        return ActivityViewInterceptor.DEFAULT;
    }
}

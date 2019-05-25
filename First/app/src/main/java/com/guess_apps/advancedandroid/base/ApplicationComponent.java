package com.guess_apps.advancedandroid.base;

import com.guess_apps.advancedandroid.data.RepoServiceModule;
import com.guess_apps.advancedandroid.database.DatabaseModule;
import com.guess_apps.advancedandroid.networking.ServiceModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        ActivityBindingModule.class,
        ServiceModule.class,
        RepoServiceModule.class,
        DatabaseModule.class,
})
public interface ApplicationComponent {
    void inject(MyApplication myApplication);
}

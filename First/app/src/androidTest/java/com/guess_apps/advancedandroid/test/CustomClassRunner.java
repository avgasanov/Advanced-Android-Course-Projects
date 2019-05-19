package com.guess_apps.advancedandroid.test;

import android.app.Application;
import android.content.Context;
import android.support.test.runner.AndroidJUnitRunner;

import com.guess_apps.advancedandroid.base.TestApplication;

public class CustomClassRunner extends AndroidJUnitRunner{

    @Override
    public Application newApplication(ClassLoader cl, String className, Context context)
            throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return super.newApplication(cl, TestApplication.class.getName(), context);
    }
}

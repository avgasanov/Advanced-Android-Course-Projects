package com.guess_apps.advancedandroid.ui;

import android.support.v7.app.AppCompatActivity;

import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.Router;
import com.guess_apps.advancedandroid.lifecycle.ActivityLifeCycleTask;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class TestScreenNavigator extends ActivityLifeCycleTask implements ScreenNavigator{

    private DefaultScreenNavigator defaultScreenNavigator;
    private Controller overrideController;

    @Inject TestScreenNavigator() {
        this.defaultScreenNavigator = new DefaultScreenNavigator();
    }

    public void overrideInitialController(Controller overrideController) {
        this.overrideController = overrideController;
    }

    @Override
    public void onCreate(AppCompatActivity activity) {
        if(!(activity instanceof RouterProvider)) {
            throw new IllegalArgumentException("Activity must be instance of Router Provider");
        }
        Controller launchController = overrideController == null ? ((RouterProvider) activity).initialScreen() : overrideController;
        defaultScreenNavigator.initWithRouter(((RouterProvider) activity).getRouter(), launchController);
    }


    @Override
    public boolean pop() {
        return defaultScreenNavigator.pop();
    }

    @Override
    public void goToRepoDetails(String repoOwner, String repoName) {
        defaultScreenNavigator.goToRepoDetails(repoOwner, repoName);
    }

    @Override
    public void onDestroy(AppCompatActivity activity) {
        defaultScreenNavigator.onDestroy(activity);
    }

}

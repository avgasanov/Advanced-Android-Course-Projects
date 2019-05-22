package com.guess_apps.advancedandroid.ui;

import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.Router;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class TestScreenNavigator implements ScreenNavigator{

    private DefaultScreenNavigator defaultScreenNavigator;
    private Controller overrideController;

    @Inject TestScreenNavigator(DefaultScreenNavigator defaultScreenNavigator) {
        this.defaultScreenNavigator = defaultScreenNavigator;
    }

    public void overrideInitialController(Controller overrideController) {
        this.overrideController = overrideController;
    }

    @Override
    public void initWithRouter(Router router, Controller rootScreen) {
        Controller launchController = overrideController == null ? rootScreen : overrideController;
        defaultScreenNavigator.initWithRouter(router, launchController);
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
    public void clear() {
        defaultScreenNavigator.clear();
    }
}

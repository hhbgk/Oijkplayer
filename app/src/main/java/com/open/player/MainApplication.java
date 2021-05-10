package com.open.player;

import android.app.Application;

public class MainApplication extends Application {
    private static MainApplication sMyApplication = null;


    @Override
    public void onCreate() {
        super.onCreate();
        sMyApplication = this;

    }

    public static MainApplication getApplication() {
        return sMyApplication;
    }
}

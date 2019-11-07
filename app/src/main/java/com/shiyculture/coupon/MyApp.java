package com.shiyculture.coupon;

import android.app.Application;

import com.vondear.rxtool.RxTool;

public class MyApp extends Application {

    public void onCreate() {
        super.onCreate();

        RxTool.init(this);
    }
}

package com.shiyculture.coupon;

import android.app.Application;

import com.tencent.smtt.sdk.QbSdk;
import com.vondear.rxtool.RxTool;

public class MyApp extends Application {

    public void onCreate() {
        super.onCreate();

        RxTool.init(getApplicationContext());

        QbSdk.setDownloadWithoutWifi(true);
        //x5内核初始化接口
        QbSdk.initX5Environment(getApplicationContext(), new QbSdk.PreInitCallback() {
            @Override
            public void onCoreInitFinished() {

            }

            @Override
            public void onViewInitFinished(boolean b) {

            }
        });
    }
}

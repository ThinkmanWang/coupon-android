package com.shiyculture.coupon;

import android.Manifest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.vondear.rxtool.RxDeviceTool;

public class MainActivity extends AppCompatActivity {

    final RxPermissions rxPermissions = new RxPermissions(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
    }

    public void initUI() {
        rxPermissions.request(Manifest.permission.READ_PHONE_STATE)
                .subscribe(granted -> {
                    if (granted) {
                        String szIMEI = RxDeviceTool.getIMEI(MainActivity.this);
                    } else {

                    }
                });
    }
}

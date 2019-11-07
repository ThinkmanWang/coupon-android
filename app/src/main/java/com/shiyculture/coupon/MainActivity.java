package com.shiyculture.coupon;

import android.Manifest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.tencent.smtt.sdk.WebView;
import com.vondear.rxtool.RxDeviceTool;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    final RxPermissions rxPermissions = new RxPermissions(this);

    @BindView(R.id.main_webview)
    WebView m_wvMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initUI();
    }

    public void initUI() {
        rxPermissions.request(Manifest.permission.READ_PHONE_STATE
                , Manifest.permission.WRITE_EXTERNAL_STORAGE
                , Manifest.permission.ACCESS_NETWORK_STATE
                , Manifest.permission.ACCESS_WIFI_STATE)
                .subscribe(granted -> {
                    if (granted) {
                        String szIMEI = RxDeviceTool.getIMEI(MainActivity.this);
                        String szUrl = String.format("https://coupon.shiyculture.com/?user_id=%s&app_key=609d544b98d347a5bcc17637c529b95a&app_secret=609d544b98d347a5bcc17637c529b95a&allow_buy=1", szIMEI);

                        m_wvMain.loadUrl(szUrl);
                    } else {

                    }
                });
    }
}

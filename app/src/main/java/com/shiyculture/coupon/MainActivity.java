package com.shiyculture.coupon;

import android.Manifest;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.vondear.rxtool.RxDeviceTool;

import org.xwalk.core.XWalkNavigationHistory;
import org.xwalk.core.XWalkResourceClient;
import org.xwalk.core.XWalkUIClient;
import org.xwalk.core.XWalkView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    final RxPermissions rxPermissions = new RxPermissions(this);

    @BindView(R.id.main_webview)
    XWalkView mXwalkView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initUI();
    }

    public void onBackPressed() {
        if (mXwalkView.getNavigationHistory().canGoBack()) {
            mXwalkView.getNavigationHistory().navigate(XWalkNavigationHistory.Direction.BACKWARD, 1) ;
        } else {
            super.onBackPressed();
        }
    }

    public void initUI() {
        rxPermissions.request(Manifest.permission.READ_PHONE_STATE
                , Manifest.permission.WRITE_EXTERNAL_STORAGE
                , Manifest.permission.ACCESS_NETWORK_STATE
                , Manifest.permission.ACCESS_WIFI_STATE)
                .subscribe(granted -> {
                    if (granted) {
                        initWebView();
                    } else {

                    }
                });
    }

    public void initWebView() {
        mXwalkView.setUIClient(new MyUIClient(mXwalkView));
        mXwalkView.load("https://coupon.shiyculture.com/?app_key=609d544b98d347a5bcc17637c529b95a&app_secret=609d544b98d347a5bcc17637c529b95a&allow_buy=1",null);
    }

    class MyResourceClient extends XWalkResourceClient {
        MyResourceClient(XWalkView view) {
            super(view);
        }
    }

    class MyUIClient extends XWalkUIClient {
        MyUIClient(XWalkView view) {
            super(view);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mXwalkView != null) {
            mXwalkView.pauseTimers();
            mXwalkView.onHide();
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (mXwalkView != null) {
            mXwalkView.resumeTimers();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mXwalkView != null) {
            mXwalkView.onDestroy();
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (mXwalkView != null) {
            mXwalkView.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onNewIntent(Intent intent) {
        if (mXwalkView != null) {
            mXwalkView.onNewIntent(intent);
        }
    }
}

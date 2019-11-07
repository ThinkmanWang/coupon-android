package com.shiyculture.coupon;

import android.Manifest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
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

    public void onBackPressed() {
        if (m_wvMain.canGoBack()) {
            m_wvMain.goBack();
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
        m_wvMain.getSettings().setJavaScriptEnabled(true);
        m_wvMain.getSettings().setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        m_wvMain.getSettings().setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        m_wvMain.getSettings().setDisplayZoomControls(true); //隐藏原生的缩放控件
        m_wvMain.getSettings().setBlockNetworkImage(false);//解决图片不显示
        m_wvMain.getSettings().setLoadsImagesAutomatically(true); //支持自动加载图片
        m_wvMain.getSettings().setDefaultTextEncodingName("utf-8");//设置编码格式

        String szIMEI = RxDeviceTool.getIMEI(MainActivity.this);
        String szUrl = String.format("https://coupon.shiyculture.com/?user_id=%s&app_key=609d544b98d347a5bcc17637c529b95a&app_secret=609d544b98d347a5bcc17637c529b95a&allow_buy=1", szIMEI);

        m_wvMain.loadUrl(szUrl);

        m_wvMain.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String s) {
                webView.loadUrl(s);
                return true;
            }
        });

        m_wvMain.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView webView, int i) {
//                if (i < 100 && MainTaskFragment.this.isVisible()) {
//                    tvTaskProgress.setVisibility(View.VISIBLE);
//                    webView.setVisibility(View.GONE);
//                } else {
//                    if (MainTaskFragment.this.isVisible()) {
//                        tvTaskProgress.setVisibility(View.GONE);
//                        webView.setVisibility(View.VISIBLE);
//                    }
//                }
            }
        });
    }
}

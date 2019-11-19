package com.example.exercise2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class WebActivity extends Activity {

    private WebView mWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        initView();
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        mWeb.setWebViewClient(new WebViewClient());
        mWeb.getSettings().setJavaScriptEnabled(true);
        mWeb.loadUrl(url);
    }

    private void initView() {
        mWeb = (WebView) findViewById(R.id.web);
    }
}

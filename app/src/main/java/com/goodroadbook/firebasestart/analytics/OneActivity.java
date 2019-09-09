package com.goodroadbook.firebasestart.analytics;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.goodroadbook.firebasestart.R;

public class OneActivity extends AppCompatActivity
{
    private FAnalytics mFAnalytics = null;
    private WebView mAnalyticsWebView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);

        mFAnalytics = new FAnalytics();
        mFAnalytics.initFirebaseAnalytics(this);

        mFAnalytics.sendLogEvent(FAnalytics.FIREBASE_ITEMID_ONEACTIVITY,
                FAnalytics.FIREBASE_ITEMNAME_ONEACTIVITY);

        mFAnalytics.setUserProperty("favorite_food", "apple");

        mAnalyticsWebView = (WebView)findViewById(R.id.analyticswebview);
        mAnalyticsWebView.getSettings().setJavaScriptEnabled(true);
        mAnalyticsWebView.setWebViewClient(new WebViewClient()
        {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon)
            {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request)
            {
                return super.shouldOverrideUrlLoading(view, request);
            }

            @Override
            public void onPageFinished(WebView view, String url)
            {
                super.onPageFinished(view, url);
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
        {
            mAnalyticsWebView.addJavascriptInterface(
                    new AnalyticsWebInterface(this), AnalyticsWebInterface.TAG);
        }
        else
        {
            ;
        }
        mAnalyticsWebView.loadUrl("https://google.com");
    }
}

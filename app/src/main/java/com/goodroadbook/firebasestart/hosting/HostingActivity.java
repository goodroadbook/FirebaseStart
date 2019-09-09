package com.goodroadbook.firebasestart.hosting;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.goodroadbook.firebasestart.R;

public class HostingActivity extends AppCompatActivity
{
    private WebView mWebView = null;

    public class WebCustomClient extends WebViewClient
    {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view,
                                                String url)
        {
            return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hosting);

        mWebView = (WebView) findViewById(R.id.mywebview);
        mWebView.setWebViewClient(new WebCustomClient());
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl("https://fir-start-58b1f.firebaseapp.com/index_test.html");
    }
}

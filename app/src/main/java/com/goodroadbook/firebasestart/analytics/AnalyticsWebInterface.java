package com.goodroadbook.firebasestart.analytics;

import android.content.Context;
import android.os.Bundle;
import android.webkit.JavascriptInterface;

import com.google.firebase.analytics.FirebaseAnalytics;

public class AnalyticsWebInterface
{
    public static final String TAG = "AnalyticsWebInterface";
    private FirebaseAnalytics mAnalytics;

    public AnalyticsWebInterface(Context context)
    {
        mAnalytics = FirebaseAnalytics.getInstance(context);
    }

    @JavascriptInterface
    public void logEvent(String name, String itemId, String itemName)
    {
        mAnalytics.logEvent(name, bundleFromJson(itemId, itemName));
    }

    @JavascriptInterface
    public void setUserProperty(String name, String value)
    {
        mAnalytics.setUserProperty(name, value);
    }

    private Bundle bundleFromJson(String itemId, String itemName)
    {
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, itemId);
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, itemName);

        return bundle;
    }
}
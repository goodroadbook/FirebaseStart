package com.goodroadbook.firebasestart.analytics;

import android.content.Context;
import android.os.Bundle;

import com.google.firebase.analytics.FirebaseAnalytics;

public class FAnalytics
{
    public static final String FIREBASE_ANALYTICS_EVENT_NAME = "goodroadbook";
    public static final String FIREBASE_ITEMID_ONEACTIVITY = "itemid_one";
    public static final String FIREBASE_ITEMID_TWOACTIVITY = "itemid_two";
    public static final String FIREBASE_TIEMID_THREEACTIVITY = "itemid_three";
    public static final String FIREBASE_ITEMNAME_ONEACTIVITY = "itemname_one";
    public static final String FIREBASE_ITEMNAME_TWOACTIVITY = "itemname_two";
    public static final String FIREBASE_ITEMNAME_THREEACTIVITY = "itemname_three";

    private FirebaseAnalytics mFirebaseAnalytics = null;

    public FAnalytics()
    {

    }

    public void initFirebaseAnalytics(Context context)
    {
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(context);
    }

    public void sendLogEvent(String aItemId, String aItemName)
    {
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, aItemId);
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, aItemName);

        mFirebaseAnalytics.logEvent(FIREBASE_ANALYTICS_EVENT_NAME, bundle);
    }

    public void setUserProperty(String key, String value)
    {
        mFirebaseAnalytics.setUserProperty(key, value);
    }
}

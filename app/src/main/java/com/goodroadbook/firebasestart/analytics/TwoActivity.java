package com.goodroadbook.firebasestart.analytics;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.goodroadbook.firebasestart.R;

public class TwoActivity extends AppCompatActivity
{
    private FAnalytics mFAnalytics = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        mFAnalytics = new FAnalytics();
        mFAnalytics.initFirebaseAnalytics(this);

        mFAnalytics.sendLogEvent(FAnalytics.FIREBASE_ITEMID_TWOACTIVITY,
                FAnalytics.FIREBASE_ITEMNAME_TWOACTIVITY);
    }
}

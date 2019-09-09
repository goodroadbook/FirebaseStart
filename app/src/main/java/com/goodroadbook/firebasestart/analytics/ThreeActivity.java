package com.goodroadbook.firebasestart.analytics;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.goodroadbook.firebasestart.R;

public class ThreeActivity extends AppCompatActivity
{
    private FAnalytics mFAnalytics = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);

        mFAnalytics = new FAnalytics();
        mFAnalytics.initFirebaseAnalytics(this);

        mFAnalytics.sendLogEvent(FAnalytics.FIREBASE_TIEMID_THREEACTIVITY,
                FAnalytics.FIREBASE_ITEMNAME_THREEACTIVITY);
    }
}

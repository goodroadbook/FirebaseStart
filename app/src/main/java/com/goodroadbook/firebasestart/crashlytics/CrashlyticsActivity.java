package com.goodroadbook.firebasestart.crashlytics;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.crashlytics.android.Crashlytics;
import com.goodroadbook.firebasestart.R;

import io.fabric.sdk.android.Fabric;

public class CrashlyticsActivity extends AppCompatActivity implements View.OnClickListener
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crashlytics);

        // Firebase Crashlytics 초기화
        Fabric.with(this, new Crashlytics());

        Button crashbtn = (Button) findViewById(R.id.crashbtn);
        crashbtn.setOnClickListener(this);

        Crashlytics.log("CrashlyticsActivity onCreate() 1");
        Crashlytics.log(Log.INFO, Crashlytics.TAG, "CrashlyticsActivity onCreate() 2");
    }


    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.crashbtn:
                Crashlytics.getInstance().crash(); // Force a crash
                break;
        }
    }
}

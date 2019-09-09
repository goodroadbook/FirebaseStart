package com.goodroadbook.firebasestart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.goodroadbook.firebasestart.analytics.AnalyticsActivity;
import com.goodroadbook.firebasestart.authentication.AuthActivity;
import com.goodroadbook.firebasestart.cloudmessaging.CloudMessagingActivity;
import com.goodroadbook.firebasestart.crashlytics.CrashlyticsActivity;
import com.goodroadbook.firebasestart.dynamiclink.DynamicLinkActivity;
import com.goodroadbook.firebasestart.firestore.FirestoreActivity;
import com.goodroadbook.firebasestart.hosting.HostingActivity;
import com.goodroadbook.firebasestart.perfromance.PerformanceActivity;
import com.goodroadbook.firebasestart.realtimedb.MemoActivity;
import com.goodroadbook.firebasestart.cloudstorage.CloudStorageActivity;
import com.goodroadbook.firebasestart.remoteconfig.RemoteConfigActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button firebaseauthbtn = (Button)findViewById(R.id.firebaseauthbtn);
        firebaseauthbtn.setOnClickListener(this);

        Button firebaserealdbbtn = (Button)findViewById(R.id.firebaserealtimedbbtn);
        firebaserealdbbtn.setOnClickListener(this);

        Button firebasecloudfirestorebtn = (Button)findViewById(R.id.firebasecloudfirestorebtn);
        firebasecloudfirestorebtn.setOnClickListener(this);

        Button firebasecloudstoragebtn = (Button)findViewById(R.id.firebasecloudstoragebtn);
        firebasecloudstoragebtn.setOnClickListener(this);

        Button firebasehostingbtn = (Button)findViewById(R.id.firebasehostingbtn);
        firebasehostingbtn.setOnClickListener(this);

        Button firebasecrashlyticsbtn = (Button)findViewById(R.id.firebasecrashlyticsbtn);
        firebasecrashlyticsbtn.setOnClickListener(this);

        Button firebaseperformancebtn = (Button)findViewById(R.id.firebaseperformancebtn);
        firebaseperformancebtn.setOnClickListener(this);

        Button firebasecloudmessagingbtn = (Button)findViewById(R.id.firebasecloudmessagingbtn);
        firebasecloudmessagingbtn.setOnClickListener(this);

        Button firebaseremoteconfigbtn = (Button)findViewById(R.id.firebaseremoteconfigbtn);
        firebaseremoteconfigbtn.setOnClickListener(this);

        Button firebasedynamiclinks = (Button)findViewById(R.id.firebasedynamiclinksbtn);
        firebasedynamiclinks.setOnClickListener(this);

        Button firebaseinvitesbtn = (Button)findViewById(R.id.firebaseAnalyticsbtn);
        firebaseinvitesbtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        Intent i = null;
        switch (view.getId())
        {
            case R.id.firebaseauthbtn:
                i = new Intent(this, AuthActivity.class);
                startActivity(i);
                break;
            case R.id.firebaserealtimedbbtn:
                i = new Intent(this, MemoActivity.class);
                startActivity(i);
                break;
            case R.id.firebasecloudfirestorebtn:
                i = new Intent(this, FirestoreActivity.class);
                startActivity(i);
                break;
            case R.id.firebasecloudstoragebtn:
                i = new Intent(this, CloudStorageActivity.class);
                startActivity(i);
                break;
            case R.id.firebasehostingbtn:
                i = new Intent(this, HostingActivity.class);
                startActivity(i);
                break;
            case R.id.firebasecrashlyticsbtn:
                i = new Intent(this, CrashlyticsActivity.class);
                startActivity(i);
                break;
            case R.id.firebaseperformancebtn:
                i = new Intent(this, PerformanceActivity.class);
                startActivity(i);
                break;
            case R.id.firebasecloudmessagingbtn:
                i = new Intent(this, CloudMessagingActivity.class);
                startActivity(i);
                break;
            case R.id.firebaseremoteconfigbtn:
                i = new Intent(this, RemoteConfigActivity.class);
                startActivity(i);
                break;
            case R.id.firebasedynamiclinksbtn:
                i = new Intent(this, DynamicLinkActivity.class);
                startActivity(i);
                break;
            case R.id.firebaseAnalyticsbtn:
                i = new Intent(this, AnalyticsActivity.class);
                startActivity(i);
            default:
                break;
        }
    }
}

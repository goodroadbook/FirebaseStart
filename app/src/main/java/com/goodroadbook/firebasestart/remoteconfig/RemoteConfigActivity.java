package com.goodroadbook.firebasestart.remoteconfig;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.goodroadbook.firebasestart.BuildConfig;
import com.goodroadbook.firebasestart.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

public class RemoteConfigActivity extends AppCompatActivity implements View.OnClickListener
{
    // 원격 구성 키, 환영의 메시지를 변경
    private static final String WELCOME_MESSAGE_KEY = "welcome_message";

    //환영의 메시지를 대문자로 표시 (Default = false)
    private static final String WELCOME_MESSAGE_CAPS_KEY = "welcome_message_caps";

    private FirebaseRemoteConfig mFirebaseRemoteConfig;
    private TextView mWelComeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remoteconfig);

        mWelComeTextView = (TextView)findViewById(R.id.welcomtextview);
        Button fetchbtn = (Button)findViewById(R.id.fetchbutton);
        fetchbtn.setOnClickListener(this);

        mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setDeveloperModeEnabled(BuildConfig.DEBUG)
                .build();
        mFirebaseRemoteConfig.setConfigSettings(configSettings);

        mFirebaseRemoteConfig.setDefaults(R.xml.remote_config_defaults);

        fetchWelcome();
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.fetchbutton:
                fetchWelcome();
                break;
            default:
                break;
        }
    }

    private void fetchWelcome()
    {
        long cacheExpiration = 3600; // 1 hour in seconds.

        if (mFirebaseRemoteConfig.getInfo().getConfigSettings().isDeveloperModeEnabled())
        {
            cacheExpiration = 0;
        }

        mFirebaseRemoteConfig.fetch(cacheExpiration)
                .addOnCompleteListener(this, new OnCompleteListener<Void>()
                {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(RemoteConfigActivity.this, "Fetch Succeeded",
                                    Toast.LENGTH_SHORT).show();
                            mFirebaseRemoteConfig.activateFetched();
                        }
                        else
                        {
                            Toast.makeText(RemoteConfigActivity.this, "Fetch Failed",
                                    Toast.LENGTH_SHORT).show();
                        }
                        displayWelcomeMessage();
                    }
                });
    }

    private void displayWelcomeMessage()
    {
        String welcomeMessage = mFirebaseRemoteConfig.getString(WELCOME_MESSAGE_KEY);

        if (mFirebaseRemoteConfig.getBoolean(WELCOME_MESSAGE_CAPS_KEY))
        {
            mWelComeTextView.setAllCaps(true);
        }
        else
        {
            mWelComeTextView.setAllCaps(false);
        }

        mWelComeTextView.setText(welcomeMessage);
    }
}

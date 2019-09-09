package com.goodroadbook.firebasestart.perfromance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.goodroadbook.firebasestart.R;
import com.google.firebase.perf.FirebasePerformance;
import com.google.firebase.perf.metrics.AddTrace;
import com.google.firebase.perf.metrics.HttpMetric;
import com.google.firebase.perf.metrics.Trace;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class PerformanceActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener
{
    private Trace trace;

    private SharedPreferences settingPreference;
    private SharedPreferences.Editor editorPreference;

    @Override
    @AddTrace(name = "onCreateTrace", enabled = true /* optional */)
    protected void onCreate(Bundle savedInstanceState)
    {
        trace = FirebasePerformance.getInstance().newTrace("test_trace");
        trace.start();
        trace.putAttribute("onCreate", "start");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_performance);

        settingPreference = getSharedPreferences("firebase_setting", 0);
        editorPreference= settingPreference.edit();
        boolean checkstate = settingPreference.getBoolean("firebase_setting_performace", false);

        trace = FirebasePerformance.getInstance().newTrace("test_trace");
        trace.start();
        trace.putAttribute("onCreate", "start");

        Button foregroundbtn = (Button)findViewById(R.id.foregroundbtn);
        foregroundbtn.setOnClickListener(this);

        Button backgroundbtn = (Button)findViewById(R.id.backgroundbtn);
        backgroundbtn.setOnClickListener(this);

        Button networkbtn = (Button)findViewById(R.id.networkbtn);
        networkbtn.setOnClickListener(this);

        CheckBox performancecheckbox = (CheckBox) findViewById(R.id.performancecheckbox);
        performancecheckbox.setChecked(checkstate);
        performancecheckbox.setOnCheckedChangeListener(this);

        trace.putAttribute("onCreate", "end");
    }

    @Override
    protected void onStart()
    {
        super.onStart();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        backgroundjob();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();

        trace.stop();
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.foregroundbtn:
                foregroundjob();
                break;
            case R.id.backgroundbtn:
                break;
            case R.id.networkbtn:
                networkjob();
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean checkstate)
    {
        editorPreference.putBoolean("firebase_setting_performace", checkstate);
        editorPreference.commit();

        FirebasePerformance.getInstance().setPerformanceCollectionEnabled(checkstate);
    }

    private void foregroundjob()
    {
        trace.putAttribute("foregroundjob", "start");
        for(int i=0; i<1000; i++)
        {
            try
            {
                Thread.sleep(10);
            }
            catch (Exception e)
            {
                ;
            }
        }
        trace.putAttribute("foregroundjob", "end");
    }

    private void backgroundjob()
    {
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                trace.putAttribute("backgroundjob", "start");
                for(int i=0; i<1000; i++)
                {
                    try
                    {
                        Thread.sleep(10);
                    }
                    catch (Exception e)
                    {
                        ;
                    }
                }
                trace.putAttribute("backgroundjob", "end");
            }
        }).start();
    }

    private void networkjob()
    {
        trace.putAttribute("networkjob", "start");
        loadImageFromWeb();
        manualNetworkTrace();
        trace.putAttribute("networkjob", "end");
    }

    private void loadImageFromWeb()
    {
        ImageView showImageView = (ImageView)findViewById(R.id.showimg);

        final String IMAGE_URL =
                "https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png";

        Glide.with(this).
                load(IMAGE_URL)
                .placeholder(new ColorDrawable(ContextCompat.getColor(this, R.color.colorAccent)))
                .listener(new RequestListener<String, GlideDrawable>()
                {
                    @Override
                    public boolean onException(
                            Exception e, String model, Target<GlideDrawable> target,
                            boolean isFirstResource)
                    {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(
                            GlideDrawable resource, String model, Target<GlideDrawable> target,
                            boolean isFromMemoryCache, boolean isFirstResource)
                    {
                        return false;
                    }
                }).into(showImageView);
    }

    private void manualNetworkTrace()
    {
        try
        {
            byte[] data = "TESTTESTTESTTESTTESTTESTTESTTEST!".getBytes();

            HttpMetric metric =
                    FirebasePerformance.getInstance().newHttpMetric("https://www.google.com",
                            FirebasePerformance.HttpMethod.GET);
            final URL url = new URL("https://www.google.com");
            metric.start();
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json");
            try
            {
                DataOutputStream outputStream = new DataOutputStream(conn.getOutputStream());
                outputStream.write(data);
            }
            catch (IOException ignored)
            {
                ;
            }
            metric.setRequestPayloadSize(data.length);
            metric.setHttpResponseCode(conn.getResponseCode());
            conn.getInputStream();

            conn.disconnect();
            metric.stop();
        }
        catch (Exception e)
        {
            ;
        }
    }
}

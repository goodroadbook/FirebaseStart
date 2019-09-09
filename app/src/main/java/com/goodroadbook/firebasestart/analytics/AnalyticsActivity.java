package com.goodroadbook.firebasestart.analytics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.goodroadbook.firebasestart.R;

public class AnalyticsActivity extends AppCompatActivity implements View.OnClickListener
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analytics);

        Button onebtn = (Button)findViewById(R.id.onebtn);
        onebtn.setOnClickListener(this);

        Button twobtn = (Button)findViewById(R.id.twobtn);
        twobtn.setOnClickListener(this);

        Button threebtn = (Button)findViewById(R.id.threebtn);
        threebtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        Intent i = null;
        switch (view.getId())
        {
            case R.id.onebtn:
                i = new Intent(this, OneActivity.class);
                startActivity(i);
                break;
            case R.id.twobtn:
                i = new Intent(this, TwoActivity.class);
                startActivity(i);
                break;
            case R.id.threebtn:
                i = new Intent(this, ThreeActivity.class);
                startActivity(i);
                break;
        }
    }
}

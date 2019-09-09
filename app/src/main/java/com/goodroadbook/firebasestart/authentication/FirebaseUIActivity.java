package com.goodroadbook.firebasestart.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.goodroadbook.firebasestart.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class FirebaseUIActivity extends AppCompatActivity implements View.OnClickListener
{
    private static final int RC_SIGN_IN                = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firbase_ui);

        Button firebaseuiauthbtn = (Button)findViewById(R.id.firebaseuiauthbtn);
        firebaseuiauthbtn.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        Log.d("namjinha", "onActivityResult in requestCode = " + requestCode);
        Log.d("namjinha", "onActivityResult in resultCode = " + resultCode);

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN)
        {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK)
            {
                // Successfully signed in
                Intent i = new Intent(this, SignedInActivity.class);
                i.putExtras(data);
                startActivity(i);

            }
            else
            {
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                // ...
            }
        }
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.firebaseuiauthbtn:
                signin();
                break;
            default:
                break;
        }
    }

    /**
     * 인증 요청
     */
    private void signin()
    {
        startActivityForResult(
                AuthUI.getInstance().createSignInIntentBuilder()
                        .setTheme(getSelectedTheme())                 // Theme 설정
                        .setLogo(getSelectedLogo())                  // 로고 설정
                        .setAvailableProviders(getSelectedProviders())// Providers 설정
                        .setTosAndPrivacyPolicyUrls("https://naver.com",
                                "https://google.com")
                        .setIsSmartLockEnabled(true)              //SmartLock 설정
                        .build(),
                RC_SIGN_IN);
    }

    /**
     * FirebaseUI에 표시할 테마 정보
     * @return 테마 정보
     */
    private int getSelectedTheme()
    {
        return AuthUI.getDefaultTheme();
    }

    /**
     * Firebase UI에 표시할 로고 이미지
     * @return 로고 이미지
     */
    private int getSelectedLogo()
    {
        return AuthUI.NO_LOGO;
    }

    /**
     * FirebaseUI를 통해 제공 받을 인증 서비스 설정
     * @return 인증 서비스
     */
    private List<AuthUI.IdpConfig> getSelectedProviders()
    {
        List<AuthUI.IdpConfig> selectedProviders = new ArrayList<>();
        CheckBox googlechk = (CheckBox)findViewById(R.id.google_provider);
        CheckBox facebookchk = (CheckBox)findViewById(R.id.facebook_provider);
        CheckBox twitterchk = (CheckBox)findViewById(R.id.twitter_provider);
        CheckBox emailchk = (CheckBox)findViewById(R.id.email_provider);

        if(googlechk.isChecked())
        {
            selectedProviders.add(new AuthUI.IdpConfig.GoogleBuilder().build());
        }

        if(facebookchk.isChecked())
        {
            selectedProviders.add(new AuthUI.IdpConfig.FacebookBuilder().build());
        }

        if(twitterchk.isChecked())
        {
            selectedProviders.add(new AuthUI.IdpConfig.TwitterBuilder().build());
        }

        if(emailchk.isChecked())
        {
            selectedProviders.add(new AuthUI.IdpConfig.EmailBuilder().build());
        }

        return selectedProviders;
    }
}

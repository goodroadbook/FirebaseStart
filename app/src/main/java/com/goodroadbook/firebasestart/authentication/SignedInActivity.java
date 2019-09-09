package com.goodroadbook.firebasestart.authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.goodroadbook.firebasestart.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.TwitterAuthProvider;
import com.google.firebase.auth.UserInfo;

public class SignedInActivity extends AppCompatActivity implements View.OnClickListener
{
    private IdpResponse mIdpResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signedin);

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser == null)
        {
            finish();
            return;
        }

        mIdpResponse = IdpResponse.fromResultIntent(getIntent());

        setContentView(R.layout.activity_signedin);
        populateProfile();
        populateIdpToken();

        Button signoutbtn = (Button)findViewById(R.id.sign_out);
        signoutbtn.setOnClickListener(this);

        Button deleteuser = (Button)findViewById(R.id.delete_account);
        deleteuser.setOnClickListener(this);
    }


    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.sign_out:
                signOut();
                break;
            case R.id.delete_account:
                deleteAccountClicked();
                break;
            default:
                break;
        }
    }

    private void signOut()
    {
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>()
                {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        if (task.isSuccessful())
                        {
                            finish();
                        }
                        else
                        {
                        }
                    }
                });
    }

    private void deleteAccountClicked()
    {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to delete this account?")
                .setPositiveButton("Yes, nuke it!", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        deleteAccount();
                    }
                })
                .setNegativeButton("No", null)
                .create();

        dialog.show();
    }

    private void deleteAccount()
    {
        AuthUI.getInstance()
                .delete(this)
                .addOnCompleteListener(new OnCompleteListener<Void>()
                {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        if (task.isSuccessful())
                        {
                            finish();
                        }
                        else
                        {
                        }
                    }
                });
    }

    private void populateProfile()
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        TextView emailtxt = (TextView)findViewById(R.id.user_email);
        emailtxt.setText(
                TextUtils.isEmpty(user.getEmail()) ? "No email" : user.getEmail());

        TextView usernametxt = (TextView)findViewById(R.id.user_display_name);
        usernametxt.setText(
                TextUtils.isEmpty(user.getDisplayName()) ? "No display name" : user.getDisplayName());

        StringBuilder providerList = new StringBuilder(100);

        providerList.append("Providers used: ");

        if (user.getProviderData() == null || user.getProviderData().isEmpty())
        {
            providerList.append("none");
        }
        else
        {
            for (UserInfo profile : user.getProviderData())
            {
                String providerId = profile.getProviderId();
                if (GoogleAuthProvider.PROVIDER_ID.equals(providerId))
                {
                    providerList.append("Google");
                }
                else if (FacebookAuthProvider.PROVIDER_ID.equals(providerId))
                {
                    providerList.append("Facebook");
                }
                else if(TwitterAuthProvider.PROVIDER_ID.equals(providerId))
                {
                    providerList.append("Twitter");
                }
                else if (EmailAuthProvider.PROVIDER_ID.equals(providerId))
                {
                    providerList.append("Email");
                }
                else
                {
                    providerList.append(providerId);
                }
            }
        }

        TextView userenabled = (TextView)findViewById(R.id.user_enabled_providers);
        userenabled.setText(providerList);
    }

    private void populateIdpToken()
    {
        String token = null;

        if (mIdpResponse != null)
        {
            token = mIdpResponse.getIdpToken();
        }

        if (token == null)
        {
            findViewById(R.id.idp_token_layout).setVisibility(View.GONE);
        }
        else
        {
            ((TextView) findViewById(R.id.idp_token)).setText(token);
        }
    }
}

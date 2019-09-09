package com.goodroadbook.firebasestart.cloudstorage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.goodroadbook.firebasestart.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

public class DownloadActivity extends AppCompatActivity implements View.OnClickListener
{
    private File localFile = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);

        Button localfilednbtn = (Button)findViewById(R.id.localimgdownloadbtn);
        localfilednbtn.setOnClickListener(this);

        Button firebaseuibtn = (Button)findViewById(R.id.imgfirebaseuidnbtn);
        firebaseuibtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.localimgdownloadbtn:
                showDownloadLocalFileImageView();
                break;
            case R.id.imgfirebaseuidnbtn:
                showFirebaseUiDownloadImageView();
                break;
            default:
                break;
        }
    }

    private void showDownloadLocalFileImageView()
    {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        StorageReference pathReference = storageRef.child("storage/20190418_064223.jpg");

        try
        {
            localFile = File.createTempFile("images", "jpg");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        pathReference.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>()
        {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot)
            {
                // Local temp file has been created
                long filesize = taskSnapshot.getTotalByteCount();
                Log.d("namjinha", "File Size = " + filesize);
                Log.d("namjinha", "File Name = " + localFile.getAbsolutePath());

                ImageView imageView = (ImageView)findViewById(R.id.fcstorageimg);
                Glide.with(DownloadActivity.this).load(new File(localFile.getAbsolutePath())).into(imageView);
            }
        }).addOnFailureListener(new OnFailureListener()
        {
            @Override
            public void onFailure(@NonNull Exception exception)
            {
                // Handle any errors
                Log.d("namjinha", "onFailure in");
            }
        });
    }

    private void showFirebaseUiDownloadImageView()
    {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        StorageReference pathReference = storageRef.child("storage/20190418_064223.jpg");

        ImageView imageView = (ImageView)findViewById(R.id.fcstorageimg);

        Glide.with(this /* context */)
                .using(new FirebaseImageLoader())
                .load(pathReference)
                .into(imageView);
    }
}

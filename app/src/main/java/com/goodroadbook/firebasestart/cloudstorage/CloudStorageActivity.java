package com.goodroadbook.firebasestart.cloudstorage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.goodroadbook.firebasestart.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class CloudStorageActivity extends AppCompatActivity implements View.OnClickListener
{
    private final int REQUEST_CODE = 100;

    private Button uploadbtn;
    private Button downloadbtn;
    private Button metainfobtn;
    private Button deletebtn;
    private Button filelistbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cloudstorage);

        uploadbtn = (Button) findViewById(R.id.uploadbtn);
        uploadbtn.setOnClickListener(this);

        downloadbtn = (Button) findViewById(R.id.downloadbtn);
        downloadbtn.setOnClickListener(this);

        metainfobtn = (Button) findViewById(R.id.metainfobtn);
        metainfobtn.setOnClickListener(this);

        deletebtn = (Button) findViewById(R.id.deletebtn);
        deletebtn.setOnClickListener(this);

        filelistbtn = (Button)findViewById(R.id.filelistbtn);
        filelistbtn.setOnClickListener(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            if (checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            {
                requestPermissions(new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE);

                Toast.makeText(this, "안드로이드 6.0부터 마시멜로부터 일부 권한에 대해 사용자에게 동의 필요!", Toast.LENGTH_LONG).show();
                uploadbtn.setEnabled(false);
                downloadbtn.setEnabled(false);
                metainfobtn.setEnabled(false);
                deletebtn.setEnabled(false);
                filelistbtn.setEnabled(false);
            }
        }
    }

    @Override
    public void onClick(View v)
    {
        Intent i = null;

        switch (v.getId())
        {
            case R.id.uploadbtn:
                i = new Intent(this, UploadActivity.class);
                break;
            case R.id.downloadbtn:
                i = new Intent(this, DownloadActivity.class);
                break;
            case R.id.metainfobtn:
                i = new Intent(this, MetaInfoActivity.class);
                break;
            case R.id.deletebtn:
                deleteFile();
                break;
            case R.id.filelistbtn:
                i = new Intent(this, FileListActivity.class);
                break;
            default:
                break;
        }

        if(i != null)
        {
            startActivity(i);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    uploadbtn.setEnabled(true);
                    downloadbtn.setEnabled(true);
                    metainfobtn.setEnabled(true);
                    deletebtn.setEnabled(true);
                    filelistbtn.setEnabled(true);
                }
                break;
            default:
                break;
        }
    }

    private void deleteFile()
    {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();

        StorageReference desertRef = storageRef.child("storage/1561444706529.jpg");
        desertRef.delete().addOnSuccessListener(new OnSuccessListener<Void>()
        {
            @Override
            public void onSuccess(Void aVoid)
            {
                // File deleted successfully
            }
        }).addOnFailureListener(new OnFailureListener()
        {
            @Override
            public void onFailure(@NonNull Exception exception)
            {
                // Uh-oh, an error occurred!
            }
        });
    }
}

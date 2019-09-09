package com.goodroadbook.firebasestart.cloudstorage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.goodroadbook.firebasestart.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;

public class MetaInfoActivity extends AppCompatActivity implements View.OnClickListener
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metainfo);

        Button metabtn = (Button)findViewById(R.id.metabtn);
        metabtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.metabtn:
                getMetaData();
                break;
        }
    }

    private void getMetaData()
    {
        // 참조 만들기 및 파일 업로드
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        StorageReference forestRef = storageRef.child("storage/20190418_064223.jpg");

        forestRef.getMetadata().addOnSuccessListener(new OnSuccessListener<StorageMetadata>()
        {
            @Override
            public void onSuccess(StorageMetadata storageMetadata)
            {
                String metadata = storageMetadata.getName() + "\n" +
                        storageMetadata.getPath() + "\n" +
                        storageMetadata.getBucket();

                TextView metatxt = (TextView)findViewById(R.id.metainfotxt);
                metatxt.setText(metadata);
            }
        }).addOnFailureListener(new OnFailureListener()
        {
            @Override
            public void onFailure(@NonNull Exception exception)
            {
                ;
            }
        });
    }
}

package com.goodroadbook.firebasestart.firestore;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.goodroadbook.firebasestart.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FirestoreActivity extends AppCompatActivity implements View.OnClickListener
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firestore);

        Button adddatabtn = (Button)findViewById(R.id.firestoreadddatabtn);
        adddatabtn.setOnClickListener(this);

        Button setdatabtn = (Button)findViewById(R.id.firestoresetdatabtn);
        setdatabtn.setOnClickListener(this);

        Button deletedocbtn = (Button)findViewById(R.id.firestoredeletedocbtn);
        deletedocbtn.setOnClickListener(this);

        Button deletefieldbtn = (Button)findViewById(R.id.firestoredeletefieldbtn);
        deletefieldbtn.setOnClickListener(this);

        Button selectdocbtn = (Button)findViewById(R.id.firestoreseldatabtn);
        selectdocbtn.setOnClickListener(this);

        Button listenerdocbtn = (Button)findViewById(R.id.firestorelistenerdatabtn);
        listenerdocbtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("경고");
            builder.setMessage("사용자 인증이 되지 않았습니다. Firebase 인증에서 로그인 후 사용하세요.");
            builder.setPositiveButton("확인", new DialogInterface.OnClickListener()
            {
                public void onClick(DialogInterface dialog, int which)
                {
                    ;
                }
            });
            builder.show();
            return;
        }

        switch (view.getId())
        {
            case R.id.firestoreadddatabtn:
                addData();
                break;
            case R.id.firestoresetdatabtn:
                setData();
                break;
            case R.id.firestoredeletedocbtn:
                deleteDoc();
                break;
            case R.id.firestoredeletefieldbtn:
                deleteField();
                break;
            case R.id.firestoreseldatabtn:
                selectDoc();
                break;
            case R.id.firestorelistenerdatabtn:
                listenerDoc();
                break;
        }
    }

    private void addData()
    {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String, Object> member = new HashMap<>();
        member.put("name", "홍길동");
        member.put("address", "수원시");
        member.put("age", 25);
        member.put("id", "hong");
        member.put("pwd", "hello!");

        db.collection("users")
                .add(member)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>()
                {
                    @Override
                    public void onSuccess(DocumentReference documentReference)
                    {
                        Log.d("namjinha", "Document ID = " + documentReference.get());
                    }
                })
                .addOnFailureListener(new OnFailureListener()
                {
                    @Override
                    public void onFailure(@NonNull Exception e)
                    {
                        Log.d("namjinha", "Document Error!!");
                    }
                });
    }

    private void setData()
    {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String, Object> member = new HashMap<>();
        member.put("name", "나야나");
        member.put("address", "경기도");
        member.put("age", 25);
        member.put("id", "my");
        member.put("pwd", "hello!");

        db.collection("users")
                .document("userinfo")
                .set(member)
                .addOnSuccessListener(new OnSuccessListener<Void>()
                {
                    @Override
                    public void onSuccess(Void aVoid)
                    {
                        Log.d("namjinha", "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener()
                {
                    @Override
                    public void onFailure(@NonNull Exception e)
                    {
                        Log.d("namjinha", "Document Error!!");
                    }
                });
    }

    private void deleteDoc()
    {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("users").document("userinfo")
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("namjinha", "DocumentSnapshot successfully deleted!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("namjinha", "Error deleting document", e);
                    }
                });
    }

    private void deleteField()
    {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        DocumentReference docRef = db.collection("users").document("userinfo");

        Map<String,Object> updates = new HashMap<>();
        updates.put("address", FieldValue.delete());

        docRef.update(updates).addOnCompleteListener(new OnCompleteListener<Void>() {

            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Log.d("namjinha", "DocumentSnapshot successfully deleted!");
            }
        });
    }

    private void selectDoc()
    {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("users").document("userinfo");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>()
        {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task)
            {
                if (task.isSuccessful())
                {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists())
                    {
                        Log.d("namjinha", "DocumentSnapshot data: " + document.getData());

                    }
                    else
                    {
                        Log.d("namjinha", "No such document");
                    }
                }
                else
                {
                    Log.d("namjinha", "get failed with ", task.getException());
                }
            }
        });
    }

    private void listenerDoc()
    {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        final DocumentReference docRef = db.collection("users").document("userinfo");
        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>()
        {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot,
                                @Nullable FirebaseFirestoreException e)
            {
                if (e != null)
                {
                    Log.w("namjinha", "Listen failed.", e);
                    return;
                }

                if (snapshot != null && snapshot.exists())
                {
                    Log.d("namjinha", "Current data: " + snapshot.getData());
                }
                else
                {
                    Log.d("namjinha", "Current data: null");
                }
            }
        });
    }
}

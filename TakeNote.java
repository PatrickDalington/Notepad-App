package com.ourlove.testingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class TakeNote extends AppCompatActivity {

    EditText title,note;
    Button save;

    DatabaseReference noteRef;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_note);


        title = findViewById(R.id.title);
        note = findViewById(R.id.note);
        save = findViewById(R.id.save);


        user = FirebaseAuth.getInstance().getCurrentUser();
        noteRef = FirebaseDatabase.getInstance().getReference("Notes").child(user.getUid());


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(title.getText())){
                    Toast.makeText(TakeNote.this, "Please input title of your note", Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(note.getText())){
                    Toast.makeText(TakeNote.this, "Please input note", Toast.LENGTH_SHORT).show();

                }else{

                    String key = noteRef.push().getKey();

                    HashMap<String,Object> hashMap = new HashMap<>();
                    hashMap.put("title",title.getText().toString().trim());
                    hashMap.put("content", note.getText().toString().trim());
                    hashMap.put("key",key);

                    noteRef.child(key).setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(TakeNote.this, "Saved", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(TakeNote.this,MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }
                    });
                }
            }
        });
    }
}
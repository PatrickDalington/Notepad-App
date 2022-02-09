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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class SignUp extends AppCompatActivity {

    EditText username,email,password;
    Button signUp;

    DatabaseReference userRef;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        mAuth = FirebaseAuth.getInstance();

        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        signUp = findViewById(R.id.sign);


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(username.getText())){
                    Toast.makeText(SignUp.this, "Please input username", Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(email.getText())){
                    Toast.makeText(SignUp.this, "Please input your email", Toast.LENGTH_SHORT).show();
                }else if (!email.getText().toString().contains("@")){
                    Toast.makeText(SignUp.this, "Please input a valid email", Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(password.getText())){
                    Toast.makeText(SignUp.this, "Please input a strong password", Toast.LENGTH_SHORT).show();
                }else{


                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {

                            userRef = FirebaseDatabase.getInstance().getReference("Users");

                            HashMap<String,Object> hashMap = new HashMap<>();
                            hashMap.put("id",authResult.getUser().getUid());
                            hashMap.put("email",email.getText().toString());
                            hashMap.put("password",password.getText().toString());

                            userRef.child(authResult.getUser().getUid()).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Intent intent = new Intent(SignUp.this,MainActivity.class);
                                    startActivity(intent);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(SignUp.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                    });

                }

            }
        });
    }
}
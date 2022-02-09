package com.ourlove.testingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayNote extends AppCompatActivity {

    TextView title,text;
    String titleT,textT;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_note);




        intent = getIntent();

        title = findViewById(R.id.title);
        text = findViewById(R.id.text);


        if (intent !=null){

            titleT = intent.getStringExtra("title");
            textT = intent.getStringExtra("text");

            getSupportActionBar().setTitle(titleT);
            title.setText(titleT);
            text.setText(textT);

        }


    }
}
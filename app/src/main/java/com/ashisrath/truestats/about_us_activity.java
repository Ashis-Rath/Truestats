package com.ashisrath.truestats;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class about_us_activity extends AppCompatActivity {
    ImageView backbtnCoBed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us_activity);

        backbtnCoBed = findViewById(R.id.backbtnViewProfile);
        backbtnCoBed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
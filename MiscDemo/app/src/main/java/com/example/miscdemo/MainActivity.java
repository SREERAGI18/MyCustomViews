package com.example.miscdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnMapsAct, btnBroadcastAct, btnServiceAct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMapsAct = findViewById(R.id.btnMapsActivity);
        btnBroadcastAct = findViewById(R.id.btnBroadcastAct);
        btnServiceAct = findViewById(R.id.btnServiceAct);

        btnServiceAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ServiceActivity.class));
            }
        });

        btnMapsAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MapsActivity.class));
            }
        });

        btnBroadcastAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, BroadcaastActivity.class));
            }
        });
    }
}
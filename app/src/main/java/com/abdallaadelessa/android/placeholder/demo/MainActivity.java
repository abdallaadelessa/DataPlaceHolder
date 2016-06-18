package com.abdallaadelessa.android.placeholder.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.abdallaadelessa.android.placeholder.PlaceHolder;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PlaceHolder viewById = (PlaceHolder) findViewById(R.id.listplaceholder);
        viewById.showActionButton(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, "Action", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

package com.abdallaadelessa.android.dataplaceholder.demo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.abdallaadelessa.android.dataplaceholder.DataPlaceHolder;

import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final DataPlaceHolder viewById = (DataPlaceHolder) findViewById(R.id.dataplaceholder);
        final Handler handler = new Handler();

//        viewById.showActionButton(new Runnable() {
//            @Override
//            public void run() {
//                Toast.makeText(MainActivity.this, "Action", Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}

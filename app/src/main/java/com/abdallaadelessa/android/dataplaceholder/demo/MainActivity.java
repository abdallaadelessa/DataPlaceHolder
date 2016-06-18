package com.abdallaadelessa.android.dataplaceholder.demo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.abdallaadelessa.android.dataplaceholder.DataPlaceHolder;


public class MainActivity extends AppCompatActivity {
    private final Handler handler = new Handler();
    private DataPlaceHolder dataPlaceHolder;
    private Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataPlaceHolder = (DataPlaceHolder) findViewById(R.id.dataplaceholder);
        (findViewById(R.id.btnError)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thread = null;
                dataPlaceHolder.showMessage("No Connection Error", -1, R.string.retry, new Runnable() {
                    @Override
                    public void run() {
                        (findViewById(R.id.btnSuccess)).performClick();
                    }
                });
            }
        });
        (findViewById(R.id.btnSuccess)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thread = null;
                dataPlaceHolder.dismissAll();
            }
        });
        (findViewById(R.id.btnEmpty)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thread = null;
                dataPlaceHolder.showMessage("No Content", R.drawable.navigation_error);
            }
        });
        (findViewById(R.id.btnDimProgress)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thread = null;
                dataPlaceHolder.showDimProgress();
            }
        });
        (findViewById(R.id.btnInDeterminateLoading)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thread = null;
                dataPlaceHolder.showProgress();
            }
        });
        (findViewById(R.id.btnDeterminateLoading)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (thread == null) {
                    thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 0; i < 100; i = i + 1) {
                                if (thread == null) break;
                                final int progress = i;
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        dataPlaceHolder.showMessage(progress+"% Loading...",progress,-1);
                                    }
                                });
                                try {
                                    Thread.sleep(50);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            thread = null;
                        }
                    });
                    thread.start();
                }
            }
        });
    }
}

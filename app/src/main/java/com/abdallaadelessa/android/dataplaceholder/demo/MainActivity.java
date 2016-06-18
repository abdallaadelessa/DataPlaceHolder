package com.abdallaadelessa.android.dataplaceholder.demo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.abdallaadelessa.android.dataplaceholder.DataPlaceHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {
    private final Handler handler = new Handler();
    @BindView(R.id.btnError)
    Button btnError;
    @BindView(R.id.btnSuccess)
    Button btnSuccess;
    @BindView(R.id.btnEmpty)
    Button btnEmpty;
    @BindView(R.id.btnInDeterminateLoading)
    Button btnInDeterminateLoading;
    @BindView(R.id.btnDeterminateLoading)
    Button btnDeterminateLoading;
    @BindView(R.id.btnDimProgress)
    Button btnDimProgress;
    @BindView(R.id.dataplaceholder)
    DataPlaceHolder dataPlaceHolder;
    private Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        dataPlaceHolder.showMessage("Message", 0, false, R.drawable.navigation_error, "Action Button", new Runnable() {
            @Override
            public void run() {

            }
        });
    }

    @OnClick({R.id.btnError, R.id.btnSuccess, R.id.btnEmpty, R.id.btnInDeterminateLoading, R.id.btnDeterminateLoading, R.id.btnDimProgress})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnError:
                thread = null;
                dataPlaceHolder.showMessage("No Connection Error", -1, "Retry", new Runnable() {
                    @Override
                    public void run() {
                        (findViewById(R.id.btnSuccess)).performClick();
                    }
                });
                break;
            case R.id.btnSuccess:
                thread = null;
                dataPlaceHolder.dismissAll();
                break;
            case R.id.btnEmpty:
                thread = null;
                dataPlaceHolder.showMessage("No Content", R.drawable.navigation_error);
                break;
            case R.id.btnInDeterminateLoading:
                thread = null;
                dataPlaceHolder.showProgress();
                break;
            case R.id.btnDeterminateLoading:
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
                                        dataPlaceHolder.showMessage(progress + "% Loading...", progress, -1);
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
                break;
            case R.id.btnDimProgress:
                thread = null;
                dataPlaceHolder.showDimProgress();
                break;
        }
    }
}

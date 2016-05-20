package com.abdallaadelessa.android.listplaceholder;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.linkdev.fta.R;
import com.linkdev.fta.helpers.AppLogger;
import com.pnikosis.materialishprogress.ProgressWheel;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by abdalla on 29/07/15.
 */
public class ListPlaceHolder extends FrameLayout {
    @BindView(R.id.vgParentLayout)
    View vgParentLayout;
    @BindView(R.id.imgState)
    ImageView imgState;
    @BindView(R.id.tvMessage)
    TextView tvMessage;
    @BindView(R.id.btnRunnable)
    Button btnRunnable;
    @BindView(R.id.pbProgress)
    ProgressWheel pbProgress;

    public ListPlaceHolder(Context context) {
        super(context);
        init(context);
    }

    public ListPlaceHolder(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public ListPlaceHolder(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    // ------------------------->

    private void init(Context cxt) {
        try {
            View view = inflate(cxt, R.layout.view_list_placeholder, this);
            ButterKnife.bind(this, view);
            hideAll();
        }
        catch(Exception e) {
            AppLogger.logError(e, true);
        }
    }

    private void dimParentBackground() {
        vgParentLayout.setClickable(true);
        vgParentLayout.setBackgroundResource(R.color.colorDim);
    }

    private void clearParentLayoutBackground() {
        vgParentLayout.setClickable(false);
        vgParentLayout.setBackgroundDrawable(null);
    }

    // ------------------------->

    public void hideAll() {
        clearParentLayoutBackground();
        btnRunnable.setVisibility(View.GONE);
        tvMessage.setVisibility(View.GONE);
        pbProgress.setVisibility(View.GONE);
        imgState.setVisibility(View.GONE);
    }

    public void showMessage(String message, int imgStateResId, final int btnTextResId, final Runnable runnable) {
        hideAll();
        tvMessage.setText(message);
        tvMessage.setVisibility(View.VISIBLE);
        if(runnable != null) {
            btnRunnable.setVisibility(View.VISIBLE);
            if(btnTextResId > 0) {
                btnRunnable.setText(btnTextResId);
            }
            btnRunnable.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    runnable.run();
                }
            });
        }
        if(imgStateResId > 0) {
            imgState.setVisibility(View.VISIBLE);
            imgState.setImageResource(imgStateResId);
        }
    }

    public void showMessage(String message, int imgStateResId, final Runnable runnable) {
        showMessage(message, imgStateResId, -1, runnable);
    }

    public void showMessage(String message, final Runnable runnable) {
        showMessage(message, -1, -1, runnable);
    }

    public void showMessage(String message, int imgStateResId) {
        showMessage(message, imgStateResId, -1, null);
    }

    public void showMessage(String message) {
        showMessage(message, -1, -1, null);
    }

    public void showProgress() {
        hideAll();
        pbProgress.setVisibility(View.VISIBLE);
    }

    public void showDimProgress() {
        showProgress();
        dimParentBackground();
    }

    // -------------------------> Attrs

    public void setMessageTextColor(int color){
        tvMessage.setTextColor(color);
    }

    // ------------------------->
}

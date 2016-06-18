package com.abdallaadelessa.android.dataplaceholder;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.pnikosis.materialishprogress.ProgressWheel;


/**
 * Created by abdalla on 29/07/15.
 */
public class DataPlaceHolder extends FrameLayout {
    private static final String TAG_PLACE_HOLDER = "PlaceHolder";
    private View vgParentLayout;
    private ImageView ivState;
    private TextView tvMessage;
    private Button btnAction;
    private ProgressWheel pbProgress;
    private int mDimModeColor;
    private boolean mHideContent;

    public DataPlaceHolder(Context context) {
        super(context);
        initUI(context);
    }

    public DataPlaceHolder(Context context, AttributeSet attrs) {
        super(context, attrs);
        initUI(context);
        readAttributeSet(context, attrs, -1);
    }

    public DataPlaceHolder(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initUI(context);
        readAttributeSet(context, attrs, defStyle);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        hideContent(mHideContent);
    }

    private static void logError(Exception e) {
        Log.e(TAG_PLACE_HOLDER, "PlaceHolder Error", e);
    }

    // ------------------------->

    private void initUI(Context cxt) {
        try {
            View view = inflate(cxt, R.layout.view_dataplaceholder, this);
            vgParentLayout = view.findViewById(R.id.vgParentLayout);
            ivState = (ImageView) view.findViewById(R.id.imgState);
            tvMessage = (TextView) view.findViewById(R.id.tvMessage);
            btnAction = (Button) view.findViewById(R.id.btnRunnable);
            pbProgress = (ProgressWheel) view.findViewById(R.id.pbProgress);
            mDimModeColor = ContextCompat.getColor(cxt, R.color.colorDim);
            dismissAll();
        } catch (Exception e) {
            logError(e);
        }
    }

    private void dimParentBackground() {
        vgParentLayout.setClickable(true);
        vgParentLayout.setBackgroundColor(mDimModeColor);
    }

    private void clearParentLayoutBackground() {
        vgParentLayout.setClickable(false);
        vgParentLayout.setBackgroundDrawable(null);
    }

    private void hideContent(boolean hide) {
        mHideContent = hide;
        for (int i = 1; i < getChildCount(); i++) {
            getChildAt(i).setVisibility(hide ? GONE : VISIBLE);
        }
    }

    private void hidePlaceHolderViews() {
        clearParentLayoutBackground();
        btnAction.setVisibility(View.GONE);
        tvMessage.setVisibility(View.GONE);
        pbProgress.setVisibility(View.GONE);
        ivState.setVisibility(View.GONE);
    }

    // -------------------------> Actions

    private void dismissAllWithContent() {
        hideContent(true);
        hidePlaceHolderViews();
    }

    public void dismissAll() {
        hideContent(false);
        hidePlaceHolderViews();
    }

    public void showMessage(String message, int stateImageResId, final int actionTextResId, final Runnable action) {
        dismissAllWithContent();
        tvMessage.setText(message);
        tvMessage.setVisibility(View.VISIBLE);
        if (action != null) {
            btnAction.setVisibility(View.VISIBLE);
            if (actionTextResId > 0) {
                btnAction.setText(actionTextResId);
            }
            btnAction.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    action.run();
                }
            });
        }
        if (stateImageResId > 0) {
            ivState.setVisibility(View.VISIBLE);
            ivState.setImageResource(stateImageResId);
        }
    }

    public void showMessage(String message, int stateImageResId, final Runnable action) {
        showMessage(message, stateImageResId, -1, action);
    }

    public void showMessage(String message, final Runnable action) {
        showMessage(message, -1, -1, action);
    }

    public void showMessage(String message, int stateImageResId) {
        showMessage(message, stateImageResId, -1, null);
    }

    public void showMessage(String message) {
        showMessage(message, -1, -1, null);
    }

    public void showStateImage(int stateImageResId, final int actionTextResId, final Runnable action) {
        showMessage("", stateImageResId, actionTextResId, action);
    }

    public void showStateImage(int stateImageResId, final Runnable action) {
        showMessage("", stateImageResId, -1, action);
    }

    public void showStateImage(int stateImageResId) {
        showMessage("", stateImageResId, -1, null);
    }

    public void showActionButton(final int actionTextResId, final Runnable action) {
        showMessage("", -1, actionTextResId, action);
    }

    public void showActionButton(final Runnable action) {
        showMessage("", -1, -1, action);
    }

    public void showProgress() {
        dismissAllWithContent();
        pbProgress.setVisibility(View.VISIBLE);
    }

    public void showDimProgress() {
        showProgress();
        dimParentBackground();
    }

    // -------------------------> Components Getters

    public TextView getMessageTextView() {
        return tvMessage;
    }

    public ImageView getStateImageView() {
        return ivState;
    }

    public Button getActionButton() {
        return btnAction;
    }

    public ProgressWheel getProgressWheel() {
        return pbProgress;
    }

    // -------------------------> Edit Properties

    public void setColor(int color) {
        setProgressWheelColor(color);
        setMessageTextColor(color);
        setActionButtonBackgroundColor(color);
    }

    public void setMessageTextColor(int color) {
        getMessageTextView().setTextColor(color);
    }

    public void setActionButtonBackgroundColor(int color) {
        getActionButton().setBackgroundColor(color);
    }

    public void setActionButtonTextColor(int color) {
        getActionButton().setTextColor(color);
    }

    public void setProgressWheelColor(int color) {
        getProgressWheel().setBarColor(color);
    }

    // -------------------------> Read UI Attrs

    private void readAttributeSet(Context context, AttributeSet attrs, int defStyle) {
        TypedArray a;
        if (defStyle != -1) {
            a = context.obtainStyledAttributes(attrs, R.styleable.app, defStyle, 0);
        } else {
            a = context.obtainStyledAttributes(attrs, R.styleable.app);
        }
        int dimModeColor = getColor(context, a, R.styleable.app_dimModeColor);
        int messageTextColor = getColor(context, a, R.styleable.app_messageTextColor);
        int progressBarColor = getColor(context, a, R.styleable.app_progressBarColor);
        int actionButtonBgColor = getColor(context, a, R.styleable.app_actionButtonBgColor);
        int actionButtonTextColor = getColor(context, a, R.styleable.app_actionButtonTextColor);
        String messageText = getString(context, a, R.styleable.app_showMessage);
        boolean showProgress = getBoolean(context, a, R.styleable.app_showProgress);
        boolean showDimProgress = getBoolean(context, a, R.styleable.app_showDimProgress);
        int stateImageResId = a.getResourceId(R.styleable.app_showStateImage, -1);
        // Set Props
        if (dimModeColor != -1) mDimModeColor = dimModeColor;
        if (messageTextColor != -1) setMessageTextColor(messageTextColor);
        if (progressBarColor != -1) setProgressWheelColor(progressBarColor);
        if (actionButtonBgColor != -1) setActionButtonBackgroundColor(actionButtonBgColor);
        if (actionButtonTextColor != -1) setActionButtonTextColor(actionButtonTextColor);
        // Show
        if (messageText != null) showMessage(messageText);
        if (stateImageResId != -1) showMessage(messageText, stateImageResId);
        if (showProgress) showProgress();
        if (showDimProgress) showDimProgress();
        // Recycle
        a.recycle();
    }

    private int getColor(Context context, TypedArray a, int index) {
        int colorResId = a.getResourceId(index, -1);
        int color = a.getColor(index, -1);
        if (colorResId != -1) {
            return ContextCompat.getColor(context, colorResId);
        } else if (color != -1) {
            return color;
        } else {
            return -1;
        }
    }

    private boolean getBoolean(Context context, TypedArray a, int index) {
        int boolResId = a.getResourceId(index, -1);
        boolean bool = a.getBoolean(index, false);
        if (boolResId != -1) {
            return context.getResources().getBoolean(boolResId);
        } else if (bool) {
            return bool;
        } else {
            return false;
        }
    }

    private String getString(Context context, TypedArray a, int index) {
        int txtResId = a.getResourceId(index, -1);
        String txt = a.getString(index);
        if (txtResId != -1) {
            return context.getString(txtResId);
        } else if (txt != null) {
            return txt;
        } else {
            return null;
        }
    }

}

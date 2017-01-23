package com.abdallaadelessa.android.dataplaceholder;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by abdullah on 1/23/17.
 */

public class SimplePlaceHolder extends DataPlaceHolder {
    private int dimModeColor;
    private ImageView ivImage;
    private TextView tvMessage;
    private Button btnAction;
    private ProgressWheel pbProgress;

    //=================>

    public SimplePlaceHolder(Context context) {
        super(context);
    }

    public SimplePlaceHolder(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SimplePlaceHolder(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    //=================>

    @Override
    protected void initUI(Context context) {
        super.initUI(context);
        ivImage = (ImageView) findViewById(R.id.ivImage);
        tvMessage = (TextView) findViewById(R.id.tvMessage);
        btnAction = (Button) findViewById(R.id.btnAction);
        pbProgress = (ProgressWheel) findViewById(R.id.pbProgress);
    }

    @Override
    protected void initDefaultViews(Context context) {
        super.initDefaultViews(context);
        addErrorView(R.layout.simple_error_view);
    }

    @Override
    protected void readAttributeSet(Context context, AttributeSet attrs, int defStyle) {
        try {
            super.readAttributeSet(context, attrs, defStyle);
            TypedArray a;
            if (defStyle != -1) {
                a = context.obtainStyledAttributes(attrs, R.styleable.simplePlaceHolder, defStyle, 0);
            } else {
                a = context.obtainStyledAttributes(attrs, R.styleable.simplePlaceHolder);
            }
            String messageText = a.getString(R.styleable.simplePlaceHolder_showMessage);
            int progress = a.getInt(R.styleable.simplePlaceHolder_showProgress, -1);
            int imageResId = a.getResourceId(R.styleable.simplePlaceHolder_showImage, -1);
            int dimColor = a.getColor(R.styleable.simplePlaceHolder_dimColor, -1);
            int messageTextColor = a.getColor(R.styleable.simplePlaceHolder_messageTextColor, -1);
            int progressColor = a.getColor(R.styleable.simplePlaceHolder_progressColor, -1);
            int actionBgColor = a.getColor(R.styleable.simplePlaceHolder_actionBgColor, -1);
            int actionTextColor = a.getColor(R.styleable.simplePlaceHolder_actionTextColor, -1);
            int dimProgress = a.getInt(R.styleable.simplePlaceHolder_showDimProgress, -1);
            int imageImageWidth = (int) a.getDimension(R.styleable.simplePlaceHolder_imageWidth, -1);
            int imageImageHeight = (int) a.getDimension(R.styleable.simplePlaceHolder_imageHeight, -1);
            int progressSize = (int) a.getDimension(R.styleable.simplePlaceHolder_progressSize, -1);
            // Set Props
            if (dimColor != -1) {
                setDimColor(dimColor);
            }
            if (messageTextColor != -1) {
                setMessageColor(messageTextColor);
            }
            if (progressColor != -1) {
                setProgressColor(progressColor);
            }
            if (actionBgColor != -1) {
                setActionBackgroundColor(actionBgColor);
            }
            if (actionTextColor != -1) {
                setActionTextColor(actionTextColor);
            }
            if (imageImageWidth != -1) {
                getImageView().getLayoutParams().width = imageImageWidth;
            }
            if (imageImageHeight != -1) {
                getImageView().getLayoutParams().height = imageImageHeight;
            }
            if (progressSize != -1) {
                getProgressWheel().setCircleRadius(progressSize);
            }
            if (dimProgress != -1) {
                showDimProgress(dimProgress);
            } else {
                showMessage(messageText, progress, imageResId);
            }
            // Recycle
            a.recycle();
        } catch (Exception e) {
            logError(e);
        }
    }

    //=================> Components Getters

    public TextView getMessageTextView() {
        return tvMessage;
    }

    public ImageView getImageView() {
        return ivImage;
    }

    public Button getActionButton() {
        return btnAction;
    }

    public ProgressWheel getProgressWheel() {
        return pbProgress;
    }

    //=================> Edit Properties

    public void setColor(int color) {
        setProgressColor(color);
        setMessageColor(color);
        setActionBackgroundColor(color);
    }

    public void setProgressColor(int color) {
        getProgressWheel().setBarColor(color);
    }

    public void setMessageColor(int color) {
        getMessageTextView().setTextColor(color);
    }

    public void setActionBackgroundColor(int color) {
        getActionButton().setBackgroundColor(color);
    }

    public void setActionTextColor(int color) {
        getActionButton().setTextColor(color);
    }

    private void setDimColor(int dimModeColor) {
        this.dimModeColor = dimModeColor;
    }

    //=================>

    public void showMessage(String message, Drawable image, String actionString, int progress, boolean dimMode, final Runnable action) {
        dismissAll();
        hideDataView();
        if (message != null || image != null || actionString != null || progress != -1 || action != null) {
            //==> Message
            if (message != null) {
                getErrorView().setVisibility(VISIBLE);
                tvMessage.setVisibility(View.VISIBLE);
                tvMessage.setText(message);
            } else {
                tvMessage.setVisibility(View.GONE);
            }
            //==> Image
            if (image != null) {
                getErrorView().setVisibility(VISIBLE);
                ivImage.setVisibility(View.VISIBLE);
                ivImage.setImageDrawable(image);
            } else {
                ivImage.setVisibility(View.GONE);
            }
            //==> Action
            if (actionString != null) {
                getErrorView().setVisibility(VISIBLE);
                btnAction.setVisibility(View.VISIBLE);
                btnAction.setText(actionString);
            }
            if (action != null) {
                getErrorView().setVisibility(VISIBLE);
                btnAction.setVisibility(View.VISIBLE);
                btnAction.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        action.run();
                    }
                });
            } else {
                btnAction.setVisibility(GONE);
            }
            //==> Progress
            if (progress != -1) {
                getErrorView().setVisibility(VISIBLE);
                pbProgress.setVisibility(VISIBLE);
                if (progress == 0) {
                    //Indeterminate
                    pbProgress.spin();
                } else {
                    //Determinate
                    float instantProgress = ((float) progress) / 100.0f;
                    pbProgress.setInstantProgress(instantProgress);
                }
            } else {
                pbProgress.setVisibility(GONE);
            }
            //==> Dim Mode
            if (dimMode) {
                getContainer().setClickable(true);
                getContainer().setBackgroundColor(dimModeColor);
            } else {
                getContainer().setClickable(false);
                getContainer().setBackgroundColor(getResources().getColor(android.R.color.transparent));
            }
        }
    }

    public void showMessage(@StringRes int messageStringId, @DrawableRes int imageResId, final @StringRes int actionStringId, int progress, boolean dimMode, final Runnable action) {
        showMessage(getString(messageStringId), getResources().getDrawable(imageResId), getString(actionStringId), progress, dimMode, action);
    }

    public void showMessage(String message, @DrawableRes int imageResId, final String actionString, final Runnable action) {
        showMessage(message, getDrawable(imageResId), actionString, -1, false, action);
    }

    public void showMessage(String message, final String actionString, final Runnable action) {
        showMessage(message, null, actionString, -1, false, action);
    }

    public void showMessage(String message, @DrawableRes int imageResId, final Runnable action) {
        showMessage(message, getDrawable(imageResId), null, -1, false, action);
    }

    public void showMessage(String message, final Runnable action) {
        showMessage(message, null, null, -1, false, action);
    }

    public void showMessage(String message, @DrawableRes int imageResId) {
        showMessage(message, getDrawable(imageResId), null, -1, false, null);
    }

    public void showMessage(String message, int progress, @DrawableRes int imageResId) {
        showMessage(message, getDrawable(imageResId), null, progress, false, null);
    }

    public void showMessage(String message) {
        showMessage(message, null, null, -1, false, null);
    }

    public void showImage(@DrawableRes int imageResId, final String actionString, final Runnable action) {
        showMessage(null, getDrawable(imageResId), actionString, -1, false, action);
    }

    public void showImage(@DrawableRes int imageResId, final Runnable action) {
        showMessage(null, getDrawable(imageResId), null, -1, false, action);
    }

    public void showImage(@DrawableRes int imageResId) {
        showMessage(null, getDrawable(imageResId), null, -1, false, null);
    }

    public void showActionButton(final String actionString, final Runnable action) {
        showMessage(null, null, actionString, -1, false, action);
    }

    public void showActionButton(final Runnable action) {
        showMessage(null, null, null, -1, false, action);
    }

    public void showProgress(String message, int progress) {
        showMessage(message, null, null, progress, false, null);
    }

    public void showProgress(int progress) {
        showMessage(null, null, null, progress, false, null);
    }

    public void showDimProgress(String message, int progress) {
        showMessage(message, null, null, progress, true, null);
    }

    public void showDimProgress(int progress) {
        showMessage(null, null, null, progress, true, null);
    }

    public void showProgress() {
        showMessage(null, null, null, 0, false, null);
    }

    public void showDimProgress() {
        showMessage(null, null, null, 0, true, null);
    }

    //=================>

    @NonNull
    private String getString(@StringRes int messageStringId) {
        String text = null;
        if (messageStringId != -1) {
            text = getResources().getString(messageStringId);
        }
        return text;
    }

    private Drawable getDrawable(@DrawableRes int imageResId) {
        Drawable drawable = null;
        if (imageResId != -1) {
            drawable = getResources().getDrawable(imageResId);
        }
        return drawable;
    }

}

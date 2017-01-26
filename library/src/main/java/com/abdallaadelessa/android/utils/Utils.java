package com.abdallaadelessa.android.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by abdullah on 1/24/17.
 */

public class Utils {
    private static final String TAG_PLACE_HOLDER = "PlaceHolder";

    public static void updateViewSize(int width, int height, View view) {
        if (view != null) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            layoutParams.width = width;
            layoutParams.height = height;
            view.setLayoutParams(layoutParams);
        }
    }

    public static int getColor(Context context, int color) {
        return context.getResources().getColor(color);
    }

    public static String getString(Context context, @StringRes int messageStringId) {
        String text = null;
        if (messageStringId != -1) {
            text = context.getResources().getString(messageStringId);
        }
        return text;
    }

    public static Drawable getDrawable(Context context, @DrawableRes int imageResId) {
        Drawable drawable = null;
        if (imageResId != -1) {
            drawable = context.getResources().getDrawable(imageResId);
        }
        return drawable;
    }

    public static void logError(Exception e) {
        Log.e(TAG_PLACE_HOLDER, "PlaceHolder Error", e);
    }
}

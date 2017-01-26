package com.abdallaadelessa.android.placeholder;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by abdullah on 1/26/17.
 */

public class PlaceHolder extends BasePlaceHolder {

    public PlaceHolder(Context context) {
        super(context);
    }

    public PlaceHolder(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PlaceHolder(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    //=================> Show and Hide

    public void showLoadView() {
        super.showLoadView();
    }

    public void showErrorView() {
        super.showErrorView();
    }

    //=================> Components Getters and Setters

    public View getLoadView() {
        return super.getLoadView();
    }

    public View getErrorView() {
        return super.getErrorView();
    }

    //=================> set new View

    public void setLoadView(View loadView) {
        super.setLoadView(loadView);
    }

    public void setErrorView(View errorView) {
        super.setErrorView(errorView);
    }

    //=================> Add new View

    public void addLoadView(@LayoutRes int layout) {
        super.addLoadView(layout);
    }

    public void addErrorView(@LayoutRes int layout) {
        super.addErrorView(layout);
    }

    public void addLoadView(View loadView) {
        super.addLoadView(loadView);
    }

    public void addErrorView(View errorView) {
        super.addErrorView(errorView);
    }

    //=================>
}

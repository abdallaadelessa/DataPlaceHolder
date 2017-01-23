package com.abdallaadelessa.android.dataplaceholder;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.LayoutRes;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;


/**
 * Created by abdalla on 29/07/15.
 */
public class DataPlaceHolder extends RelativeLayout {
    protected static final String TAG_PLACE_HOLDER = "PlaceHolder";
    protected static final int ID_LOAD_VIEW = 111;
    protected static final int ID_ERROR_VIEW = 222;
    protected static final int ID_DATA_VIEW = 333;
    private FrameLayout container;
    private View loadView;
    private View errorView;
    private View dataView;
    private int dataViewId;
    private int errorViewId;
    private int loadViewId;

    //=================>

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
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (loadViewId != -1) {
            setLoadView(findViewById(loadViewId));
        }
        if (errorViewId != -1) {
            setErrorView(findViewById(errorViewId));
        }
        if (dataViewId != -1) {
            setDataView(findViewById(dataViewId));
        }
    }

    protected static void logError(Exception e) {
        Log.e(TAG_PLACE_HOLDER, "PlaceHolder Error", e);
    }

    //=================>

    protected void initUI(Context context) {
        try {
            inflate(context, R.layout.dataplaceholder, this);
            container = (FrameLayout) findViewById(R.id.dataPlaceHolderContainer);
            initDefaultViews(context);
        } catch (Exception e) {
            logError(e);
        }
    }

    protected void initDefaultViews(Context context) {
        addLoadView(new View(context));
        addErrorView(new View(context));
        addDataView(new View(context));
    }

    protected void readAttributeSet(Context context, AttributeSet attrs, int defStyle) {
        try {
            TypedArray a;
            if (defStyle != -1) {
                a = context.obtainStyledAttributes(attrs, R.styleable.dataPlaceHolder, defStyle, 0);
            } else {
                a = context.obtainStyledAttributes(attrs, R.styleable.dataPlaceHolder);
            }
            loadViewId = a.getResourceId(R.styleable.dataPlaceHolder_loadViewId, -1);
            errorViewId = a.getResourceId(R.styleable.dataPlaceHolder_errorViewId, -1);
            dataViewId = a.getResourceId(R.styleable.dataPlaceHolder_dataViewId, -1);
            //===============>

            int loadViewLayoutId = a.getResourceId(R.styleable.dataPlaceHolder_loadLayoutId, -1);
            int errorViewLayoutId = a.getResourceId(R.styleable.dataPlaceHolder_errorLayoutId, -1);
            int dataViewLayoutId = a.getResourceId(R.styleable.dataPlaceHolder_dataLayoutId, -1);

            if (loadViewLayoutId != -1) {
                addLoadView(loadViewLayoutId);
            }
            if (errorViewLayoutId != -1) {
                addErrorView(errorViewLayoutId);
            }
            if (dataViewLayoutId != -1) {
                addDataView(dataViewLayoutId);
            }
            // Recycle
            a.recycle();
        } catch (Exception e) {
            logError(e);
        }
    }

    //===>

    protected View findChildViewById(int id) {
        View view = null;
        for (int i = 0; i < getChildCount(); i++) {
            View viewAtIndex = getChildAt(i);
            if (viewAtIndex.getId() == id) {
                view = viewAtIndex;
                break;
            }
        }
        return view;
    }

    protected void replaceContainerView(int id, View view) {
        Object lastTag = null;
        int lastVisibilityState = View.VISIBLE;
        View viewById = findChildViewById(id);
        if (viewById != null) {
            // Remove Old
            lastTag = viewById.getTag();
            lastVisibilityState = viewById.getVisibility();
            container.removeView(viewById);
        }
        // Add
        view.setId(id);
        if (lastTag != null) view.setTag(lastTag);
        view.setVisibility(lastVisibilityState);
        container.addView(view);
    }

    //=================> Show and Hide

    public void dismissAll() {
        if (getLoadView() != null) {
            getLoadView().setVisibility(GONE);
        }
        if (getErrorView() != null) {
            getErrorView().setVisibility(GONE);
        }
        if (getDataView() != null) {
            getDataView().setVisibility(VISIBLE);
        }
    }

    public void hideDataView() {
        if (getDataView() != null) {
            getDataView().setVisibility(GONE);
        }
    }

    public void showLoadView() {
        dismissAll();
        if (getLoadView() != null) {
            getLoadView().setVisibility(VISIBLE);
        }
    }

    public void showErrorView() {
        dismissAll();
        if (getErrorView() != null) {
            getErrorView().setVisibility(VISIBLE);
        }
    }

    public void showDataView() {
        dismissAll();
        if (getDataView() != null) {
            getDataView().setVisibility(VISIBLE);
        }
    }

    //=================> Components Getters and Setters

    public FrameLayout getContainer() {
        return container;
    }

    public void setLoadView(View loadView) {
        this.loadView = loadView;
    }

    public void setErrorView(View errorView) {
        this.errorView = errorView;
    }

    public void setDataView(View dataView) {
        this.dataView = dataView;
    }

    public View getLoadView() {
        return loadView;
    }

    public View getErrorView() {
        return errorView;
    }

    public View getDataView() {
        return dataView;
    }

    //=================>

    public void addLoadView(@LayoutRes int layout) {
        addLoadView(LayoutInflater.from(getContext()).inflate(layout, null));
    }

    public void addErrorView(@LayoutRes int layout) {
        addErrorView(LayoutInflater.from(getContext()).inflate(layout, null));
    }

    public void addDataView(@LayoutRes int layout) {
        addDataView(LayoutInflater.from(getContext()).inflate(layout, null));
    }

    public void addLoadView(View loadView) {
        this.loadView = loadView;
        replaceContainerView(ID_LOAD_VIEW, loadView);
    }

    public void addErrorView(View errorView) {
        this.errorView = errorView;
        replaceContainerView(ID_ERROR_VIEW, errorView);
    }

    public void addDataView(View dataView) {
        this.dataView = dataView;
        replaceContainerView(ID_DATA_VIEW, dataView);
    }

    //=================>

}

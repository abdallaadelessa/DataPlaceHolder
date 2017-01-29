package com.abdallaadelessa.android.placeholder;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.LayoutRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.abdallaadelessa.android.utils.Utils;


/**
 * Created by abdalla on 29/07/15.
 */
public abstract class BasePlaceHolder extends FrameLayout {
    protected static final int ID_LOAD_VIEW = 111;
    protected static final int ID_ERROR_VIEW = 222;
    protected static final int ID_DATA_VIEW = 333;
    protected FrameLayout container;
    protected PlaceHolderListener listener;
    protected View loadView;
    protected View errorView;
    protected View dataView;
    protected int dataViewId;
    protected int errorViewId;
    protected int loadViewId;
    //=================>

    public BasePlaceHolder(Context context) {
        super(context);
        initUI(context);
    }

    public BasePlaceHolder(Context context, AttributeSet attrs) {
        super(context, attrs);
        initUI(context);
        readAttributeSet(context, attrs, -1);
    }

    public BasePlaceHolder(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initUI(context);
        readAttributeSet(context, attrs, defStyle);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        onAttachViews(getContainer());
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (listener != null) {
            listener.onDetach();
        }
    }

    //=================>

    protected void initUI(Context context) {
        try {
            container = this;
            initDefaultViews(context);
        } catch (Exception e) {
            Utils.logError(e);
        }
    }

    protected void initDefaultViews(Context context) {

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
            Utils.logError(e);
        }
    }

    public void setListener(PlaceHolderListener listener) {
        this.listener = listener;
    }

    //===>

    protected void onAttachViews(ViewGroup viewGroup) {
        if (loadViewId != -1) {
            View viewById = viewGroup.findViewById(loadViewId);
            if (viewById != null) {
                setLoadView(viewById);
            }
        }
        if (errorViewId != -1) {
            View viewById = viewGroup.findViewById(errorViewId);
            if (viewById != null) {
                setErrorView(viewById);
            }
        }
        if (dataViewId != -1) {
            View viewById = viewGroup.findViewById(dataViewId);
            if (viewById != null) {
                setDataView(viewById);
            }
        }
        dismissAll();
        if (listener != null) {
            listener.onAttach();
        }
    }

    protected View findChildViewById(int id) {
        View view = null;
        for (int i = 0; i < getContainer().getChildCount(); i++) {
            View viewAtIndex = getContainer().getChildAt(i);
            if (viewAtIndex.getId() == id) {
                view = viewAtIndex;
                break;
            }
        }
        return view;
    }

    protected void replaceAndAddContainerView(int id, View view) {
        Object lastTag = null;
        int lastVisibilityState = View.VISIBLE;
        View viewById = findChildViewById(id);
        if (viewById != null) {
            // Remove Old
            lastTag = viewById.getTag();
            lastVisibilityState = viewById.getVisibility();
            getContainer().removeView(viewById);
        }
        if (view != null) {
            // Add
            view.setId(id);
            if (lastTag != null) view.setTag(lastTag);
            view.setVisibility(lastVisibilityState);
            getContainer().addView(view);
        }
    }

    protected void replaceAndSetContainerView(int id, View view) {
        if (getContainer() == null) return;
        Object lastTag = null;
        int lastVisibilityState = View.VISIBLE;
        View viewById = getContainer().findViewById(id);
        if (viewById != null) {
            // Remove Old
            lastTag = viewById.getTag();
            lastVisibilityState = viewById.getVisibility();
            getContainer().removeView(viewById);
        }
        if (view != null) {
            // Set
            if (lastTag != null) view.setTag(lastTag);
            view.setVisibility(lastVisibilityState);
        }
    }

    //=================> Show and Hide

    public void dismissAll() {
        if (getContainer() != null) {
            Utils.updateViewSize(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, getContainer());
        }
        if (getLoadView() != null) {
            getLoadView().setVisibility(GONE);
        }
        if (getErrorView() != null) {
            Utils.updateViewSize(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, getErrorView());
            getErrorView().setVisibility(GONE);
        }
        if (getDataView() != null) {
            getDataView().setVisibility(VISIBLE);
        }
    }

    protected void showLoadView() {
        dismissAll();
        if (getLoadView() != null) {
            getLoadView().setVisibility(VISIBLE);
        }
    }

    protected void showErrorView() {
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

    protected View getLoadView() {
        return loadView;
    }

    protected View getErrorView() {
        return errorView;
    }

    public View getDataView() {
        return dataView;
    }

    //=================> set new View

    protected void setLoadView(View loadView) {
        this.loadView = loadView;
        replaceAndSetContainerView(ID_LOAD_VIEW, loadView);
    }

    protected void setErrorView(View errorView) {
        this.errorView = errorView;
        replaceAndSetContainerView(ID_ERROR_VIEW, errorView);
    }

    public void setDataView(View dataView) {
        this.dataView = dataView;
        replaceAndSetContainerView(ID_DATA_VIEW, dataView);
    }

    //=================> Add new View

    protected void addLoadView(@LayoutRes int layout) {
        addLoadView(LayoutInflater.from(getContext()).inflate(layout, null));
    }

    protected void addErrorView(@LayoutRes int layout) {
        addErrorView(LayoutInflater.from(getContext()).inflate(layout, null));
    }

    public void addDataView(@LayoutRes int layout) {
        addDataView(LayoutInflater.from(getContext()).inflate(layout, null));
    }

    protected void addLoadView(View loadView) {
        this.loadView = loadView;
        replaceAndAddContainerView(ID_LOAD_VIEW, loadView);
    }

    protected void addErrorView(View errorView) {
        this.errorView = errorView;
        replaceAndAddContainerView(ID_ERROR_VIEW, errorView);
    }

    public void addDataView(View dataView) {
        this.dataView = dataView;
        replaceAndAddContainerView(ID_DATA_VIEW, dataView);
    }

    //=================>

    public interface PlaceHolderListener {
        void onAttach();

        void onDetach();
    }
}

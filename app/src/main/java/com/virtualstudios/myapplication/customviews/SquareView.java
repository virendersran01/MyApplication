package com.virtualstudios.myapplication.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.virtualstudios.myapplication.R;

public class SquareView extends View {
    public SquareView(Context context) {
        super(context);
        init();
    }

    public SquareView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SquareView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int size = Math.min(getMeasuredWidth(), getMeasuredHeight());
        setMeasuredDimension(size, size);
    }
}

package com.virtualstudios.myapplication.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.virtualstudios.myapplication.R;


public class Pizza extends View {
    private Paint paint;
    private int mNumWedges = 5;
    private int mColor = Color.YELLOW;
    private int mStrokeWidth = 4;

    public Pizza(Context context) {
        super(context);
        init();
    }

    public Pizza(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
        obtainAttributes(context, attrs);
    }

    public Pizza(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        obtainAttributes(context, attrs);
    }

    private void obtainAttributes(Context context, @Nullable AttributeSet attrs){
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Pizza);
        mNumWedges = typedArray.getInt(R.styleable.Pizza_color,mNumWedges);
        mColor = typedArray.getColor(R.styleable.Pizza_color, mColor);
        mStrokeWidth = typedArray.getDimensionPixelSize(R.styleable.Pizza_stroke_width, mStrokeWidth);
        typedArray.recycle();

    }

    private void init(){
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(mStrokeWidth);
        paint.setColor(mColor);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        final int width = getWidth() - getPaddingLeft() - getPaddingRight();
        final int height = getHeight() - getPaddingTop() - getPaddingBottom();
        final int cx = width / 2 + getPaddingLeft();
        final int cy = height / 2 + getPaddingTop();
        final float diameter = Math.min(width, height) - paint.getStrokeWidth();
        final float radius = diameter / 2;

        canvas.drawCircle(cx, cy, radius, paint);
        drawPizzaCuts(canvas, cx, cy, radius);
    }

    private void drawPizzaCuts(Canvas canvas, float cx, float cy, float radius){
        final float degree = 360f / mNumWedges;
        canvas.save();
        for (int i=0;i<mNumWedges;i++) {
            canvas.rotate(degree, cx, cy);
            canvas.drawLine(cx, cy, cx, cy - radius, paint);
        }
        canvas.restore();
    }
}

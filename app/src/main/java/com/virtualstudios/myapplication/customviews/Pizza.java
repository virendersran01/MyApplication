package com.virtualstudios.myapplication.customviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.virtualstudios.myapplication.R;

public class Pizza extends View {
    private Paint paint;

    public Pizza(Context context) {
        super(context);
        init();
    }

    public Pizza(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Pizza(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(8);
        paint.setColor(Color.YELLOW);
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
        final int numWedges = 5;
        final float degree = 360f / numWedges;
        canvas.save();
        for (int i=0;i<numWedges;i++) {
            canvas.rotate(degree, cx, cy);
            canvas.drawLine(cx, cy, cx, cy - radius, paint);
        }
        canvas.restore();
    }
}

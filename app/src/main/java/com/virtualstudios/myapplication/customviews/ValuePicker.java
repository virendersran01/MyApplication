package com.virtualstudios.myapplication.customviews;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.virtualstudios.myapplication.R;

public class ValuePicker extends LinearLayout {
    private static final String KEY_SUPER_STATE = "superState";
    private static final String KEY_VALUE = "value";
    private ImageButton buttonAdd;
    private ImageButton buttonSub;
    private TextView textView;
    private int mValue = 0;
    private OnChangeListener mListener = null;

    public ValuePicker(Context context) {
        super(context);
        init();
    }

    public ValuePicker(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ValuePicker(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.layout_custom_view, this);
        buttonSub = findViewById(R.id.button_minus);
        buttonAdd = findViewById(R.id.button_add);
        textView = findViewById(R.id.text_value);
        textView.setText(String.valueOf(mValue));
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER);

        View.OnClickListener onClickListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.button_add:
                        mValue++;
                        textView.setText(String.valueOf(mValue));
                        if (mListener != null){
                            mListener.onChangeValue(mValue);
                        }
                        break;
                    case R.id.button_minus:
                        if (mValue > 0) {
                            mValue--;
                            textView.setText(String.valueOf(mValue));
                            if (mListener != null){
                                mListener.onChangeValue(mValue);
                            }
                        }
                        break;
                }
            }
        };

        buttonAdd.setOnClickListener(onClickListener);
        buttonSub.setOnClickListener(onClickListener);
    }

    @Nullable
    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_SUPER_STATE, super.onSaveInstanceState());
        bundle.putInt(KEY_VALUE, mValue);
        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            mValue = bundle.getInt(KEY_VALUE);
            super.onRestoreInstanceState(bundle.getParcelable(KEY_SUPER_STATE));
        } else {
            super.onRestoreInstanceState(state);
        }
        textView.setText(String.valueOf(mValue));
    }

    public void setOnChangeListener(OnChangeListener listener){
        this.mListener = listener;
    }

    public int getValue(){
        return mValue;
    }

    public interface OnChangeListener{
        void onChangeValue(int value);
    }
}

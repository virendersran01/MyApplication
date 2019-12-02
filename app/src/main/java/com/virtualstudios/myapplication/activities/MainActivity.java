package com.virtualstudios.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.virtualstudios.myapplication.R;
import com.virtualstudios.myapplication.customviews.ValuePicker;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.valuePicker1)
    ValuePicker valuePicker1;
    @BindView(R.id.valuePicker2)
    ValuePicker valuePicker2;
    @BindView(R.id.textView)
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ValuePicker.OnChangeListener onChangeListener = new ValuePicker.OnChangeListener() {
            @Override
            public void onChangeValue(int value) {
                int result = valuePicker1.getValue() * valuePicker2.getValue();
                textView.setText(String.valueOf(result));
                if (result == 5){
                    startActivity(new Intent(MainActivity.this, PhotoSpiralActivity.class));
                }
            }
        };

        valuePicker1.setOnChangeListener(onChangeListener);
        valuePicker2.setOnChangeListener(onChangeListener);
    }
}

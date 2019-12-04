package com.virtualstudios.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.button.MaterialButton;
import com.virtualstudios.myapplication.R;
import com.virtualstudios.myapplication.adapters.AdapterIntro;
import com.virtualstudios.myapplication.model.ModelIntro;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IntroActivity extends AppCompatActivity {
    @BindView(R.id.viewPager)
    ViewPager2 mViewPager;
    @BindView(R.id.viewNext)
    MaterialButton mButtonNext;
    @BindView(R.id.viewGroupDotsIndicator)
    LinearLayout mDotsIndicator;
    private AdapterIntro mAdapterIntro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        ButterKnife.bind(this);
        setUpData();
        mViewPager.setAdapter(mAdapterIntro);
        setUpDotsIndicator();
        setCurrentIndicator(0);

        mViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentIndicator(position);
            }
        });

        mButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mViewPager.getCurrentItem() + 1 < mAdapterIntro.getItemCount()){
                    mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
                }else {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }
            }
        });
    }

    private void setUpData(){
        List<ModelIntro> introItems = new ArrayList<>();
        ModelIntro modelIntro1 = new ModelIntro();
        ModelIntro modelIntro2 = new ModelIntro();
        ModelIntro modelIntro3 = new ModelIntro();
        modelIntro1.setImage(R.drawable.superman1);
        modelIntro2.setImage(R.drawable.superman2);
        modelIntro3.setImage(R.drawable.superman3);
        modelIntro1.setTitle("Superman");
        modelIntro2.setTitle("Superman");
        modelIntro3.setTitle("Superman");
        modelIntro1.setDescription("Superman is worlds most power super hero");
        modelIntro2.setDescription("Superman is worlds most power super hero");
        modelIntro3.setDescription("Superman is worlds most power super hero");
        introItems.add(modelIntro1);
        introItems.add(modelIntro2);
        introItems.add(modelIntro3);

        mAdapterIntro = new AdapterIntro(introItems);
    }

    private void setUpDotsIndicator(){
        ImageView[] indicators = new ImageView[mAdapterIntro.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(8,0,8,0);

        for (int i = 0; i <indicators.length ; i++) {
            indicators[i] = new ImageView(getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.indicator_inactive));
            indicators[i].setLayoutParams(layoutParams);
            mDotsIndicator.addView(indicators[i]);
        }
    }

    private void setCurrentIndicator(int index){
        int childCount = mDotsIndicator.getChildCount();

        for (int i = 0; i <childCount ; i++) {
            ImageView imageView = (ImageView) mDotsIndicator.getChildAt(i);
            if (i == index){
                imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.indicator_active));
            }else{
                imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.indicator_inactive));
            }

        }
        if (index == mAdapterIntro.getItemCount() -1){
            mButtonNext.setText("Start");
        }else {
            mButtonNext.setText("Next");
        }
    }
}

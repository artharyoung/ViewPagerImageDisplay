package com.github.artharyoung.viewpagerimagedisplay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.github.artharyoung.viewpagerimagedisplay.adapter.ShowImageAdapter;

public class ShowImageActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{

    private static final String TAG = "ImageShowActivity";

    private RadioGroup mRadioGroup;

    private String[] picInfo;
    private int defaultIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image);

        Intent intent = getIntent();
        picInfo = intent.getStringArrayExtra("url");
        defaultIndex = intent.getIntExtra("current", 0);

        init();
    }

    private void init( ){

        mRadioGroup = (RadioGroup) findViewById(R.id.image_show_select);
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                Log.d(TAG, "mRadioGroup===========onCheckedChanged: " + i);

                RadioButton tempButton = (RadioButton)mRadioGroup.findViewById(i);
                if (tempButton != null) {
                    if(tempButton.isChecked()){

                        tempButton.setButtonDrawable(R.drawable.game_banner_dot_enable);
                    }else{
                        tempButton.setButtonDrawable(R.drawable.game_banner_dot_normal);
                    }

                }
            }
        });

        mRadioGroup.removeAllViews();
        int length = picInfo.length;
        for (int i = 0; i < length; i++) {
            RadioButton tempButton = new RadioButton(this);
            tempButton.setButtonDrawable(R.drawable.game_banner_dot_normal);

            RadioGroup.LayoutParams childParams = new RadioGroup.LayoutParams(
                    RadioGroup.LayoutParams.WRAP_CONTENT,
                    RadioGroup.LayoutParams.WRAP_CONTENT);
            childParams.gravity = Gravity.CENTER_HORIZONTAL;
            childParams.setMargins((int)getResources().getDimension(R.dimen.ui_layout_margin),0,
                    (int)getResources().getDimension(R.dimen.ui_layout_margin),0);
            tempButton.setId(i);

            mRadioGroup.addView(tempButton,childParams);

        }

        ViewPager pager = (ViewPager) findViewById(R.id.image_show_viewpager);
        pager.addOnPageChangeListener(this);

        if (picInfo != null) {
            ShowImageAdapter adapter = new ShowImageAdapter(this,picInfo);
            pager.setAdapter(adapter);
            pager.setCurrentItem(defaultIndex);
            mRadioGroup.check(defaultIndex);
        }
    }

    @Override
    public void onPageSelected(int position) {
        Log.d(TAG, "=========onPageSelected: " + position);
        mRadioGroup.check(position);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}

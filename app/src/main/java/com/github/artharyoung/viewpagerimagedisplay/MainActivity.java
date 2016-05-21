package com.github.artharyoung.viewpagerimagedisplay;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.widget.LinearLayout;

import com.github.artharyoung.viewpagerimagedisplay.adapter.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity{
    private static final String TAG = "MainActivity";
    private String[] mPics={
            "http://res.vsg3.com/20160519061256_77373.jpg",
            "http://res.vsg3.com/20160519061321_83560.jpg",
            "http://res.vsg3.com/20160519061332_93483.jpg",
            "http://res.vsg3.com/20160519061352_94104.jpg",
            "http://res.vsg3.com/20160519061401_75129.jpg"
    };

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = (ViewPager)findViewById(R.id.viewpager);
        mViewPager.setOffscreenPageLimit(2);
        //获取屏幕像素相关信息
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        //根据屏幕信息设置ViewPager容器的宽高,为了适配pad和手机
        mViewPager.setLayoutParams(new LinearLayout.LayoutParams(dm.widthPixels, dm.heightPixels * 1/3));

        mViewPager.setAdapter(new ViewPagerAdapter(this,mPics));

    }
}

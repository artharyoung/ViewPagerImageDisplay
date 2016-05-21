package com.github.artharyoung.viewpagerimagedisplay.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.github.artharyoung.viewpagerimagedisplay.R;
import com.github.artharyoung.viewpagerimagedisplay.ShowImageActivity;
import com.github.artharyoung.viewpagerimagedisplay.tools.ImageLoaderUtil;

/**
 * Created by syamiadmin on 2016/5/20.
 */
public class ViewPagerAdapter extends PagerAdapter {

    private static final String TAG = "ViewPagerAdapter";
    private Context context;
    private String[] mPics;

    public ViewPagerAdapter(Context context, String ...urlList) {
        this.context = context;
        this.mPics = urlList;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        ImageView imageView = new ImageView(context);
        imageView.setPadding((int)context.getResources().getDimension(R.dimen.ui_layout_margin),0,
                (int)context.getResources().getDimension(R.dimen.ui_layout_margin),0);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "=================onClick: " + position);
                Intent intent = new Intent(context, ShowImageActivity.class);
                intent.putExtra("url", mPics);
                intent.putExtra("current", position);
                context.startActivity(intent);
            }
        });

        ImageLoaderUtil.LoadImageView(context,imageView,mPics[position]);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return mPics.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public float getPageWidth(int position) {
        return 1/3f;
    }
}

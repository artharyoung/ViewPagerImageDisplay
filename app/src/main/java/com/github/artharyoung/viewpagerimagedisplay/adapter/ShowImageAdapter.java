package com.github.artharyoung.viewpagerimagedisplay.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.github.artharyoung.viewpagerimagedisplay.tools.ImageLoaderUtil;


/**
 * Created by syamiadmin on 2016/5/20.
 */
public class ShowImageAdapter extends PagerAdapter implements View.OnClickListener {

    private Context mContext;
    private String[] mPics;

    public ShowImageAdapter(Context context, String... list) {
        mContext = context;
        mPics = list;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        ImageView imageView = new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        ImageLoaderUtil.LoadImageView(mContext, imageView, mPics[position]);
        container.addView(imageView);
        imageView.setOnClickListener(this);
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
    public void onClick(View view) {
        ((Activity) mContext).finish();
    }
}

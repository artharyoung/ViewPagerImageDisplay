package com.github.artharyoung.viewpagerimagedisplay.tools;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.github.artharyoung.viewpagerimagedisplay.R;

/**
 * Created by syamiadmin on 2016/5/20.
 */
public class ImageLoaderUtil {

    public static void LoadImageView(Context context, ImageView view, String imgUrl) {
        Glide.with(context.getApplicationContext())
                .load(imgUrl)
                .placeholder(R.mipmap.ic_launcher)
                .crossFade()
                .into(view);
    }
}

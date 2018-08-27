package com.huaao.ejingwu.common.utils;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.huaao.ejingwu.common.R;

/**
 * @author: xzp
 * @date: 2018/8/14
 * @desc: glide工具类
 */
public class GlideUtils {

    private static final int placeholder = R.drawable.ic_launcher;
    private static final float blurRadius = 50;

    public static void loadImage(@NonNull Context context, String imageUrl, @NonNull ImageView imageView) {
        loadImage(context, imageUrl, placeholder, imageView);
    }

    public static void loadImage(@NonNull Context context, String imageUrl, int placeholder, @NonNull ImageView imageView) {
        RequestOptions options = new RequestOptions().placeholder(placeholder).centerCrop();
        Glide.with(context).load(imageUrl).apply(options).into(imageView);
    }

    public static void loadImage(@NonNull Context context, @DrawableRes int imageRes, @NonNull ImageView imageView) {
        loadImage(context, imageRes, placeholder, imageView);
    }

    public static void loadImage(@NonNull Context context, @DrawableRes int imageRes, int placeholder, @NonNull ImageView imageView) {
        RequestOptions options = new RequestOptions().placeholder(placeholder).centerCrop();
        Glide.with(context).load(imageRes).apply(options).into(imageView);
    }

    public static void loadCircleImage(@NonNull Context context, String imageUrl, @NonNull ImageView imageView) {
        loadCircleImage(context, imageUrl, placeholder, imageView);
    }

    public static void loadCircleImage(@NonNull Context context, String imageUrl, int placeholder, @NonNull ImageView imageView) {
        RequestOptions options = new RequestOptions().centerCrop().transform(new CircleCrop()).placeholder(placeholder);
        Glide.with(context).load(imageUrl).apply(options).into(imageView);
    }

    public static void loadCircleImage(@NonNull Context context, @DrawableRes int imageRes, @NonNull ImageView imageView) {
        loadCircleImage(context, imageRes, placeholder, imageView);
    }

    public static void loadCircleImage(@NonNull Context context, @DrawableRes int imageRes, int placeholder, @NonNull ImageView imageView) {
        RequestOptions options = new RequestOptions().centerCrop().placeholder(placeholder).transform(new CircleCrop()).placeholder(placeholder);
        Glide.with(context).load(imageRes).apply(options).into(imageView);
    }

    public static void loadRoundedCornerImage(@NonNull Context context, String imageUrl, int roundingRadius, @NonNull ImageView imageView) {
        loadRoundedCornerImage(context, imageUrl, placeholder, roundingRadius, imageView);
    }

    public static void loadRoundedCornerImage(@NonNull Context context, String imageUrl, int placeholder, int roundingRadius, @NonNull ImageView imageView) {
        RequestOptions options = new RequestOptions().placeholder(placeholder).transforms(new CenterCrop(), new RoundedCorners(roundingRadius));
        Glide.with(context).load(imageUrl).apply(options).into(imageView);
    }

    public static void loadRoundedCornerImage(@NonNull Context context, @DrawableRes int imageRes, int roundingRadius, @NonNull ImageView imageView) {
        loadRoundedCornerImage(context, imageRes, placeholder, roundingRadius, imageView);
    }

    public static void loadRoundedCornerImage(@NonNull Context context, @DrawableRes int imageRes, int placeholder, int roundingRadius, @NonNull ImageView imageView) {
        RequestOptions options = new RequestOptions().placeholder(placeholder).transforms(new CenterCrop(), new RoundedCorners(roundingRadius));
        Glide.with(context).load(imageRes).apply(options).into(imageView);
    }

    public static void loadGifImage(@NonNull Context context, String imageUrl, @NonNull ImageView imageView) {
        loadGifImage(context, imageUrl, placeholder, imageView);
    }

    public static void loadGifImage(@NonNull Context context, String imageUrl, int placeholder, @NonNull ImageView imageView) {
        RequestOptions options = new RequestOptions().placeholder(placeholder).centerCrop();
        Glide.with(context).asGif().load(imageUrl).apply(options).into(imageView);
    }

    public static void loadGifImage(@NonNull Context context, @DrawableRes int imageRes, @NonNull ImageView imageView) {
        loadGifImage(context, imageRes, placeholder, imageView);
    }

    public static void loadGifImage(@NonNull Context context, @DrawableRes int imageRes, int placeholder, @NonNull ImageView imageView) {
        RequestOptions options = new RequestOptions().placeholder(placeholder).centerCrop();
        Glide.with(context).asGif().load(imageRes).apply(options).into(imageView);
    }

    public static void loadBlurImage(@NonNull Context context, String imageUrl, @NonNull ImageView imageView) {
        loadBlurImage(context, imageUrl, placeholder, blurRadius, imageView);
    }

    public static void loadBlurImage(@NonNull Context context, String imageUrl, float blurRadius, @NonNull ImageView imageView) {
        loadBlurImage(context, imageUrl, placeholder, blurRadius, imageView);
    }

    public static void loadBlurImage(@NonNull Context context, String imageUrl, int placeholder, @NonNull ImageView imageView) {
        loadBlurImage(context, imageUrl, placeholder, blurRadius, imageView);
    }

    public static void loadBlurImage(@NonNull Context context, String imageUrl, int placeholder, float blurRadius, @NonNull ImageView imageView) {
        RequestOptions options = new RequestOptions().placeholder(placeholder).transform(new GlideBlurTransformation(context, blurRadius));
        Glide.with(context).load(imageUrl).apply(options).into(imageView);
    }

    public static void loadBlurImage(@NonNull Context context, @DrawableRes int imageRes, @NonNull ImageView imageView) {
        loadBlurImage(context, imageRes, placeholder, blurRadius, imageView);
    }

    public static void loadBlurImage(@NonNull Context context, @DrawableRes int imageRes, float blurRadius, @NonNull ImageView imageView) {
        loadBlurImage(context, imageRes, placeholder, blurRadius, imageView);
    }

    public static void loadBlurImage(@NonNull Context context, @DrawableRes int imageRes, int placeholder, @NonNull ImageView imageView) {
        loadBlurImage(context, imageRes, placeholder, blurRadius, imageView);
    }

    public static void loadBlurImage(@NonNull Context context, @DrawableRes int imageRes, int placeholder, float blurRadius, @NonNull ImageView imageView) {
        RequestOptions options = new RequestOptions().placeholder(placeholder).transform(new GlideBlurTransformation(context, blurRadius));
        Glide.with(context).load(imageRes).apply(options).into(imageView);
    }
}

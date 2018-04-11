package com.example.yuekaole;

import android.graphics.Bitmap;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

/**
 * author:Created by WangZhiQiang on 2018/4/7.
 */

class ImageLoaderUtils {

    public static DisplayImageOptions getDisplayImageOption() {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)//设置下载的图片是否缓存在内存中
                .cacheOnDisk(true)
                .considerExifParams(true)  //是否考虑JPEG图像EXIF参数（旋转，翻转）
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)//设置图片以如何的编码方式显示
                .bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型//
                .displayer(new RoundedBitmapDisplayer(5))//是否设置为圆角，弧度为多少
//                .displayer(new FadeInBitmapDisplayer(100))//是否图片加载好后渐入的动画时间
                .build();//构建完成
        return options;
    }
}

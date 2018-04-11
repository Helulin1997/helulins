package com.example.yuekaole;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * author:Created by WangZhiQiang on 2018/4/7.
 */

public class MApp extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoaderConfiguration   configuration=new   ImageLoaderConfiguration.Builder(this).build();
        ImageLoader instance = ImageLoader.getInstance();
        instance.init(configuration);

    }
}

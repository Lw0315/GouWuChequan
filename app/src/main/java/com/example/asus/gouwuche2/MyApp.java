package com.example.asus.gouwuche2;

import android.app.Application;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import common.CrashHandler;

/**
 * Created by asus on 2017/10/25.
 */

public class MyApp  extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        DisplayImageOptions op=new DisplayImageOptions.Builder()
                .build();
        ImageLoaderConfiguration con=new ImageLoaderConfiguration.Builder(this)
                .defaultDisplayImageOptions(op)
                .build();
        ImageLoader.getInstance().init(con);
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());
    }
}

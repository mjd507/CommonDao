package com.cleaner.commondao.test.sample;

import android.app.Application;

import com.cleaner.commondao.lib.DbManager;


/**
 * 描述:
 * Created by mjd on 2017/1/12.
 */

public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        //初始化 数据库
        DbManager.DbParams params = new DbManager.DbParams();
        params.dbName = "TuHu.db";
        params.dbVersion = 1;
        DbManager.getInstance().init(this, params);


    }

}

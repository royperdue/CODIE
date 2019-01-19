package com.codie.simulation;


import android.app.Application;
import android.content.Context;

import com.codie.simulation.data.model.DaoMaster;
import com.codie.simulation.data.model.DaoSession;

import org.greenrobot.greendao.database.Database;


public class MyApplication extends Application {

    private static Context context;
    private static DaoSession daoSession;

    public void onCreate() {
        super.onCreate();
        MyApplication.context = getApplicationContext();

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "codie-db");
        Database db = helper.getWritableDb();

        daoSession = new DaoMaster(db).newSession();
    }

    public static Context getAppContext() {
        return MyApplication.context;
    }

    public static DaoSession getDaoSession() {
        return daoSession;
    }
}

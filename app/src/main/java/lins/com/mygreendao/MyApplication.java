package lins.com.mygreendao;

import android.app.Application;
import android.content.Context;

/**
 * Created by LINS on 2017/3/7.
 */

public class MyApplication extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        GreenDaoManager.getInstance();
    }

    public static Context getContext() {
        return mContext;
    }
}
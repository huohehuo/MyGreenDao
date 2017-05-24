package lins.com.mygreendao;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import lins.com.mygreendao.gen.DaoMaster;
import lins.com.mygreendao.gen.DaoSession;
import lins.com.mygreendao.gen.UserDao;
import lins.com.mygreendao.utils.PreferencesUtils;
import lins.com.mygreendao.utils.UpgradeHelper;

/**
 * Created by LINS on 2017/3/7.
 */

public class MyApplication extends Application {
    private static Context mContext;
    private DaoSession mDaoSession;


    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        GreenDaoManager.getInstance();

        initDaoSession();
    }

    public static Context getContext() {
        return mContext;
    }

    /*
     * GreenDao相关
     */
    public synchronized DaoSession getDaoSession() {
        if (mDaoSession == null) {
            initDaoSession();
        }
        return mDaoSession;
    }

    private void initDaoSession() {
        // 相当于得到数据库帮助对象，用于便捷获取db
        // 这里会自动执行upgrade的逻辑.backup all table→del all table→create all new table→restore data
        UpgradeHelper helper = new UpgradeHelper(this, "note.db", null);
        // 得到可写的数据库操作对象
        SQLiteDatabase db = helper.getWritableDatabase();
        // 获得Master实例,相当于给database包装工具
        DaoMaster daoMaster = new DaoMaster(db);
        // 获取类似于缓存管理器,提供各表的DAO类
        mDaoSession = daoMaster.newSession();
    }
}
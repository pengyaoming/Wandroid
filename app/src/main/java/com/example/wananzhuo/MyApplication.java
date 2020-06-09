package com.example.wananzhuo;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.blankj.utilcode.util.Utils;
import com.hjq.toast.ToastUtils;

import net.lucode.hackware.magicindicator.buildins.UIUtil;

/**
 * Created by Android Studio.
 * User: pengym
 * Date: 2020/5/14
 * Time: 14:29
 */
public class MyApplication extends Application {
    private static MyApplication instance;
    private static final String TAGH = MyApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        ToastUtils.init(instance);
        Utils.init(instance);
//        if (LeakCanary.isInAnalyzerProcess(this)) {//1
////            // This process is dedicated to LeakCanary for heap analysis.
////            // You should not init your app in this process.
////            return;
////        }
////        LeakCanary.install(this);

    }

    /**
     * 获取全局Application
     *
     * @return
     */
    public static MyApplication getInstance() {
        return instance;

    }


//    @Override
//    protected void attachBaseContext(Context base) {
//        super.attachBaseContext(base);
//        new Handler(Looper.getMainLooper()).post(new Runnable() {
//            @Override
//            public void run() {
//                while (true) {
//                    try {
//                        Looper.loop();
//                    } catch (Exception e) {
//                        Log.d(TAGH, e.getMessage());
//                    }
//                }
//            }
//        });
//    }

//    //性能检测工具
//    private RefWatcher setupLeakCanary() {
//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            return RefWatcher.DISABLED;
//        }
//        return LeakCanary.install(this);
//    }
//
//    public static RefWatcher getRefWatcher(Context context) {
//        MyApplication leakApplication = (MyApplication) context.getApplicationContext();
//        return leakApplication.refWatcher;
//    }
}

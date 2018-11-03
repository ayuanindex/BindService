package com.ayuan.bindservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class DemoService extends Service {

    private String TAG = "DemoService";

    /**
     * 当绑定服务调用的时候执行这个方法
     *
     * @param intent
     * @return
     */
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "这里是onbind方法");
        return null;
    }

    /**
     * onCreate方法要比onBind方法优先执行
     */
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "这里是oncreate方法");
        Toast.makeText(this, "服务开启了", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "这里是onstartcommand方法");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "这里是ondestroy方法");
        Toast.makeText(this, "服务关闭了", Toast.LENGTH_SHORT).show();
    }
}

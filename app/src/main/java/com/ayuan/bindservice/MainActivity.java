package com.ayuan.bindservice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String TAG = "MainActivity";
    private MyConnection myConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button start = (Button) findViewById(R.id.btn_start);
        Button stop = (Button) findViewById(R.id.btn_stop);

        //给按钮绑定点击数事件
        start.setOnClickListener(new MyStartService());
        stop.setOnClickListener(new MyStopService());
    }

    //点击按钮开启服务
    private class MyStartService implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, DemoService.class);
            myConnection = new MyConnection();
            //BIND_AUTO_CREATE：自动创建服务，（如果服务不存在则自动创建服务）
            bindService(intent, myConnection, BIND_AUTO_CREATE);
        }
    }

    //点击按钮关闭服务
    private class MyStopService implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            unbindService(myConnection);
        }
    }

    //定义一个类用来监视服务的状态
    private class MyConnection implements ServiceConnection {
        /**
         * 当服务连接成功之后调用此方法
         *
         * @param name
         * @param service
         */
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i(TAG, "服务连接成功");
        }

        /**
         * 当服务失去连接的时候调用
         *
         * @param name
         */
        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i(TAG, "服务失去连接");
        }
    }

    /**
     * 当Activity销毁的时候需要解绑服务
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(myConnection);
    }
}

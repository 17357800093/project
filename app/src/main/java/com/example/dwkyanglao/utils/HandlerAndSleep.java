package com.example.dwkyanglao.utils;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

public class HandlerAndSleep {

    // 1、定义一个Handler类，用于处理接受到的Message.
    Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            // do something eg.
            Log.e("HandlerAndSleep", "HandlerAndSleep");
        }
    };

    // 2、新建一个实现Runnable接口的线程类
    class MyThread implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(10000);
                    Message message = new Message();
                    message.what = 1;
                    handler.sendMessage(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public HandlerAndSleep() {
        //3、在需要启动线程的地方加入下面语句,启动线程后，线程每10s发送一次消息
        new Thread(new MyThread()).start();
    }
}

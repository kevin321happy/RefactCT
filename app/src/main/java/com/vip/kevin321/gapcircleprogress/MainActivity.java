package com.vip.kevin321.gapcircleprogress;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import com.vip.kevin321.gapcircleprogress.view.CapCircleprogressView;

public class MainActivity extends AppCompatActivity {

    private CapCircleprogressView mCap_circle1;
    private CapCircleprogressView mCap_circle2;
    private int START = 100;
    private int progress = 0;
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 100:
                    progress++;
                    if (progress < 80) {
                        mCap_circle1.setCurrentProgress(progress);
                        mCap_circle2.setCurrentProgress(progress);
                        sendEmptyMessageDelayed(START, 50);
                    }else {
                        mCap_circle1.setCurrentProgress(-1);
                        mCap_circle2.setCurrentProgress(-1);
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCap_circle1 = (CapCircleprogressView) findViewById(R.id.cap_circle1);
        mCap_circle2 = (CapCircleprogressView) findViewById(R.id.cap_circle2);
        mHandler.sendEmptyMessageDelayed(START,100);
    }
}

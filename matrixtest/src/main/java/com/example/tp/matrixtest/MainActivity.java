package com.example.tp.matrixtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;

public class MainActivity extends AppCompatActivity {
    private MyImageView myImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myImageView = new MyImageView(this);
        setContentView(myImageView);
        System.out.println("1");
        if(myImageView == null)
            System.out.println("imageView空指针");
        System.out.println("3");
        Thread.currentThread().setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("异常："+e);
            }
        });
        System.out.println("4");
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                myImageView.type = 1;
                myImageView.invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                myImageView.type = 2;
                myImageView.invalidate();
                break;
            case MotionEvent.ACTION_UP:
                myImageView.type = 0;
                myImageView.invalidate();
                break;

        }
        return super.onTouchEvent(event);
    }
    //
//    public void translate(View v) {
//        myImageView.type = 1;
//        myImageView.invalidate();
//    }
//
//    public void skew(View v) {
//        myImageView.type = 0;
//        myImageView.invalidate();
//    }
//
//    public void scale(View v) {
//        myImageView.type = 2;
//        myImageView.invalidate();
//    }

}

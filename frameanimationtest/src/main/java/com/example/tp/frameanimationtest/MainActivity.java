package com.example.tp.frameanimationtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.ListView;

import java.lang.reflect.Field;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private AnimationDrawable animation;
    private Animation animStart;
    private Animation animReverse;
    private float curX = 0, curY = 30, nextX = 0, nextY = 0;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // FrameLayout frame = new FrameLayout(this);
        setContentView(R.layout.activity_main);
        ListView list = (ListView)findViewById(R.id.list_item);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        list.startAnimation(new TweenAnimationBySelf(metrics.xdpi/2,metrics.ydpi/2,3500));
      //  final ImageView image = (ImageView) findViewById(R.id.image);
//        animStart = AnimationUtils.loadAnimation(this,R.anim.anim_start);
//        animStart.setFillAfter(true);
//        animReverse = AnimationUtils.loadAnimation(this,R.anim.anim_reverse);
//        animReverse.setFillAfter(true);
//        image.setAnimation(animStart);
//        animation = (AnimationDrawable) image.getBackground();
//        handler = new Handler() {
//            @Override
//            public void handleMessage(Message msg) {
//                super.handleMessage(msg);
//                if (msg.what == 0x123) {
//                    if (nextX > 320) {
//                        curX = nextX = 0;
//                    } else {
//                        nextX += 8;
//                    }
//                    nextY = curY + (float) (Math.random() * 10 - 5);
//                    //设置位移动画
//                    TranslateAnimation translate = new TranslateAnimation(curX, nextX, curY, nextY);
//                    curX = nextX;
//                    curY = nextY;
//                    translate.setDuration(200);
//                    image.startAnimation(translate);
//                }
//            }
//        };
//        myView = new MyView(this);
//        myView.setBackgroundResource(R.drawable.animation_bomb);
//        myView.setVisibility(View.INVISIBLE);
//        frame.addView(myView);
//        animation = (AnimationDrawable) myView.getBackground();
//        frame.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if(event.getAction() == MotionEvent.ACTION_DOWN)
//                {
//                    animation.stop();
//                    float fx = event.getX();
//                    float fy = event.getY();
//                    myView.setLocation((int)fx-40,(int)fy-20);
//                    myView.setVisibility(View.VISIBLE);
//                    animation.start();
//                }
//                return false;
//            }
//        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            animation.start();
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.sendEmptyMessage(0x123);
                }
            }, 0, 200);
        }
        return true;
    }

    class MyView extends ImageView {
        public MyView(Context context) {
            super(context);
        }

        //设置view在父组件中的布局位置
        public void setLocation(int top, int left) {
            this.setFrame(left, top, left + 40, top + 40);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            try {
                Field field = AnimationDrawable.class.getDeclaredField("mCurFrame");
                field.setAccessible(true);
                try {
                    int curFrame = field.getInt(animation);
                    if (curFrame == animation.getNumberOfFrames()) {
                        setVisibility(View.INVISIBLE);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }

        }
    }

}

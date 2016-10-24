package com.example.tp.tablegametest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {
    Random random = new Random();
    //桌面的宽高度
    private int tableWidth, tableHeigth;
    //小球的坐标
    private int ballX = random.nextInt(200) + 20, ballY = random.nextInt(10) + 20;
    //定义求的大小
    private int BALL_SIZE = 16;
    //小球的y方向运动速度
    private int ySpeed = 15;
    //小球的xy方向运动速度比
    private double xyRate = random.nextDouble() - 0.5;
    private int xSpeed = (int) (ySpeed * xyRate * 2);
    //球拍的坐标
    private int racketX = random.nextInt(200), racketY;
    //定义球拍的宽高
    private final int RACKET_WIDTH = 90;
    private final int RACKET_HEIGHT = 30;
    //游戏是否结束的标志
    private boolean gameOver = false;
    private GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        WindowManager winManager = getWindowManager();
        Display display = winManager.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        tableWidth = metrics.widthPixels;
        tableHeigth = metrics.heightPixels;
        racketY = tableHeigth - 180;
        System.out.println("racket x,y:"+racketX+" , "+racketY);
        System.out.println("metrics x,y:"+tableWidth+" , "+tableHeigth);
        gameView = new GameView(this);
        setContentView(gameView);
        final Handler hander = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == 0x123) {
                    gameView.invalidate();
                }
            }
        };
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //小球碰到左右边框
                if (ballX <= 0 || ballX >= tableWidth - BALL_SIZE) {
                    xSpeed = -xSpeed;
                }
                //小球在球拍以下，游戏结束
                if (ballY >= racketY - BALL_SIZE && (ballX < racketX || ballX > racketX + RACKET_WIDTH)) {
                    timer.cancel();
                    gameOver = true;
                }
                //小球落在球拍范围内并反弹,或者碰到上边框
                if ((ballY <= 0) || ballY >= racketY - BALL_SIZE && ballX >= racketX && ballX <= racketX + RACKET_WIDTH) {
                    ySpeed = -ySpeed;
                }
                //小球坐标增加
                ballX += xSpeed;
                ballY += ySpeed;
                //发送消息给handler通知更新view
                hander.sendEmptyMessage(0x123);
            }
        }, 0, 100);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
        switch (event.getAction())
        {
            case MotionEvent.ACTION_MOVE:

        }
    }

    public void left(View v) {
        //控制球拍向左移动
        if (racketX > 0)
            racketX -= 10;
        gameView.invalidate();
    }

    public void right(View v) {
        //控制球拍向左移动
        if (racketX < tableWidth - RACKET_WIDTH)
            racketX += 10;
        gameView.invalidate();
    }

    class GameView extends View {
        public GameView(Context context) {
            super(context);
            setFocusable(true);
        }

        Paint paint = new Paint();

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            paint.setStyle(Paint.Style.FILL);
            paint.setAntiAlias(true);
            if (gameOver) {
                paint.setTextSize(40);
                paint.setColor(Color.RED);
                canvas.drawText("游戏结束", tableWidth / 2 - 100, 200, paint);
            } else {
                paint.setColor(Color.rgb(255, 0, 0));
                canvas.drawCircle(ballX, ballY, BALL_SIZE, paint);
                paint.setColor(Color.rgb(255, 80, 20));
                canvas.drawRect(racketX, racketY, racketX + RACKET_WIDTH, racketY + RACKET_HEIGHT, paint);
            }
        }
    }

}

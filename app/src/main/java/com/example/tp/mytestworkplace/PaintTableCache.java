package com.example.tp.mytestworkplace;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by tangpeng on 2016/10/15.
 */

public class PaintTableCache extends View {
    private Bitmap bitmap;
    private Canvas cacheCanvas;
    private Path path;
    public Paint paint = null;
    float preX,preY;

    public PaintTableCache(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PaintTableCache(Context context,int width,int height) {
        super(context);
        //创建一个存在内存中的bitmap对象作为缓存
        bitmap = Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_8888);
        //chacheCanvas绘制到bitmap上
        cacheCanvas = new Canvas();
        cacheCanvas.setBitmap(bitmap);
        path = new Path();
        paint = new Paint(Paint.DITHER_FLAG);
        paint.setColor(Color.RED);
        //设置画笔风格
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1);
        //设置反锯齿
        paint.setAntiAlias(true);
        paint.setDither(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX(),y = event.getY();
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(x,y);
                preX = x;
                preY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                path.quadTo(preX,preY,x,y);
                preY = y;
                preX = x;
                break;
            case MotionEvent.ACTION_UP:
                cacheCanvas.drawPath(path,paint);
                path.reset();
                break;
        }
        invalidate();
        return true;    //返回true表明该方法已经处理了该事件
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint viewPaint = new Paint();
        canvas.drawBitmap(bitmap,0,0,viewPaint);
        canvas.drawPath(path,paint);
    }

}

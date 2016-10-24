package com.example.tp.matrixtest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;

import java.lang.ref.WeakReference;

/**
 * Created by tangpeng on 2016/10/15.
 */

public class MyImageView extends View {
    private Bitmap bitmap;
    private WeakReference<Bitmap> tmpBitmap;
    private Matrix matrix = new Matrix();
    public int type;
    private int width, height;

    public MyImageView(Context context) {
        super(context);
        bitmap = ((BitmapDrawable) getResources().getDrawable(R.drawable.back2)).getBitmap();
        width = bitmap.getWidth();
        height = bitmap.getHeight();
        System.out.println("5");
    }

    public MyImageView(Context context, AttributeSet attrs) {
        super(context);
        System.out.println("55");
    }

    public MyImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs);
        System.out.println("555");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        System.out.println("6");
        matrix.reset();
        System.out.println("7");
        switch (type) {
            case 0:
                matrix.setSkew(1, 1);
                break;
            case 1:
                matrix.setTranslate(20, 30);
                break;
            case 2:
                matrix.setScale(0.5f, 0.5f);
                break;
            default:
                break;
        }
        System.out.println("8");
        tmpBitmap = new WeakReference<Bitmap>(Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true));
        System.out.println("9");
        canvas.drawBitmap(tmpBitmap.get(), 0, 0, null);
        System.out.println("10");
    }
}

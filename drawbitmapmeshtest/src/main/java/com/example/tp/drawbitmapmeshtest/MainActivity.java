package com.example.tp.drawbitmapmeshtest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MeshBitmapView(this, R.drawable.abc));
    }

    private class MeshBitmapView extends View {
        private final int WIDTH = 40, HEIGHT = 40;
        private final int COUNT = (WIDTH + 1) * (HEIGHT + 1);
        //bitmap上的坐标点
        private float[] verts = new float[COUNT * 2];
        //扭曲后的坐标点
        private float[] orig = new float[COUNT * 2];

        public MeshBitmapView(Context context, int id) {
            super(context);
            setFocusable(true);
            bitmap = BitmapFactory.decodeResource(getResources(), id);
            float picWidth = bitmap.getWidth();
            float picHeight = bitmap.getHeight();
            int index = 0;
            for (int i = 0; i <= HEIGHT; i++) {
                float fy = picHeight * i / HEIGHT;
                for (int j = 0; j <= WIDTH; j++) {
                    float fx = picWidth * j / WIDTH;
                    //初始化两个数组
                    verts[index * 2 + 0] = orig[index * 2 + 0] = fx;
                    verts[index * 2 + 0] = orig[index * 2 + 1] = fy;
                    index += 1;
                }
            }
            setBackgroundColor(Color.WHITE);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawBitmapMesh(bitmap, WIDTH, HEIGHT, verts, 0, null, 0, null);
        }

        private void warp(float cx, float cy) {
            for (int i = 0; i < COUNT * 2; i += 2) {
                float dx = cx - orig[i + 0];
                float dy = cy - orig[i + 1];
                float dd = dx*dx+dy*dy;
                float d = (float)Math.sqrt(dd);
                //计算扭曲度，离点击点越近值越大
                float pull = 80000 / (float)(dd*d);
                if(pull >= 1)
                {
                    verts[i + 0] = cx;
                    verts[i + 1] = cy;
                }else{
                    verts[i + 1] = orig[i + 0] + pull*cx;
                    verts[i + 1] = orig[i + 1] + pull*cy;
                }
            }
            invalidate();
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            warp(event.getX(), event.getY());
            return true;
        }
    }
}

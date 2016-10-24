package com.example.tp.frameanimationtest;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;

/**
 * Created by tangpeng on 2016/10/18.
 */

public class TweenAnimationBySelf extends Animation {
    float centerX, centerY;
    int duration;
    Camera camera = new Camera();

    public TweenAnimationBySelf(float x, float y, int duration) {
        centerX = x;
        centerY = y;
        this.duration = duration;
    }

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        setDuration(duration);
        setFillAfter(true);
        setInterpolator(new LinearInterpolator());
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        camera.save();
        //通过interpolatedTime来设置在xyz轴上的位移
        camera.translate(100.0f - 100.0f * interpolatedTime, 150.0f * interpolatedTime - 150.0f, 80.0f - 80.0f * interpolatedTime);
        //在xy轴上面的旋转
        camera.rotateX(360*interpolatedTime);
        camera.rotateY(360*interpolatedTime);
        //获取Transformation封装的Matrix，该Matrix上的变换将会应用到组件或图片上
        Matrix matrix = t.getMatrix();
        //将camera所做的变换赋给matrix
        camera.getMatrix(matrix);
        matrix.preTranslate(-centerY,-centerY);
        matrix.postTranslate(centerX,centerY);
        //回复到改变之前保存的状态
        camera.restore();
    }
}

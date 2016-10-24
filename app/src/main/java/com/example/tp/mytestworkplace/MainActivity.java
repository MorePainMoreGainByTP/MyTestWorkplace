package com.example.tp.mytestworkplace;

import android.graphics.BlurMaskFilter;
import android.graphics.Color;
import android.graphics.EmbossMaskFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    EmbossMaskFilter emboss;
    BlurMaskFilter blur;
    PaintTableCache paintTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout linear = new LinearLayout(this);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        //获取创建的view的高度宽度
        getWindowManager().getDefaultDisplay().getRealMetrics(displayMetrics);
        paintTable = new PaintTableCache(this, displayMetrics.widthPixels, displayMetrics.heightPixels);
        linear.addView(paintTable);
        setContentView(linear);
        emboss = new EmbossMaskFilter(new float[]{1.5f, 1.5f, 1.5f}, 0.6f, 6, 4.2f);
        blur = new BlurMaskFilter(8, BlurMaskFilter.Blur.NORMAL);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.red:
                paintTable.paint.setColor(Color.RED);
                item.setChecked(true);
                break;
            case R.id.green:
                paintTable.paint.setColor(Color.GREEN);
                item.setChecked(true);
                break;
            case R.id.blue:
                paintTable.paint.setColor(Color.BLUE);
                item.setChecked(true);
                break;
            case R.id.width1:
                paintTable.paint.setStrokeWidth(1);
                break;
            case R.id.width3:
                paintTable.paint.setStrokeWidth(3);
                break;
            case R.id.width5:
                paintTable.paint.setStrokeWidth(5);
                break;
            case R.id.blur:
                paintTable.paint.setMaskFilter(blur);
                break;
            case R.id.emboss:
                paintTable.paint.setMaskFilter(emboss);
                break;
        }
        return true;
    }

    public void show(View v) {

    }

}

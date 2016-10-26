package com.example.tp.auxiliaryteachingapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.tp.myviews.SlidingViewBySelf;

public class MainActivity extends AppCompatActivity {
    private SlidingViewBySelf slidingMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        slidingMenu = (SlidingViewBySelf)findViewById(R.id.activity_main);

    }

    public void onClick(View v)
    {
        slidingMenu.toggle();
    }

}

package com.example.tp.getcontactsinfofromsystem;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

/**
 * Created by tangpeng on 2016/10/24.
 */

public class SecondActivity extends TabActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("second_01");
        System.out.println("second");
        Intent intent = getIntent();
        System.out.println(intent.getAction()+"\t"+intent.getPackage());
        TabHost tabHost = getTabHost();
        System.out.println("second_02");
        tabHost.addTab(tabHost.newTabSpec("第一页").setIndicator("第一页",
 getResources().getDrawable(R.mipmap.ic_launcher)).setContent(new Intent(SecondActivity.this, ThirdActivity.class)));
        tabHost.addTab(tabHost.newTabSpec("第~~页").setIndicator("第二页",
 getResources().getDrawable(R.mipmap.ic_launcher)).setContent(new Intent(SecondActivity.this, FourthActivity.class)));
        tabHost.addTab(tabHost.newTabSpec("第**页").setIndicator("第三页",
 getResources().getDrawable(R.mipmap.ic_launcher)).setContent(new Intent(SecondActivity.this, FifthActivity.class)));
    }
}

package com.example.tp.mediaplayertest;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private MediaPlayer mp3;
    private final String strUri = "http://downdb.51voa.com/201610/texas-teenager-rocks-the-blues-on-new-album.mp3";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mp3 =  new MediaPlayer();
    }

    public void startMP3(View v)
    {
        System.out.println("1");
        Uri mp3Uri = Uri.parse(strUri);
        System.out.println("2");
        try {
            mp3.setDataSource(MainActivity.this,mp3Uri);
            System.out.println("3");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("打开uri异常"+e);
        }
        System.out.println("4");
        mp3.prepareAsync();
        System.out.println("5");
        mp3.start();
        System.out.println("6");
    }


    public void stopMp3(View v)
    {
        if(mp3 != null)
        {
            mp3.stop();
        }
    }

}

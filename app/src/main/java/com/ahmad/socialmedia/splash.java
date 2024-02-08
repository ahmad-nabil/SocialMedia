package com.ahmad.socialmedia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.ahmad.socialmedia.UI.Home;

public class splash extends AppCompatActivity {
    long SplashTimeOut=2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        splasher();
    }
    private void splasher(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(SplashTimeOut-500);
                }catch (Exception e){
                }
                startActivity(new Intent(splash.this, Home.class));
finish();
            }
        }).start();
    }
}
package com.ahmad.socialmedia.UI;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ahmad.socialmedia.databinding.ActivityHomeBinding;

public class Home extends AppCompatActivity {
    SocialVideoViewBinding socialVideoViewBinding;
ActivityHomeBinding HomeBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HomeBinding=ActivityHomeBinding.inflate(getLayoutInflater());
        View HomeView=HomeBinding.getRoot();
        setContentView(HomeView);

        //call method from other classes
  socialVideoViewBinding=new SocialVideoViewBinding(HomeBinding,this);
        socialVideoViewBinding.InitializeUiComponents();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        socialVideoViewBinding.HandleActivityResult(requestCode, resultCode, data);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, Home.class));
    }
}
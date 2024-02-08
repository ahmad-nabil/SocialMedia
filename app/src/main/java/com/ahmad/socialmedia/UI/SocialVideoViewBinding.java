package com.ahmad.socialmedia.UI;


import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaMetadataRetriever;
import android.net.Uri;

import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.ahmad.socialmedia.databinding.ActivityHomeBinding;
import com.ahmad.socialmedia.fragment_menu.ClipBoard;
import com.ahmad.socialmedia.fragment_menu.RateUs;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.fragment.app.FragmentActivity;



import com.ahmad.socialmedia.R;
import com.ahmad.socialmedia.adabters.HomePagerAdabter;
import com.ahmad.socialmedia.custommodel.VideoPostList;


import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

public class SocialVideoViewBinding   {
ActivityHomeBinding HomeBinding;
Activity Home;
Uri videoUri;
    ArrayList<VideoPostList> VideoPost=new ArrayList<>();
int Count=0;
    public SocialVideoViewBinding(ActivityHomeBinding homeBinding, Activity home) {
        HomeBinding = homeBinding;
        Home = home;
    }

    public void InitializeUiComponents() {
        HomeBinding.UploadVideo.setOnClickListener(v -> {
            UploadVideo();
        });
        HomeBinding.SharePost.setOnClickListener(V -> {
            SharePost();
        });
        HomePagerAdabter homePagerAdabter = new HomePagerAdabter(VideoPost, Home);
        HomeBinding.viewpager.setAdapter(homePagerAdabter);
        homePagerAdabter.notifyDataSetChanged();
        //navigation view components
        ActionBar actionBar = Home.getActionBar();
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(Home, HomeBinding.drawer, HomeBinding.toolpar, R.string.open, R.string.close);
        drawerToggle.getDrawerArrowDrawable().setColor(HomeBinding.getRoot().getResources().getColor(android.R.color.holo_blue_dark));
        drawerToggle.syncState();
        HomeBinding.NavView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId()==R.id.rate_us){

                    ((FragmentActivity)Home).getSupportFragmentManager().beginTransaction().replace(HomeBinding.fragmentContainer.getId(), new RateUs()).commit();

                }
                if (item.getItemId()==R.id.clipboard){

                    ((FragmentActivity)Home).getSupportFragmentManager().beginTransaction().replace(HomeBinding.fragmentContainer.getId(), new ClipBoard()).commit();

                }

                if (item.getItemId()==R.id.exit){
Intent exit=new Intent();
exit.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
exit.putExtra("EXIT", true);
Home.startActivity(exit);
                }

                return true;            }
        });
    }
    //uploading ur video you selected from gallery
    private void UploadVideo(){

        Intent upload=new Intent();
        upload.setType("video/*");
        upload.setAction(Intent.ACTION_GET_CONTENT);
        //1==request code for activity Result
Home.startActivityForResult(upload,1);
    }
    //methode for sharing selected posted

    private void SharePost() {
        String hisPost = HomeBinding.post.getText().toString();
        HomeBinding.UploadVideo.setVisibility(View.VISIBLE);
        if (hisPost.isEmpty()&&videoUri==null){
            Toast.makeText(Home, "write any thing please", Toast.LENGTH_SHORT).show();
        }else {
            VideoPost.add(new VideoPostList( videoUri, hisPost,0,""));
            //update viewpager
            Objects.requireNonNull(HomeBinding.viewpager.getAdapter()).notifyDataSetChanged();
            //rest values
            videoUri=null;
            HomeBinding.post.setText("");
            HomeBinding.post.setHint("what you think now");
        }

    }


//handle activity we called it also in activity result  to get Uri video
    public void HandleActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == 1 && resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {

            videoUri = data.getData();

            HomeBinding.UploadVideo.setVisibility(View.GONE);
            Toast.makeText(Home, "uploaded", Toast.LENGTH_SHORT).show();
        }
    }
}
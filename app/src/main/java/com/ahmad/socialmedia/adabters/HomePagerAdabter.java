package com.ahmad.socialmedia.adabters;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.ahmad.socialmedia.custommodel.VideoPostList;
import com.ahmad.socialmedia.databinding.DialogRateVideosBinding;
import com.ahmad.socialmedia.databinding.LayoutViewpagerHomeBinding;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;

import java.util.ArrayList;

public class HomePagerAdabter extends PagerAdapter {
ArrayList <VideoPostList>list;
Context context;
    LayoutViewpagerHomeBinding bindingViewPager;
    DialogRateVideosBinding binding;
    ExoPlayer player;
    public HomePagerAdabter(ArrayList<VideoPostList> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        //inflate layout using binding
        bindingViewPager=LayoutViewpagerHomeBinding
        .inflate(LayoutInflater.from(context),container,false);
        View view=bindingViewPager.getRoot();
        bindingViewPager.posted.setText(list.get(position).getPost());
        bindingViewPager.ratingBar.setRating(list.get(position).getRate());
        bindingViewPager.nametv.setText(list.get(position).getName());
        if (list.get(position).getRate()>0){
            if (list.get(position).getRate()<=1){
                bindingViewPager.valueRatetv.setText("meh");
            }
            if (list.get(position).getRate()<=2){
                bindingViewPager.valueRatetv.setText("not bad");
            }
            if (list.get(position).getRate()<=3){
                bindingViewPager.valueRatetv.setText("ok");
            }
            if (list.get(position).getRate()<5){
                bindingViewPager.valueRatetv.setText("good");
            }
            if (list.get(position).getRate()==5){
                bindingViewPager.valueRatetv.setText("excellant");
            }
        }
        //here check if uri video in custom array not null to avoid any invoke mull bug
        if (list.get(position).getVideoUri()!=null){
            bindingViewPager.exoPlayer.setVisibility(View.VISIBLE);


            player=new ExoPlayer.Builder(context.getApplicationContext()).build();
            bindingViewPager.exoPlayer.setPlayer(player);
            MediaItem mediaItem=MediaItem.fromUri(list.get(position).getVideoUri());
            player.setMediaItem(mediaItem);
            player.prepare();
            container.addView(view);

        }else {
            bindingViewPager.posted.setText(list.get(position).getPost());
            bindingViewPager.exoPlayer.setVisibility(View.GONE);
            container.addView(view);
        }

        bindingViewPager.ReviewButton.setOnClickListener(v -> {
            AddReview(position);
});

        return view;

    }
// add review under any video
    private void AddReview(int position) {
        //initilize dialog
        Dialog dialog = new Dialog(context);
        binding = DialogRateVideosBinding.inflate(dialog.getLayoutInflater());
        dialog.setContentView(binding.getRoot());
        dialog.show();
        binding.btnCancel.setOnClickListener(v -> dialog.dismiss());
binding.btnEnterRate.setOnClickListener(v -> {
    if (!TextUtils.isEmpty(binding.name.getText())
            &&binding.rateBar.getRating()>0){
        //here we updates value rate bar and text view in this method
        bindingViewPager.ReviewButton.setVisibility(View.INVISIBLE);
                list.get(position).setRate(binding.rateBar.getRating());
                list.get(position).setName(binding.name.getText().toString());
notifyDataSetChanged();
        dialog.dismiss();


    }
        }
        );
    }



    @Override
    public int getCount() {
        return list.size();
    }
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);    }
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}

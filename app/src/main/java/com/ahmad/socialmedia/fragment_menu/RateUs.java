package com.ahmad.socialmedia.fragment_menu;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;

import com.ahmad.socialmedia.R;
import com.ahmad.socialmedia.adabters.RecycleAdabter;
import com.ahmad.socialmedia.databinding.DialogRateUsBinding;
import com.ahmad.socialmedia.databinding.FragmentRateUsBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class RateUs extends Fragment implements View.OnClickListener {
FragmentRateUsBinding RateBinding;
JSONArray jsonArray=new JSONArray();
RecycleAdabter recycleAdabter;
    View RateView;
    DialogRateUsBinding dialogRateUsBinding;
    Dialog dialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RateBinding=FragmentRateUsBinding.inflate(getLayoutInflater());
       RateView=RateBinding.getRoot();
        initializeUi();
return RateView;
    }


//initilize dialoge AND other component
    private void initializeUi() {
        dialogRateUsBinding=DialogRateUsBinding.inflate(getLayoutInflater());
        dialogRateUsBinding.supmitBtn.setOnClickListener(this);
RateBinding.AddUrRate.setOnClickListener(this);
       dialog=new Dialog(getContext());
        dialog.setContentView(dialogRateUsBinding.getRoot());
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
       RateBinding.RecycleRateList.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recycleAdabter=new RecycleAdabter(jsonArray,getContext());
        RateBinding.RecycleRateList.setAdapter(recycleAdabter);


    }


    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onClick(View v) {



if(v.getId()==R.id.AddUrRate){
//get dialoge to input rate
    dialog.show();


            dialogRateUsBinding.rateBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                    dialogRateUsBinding.RatingValue.setText(String.valueOf(rating));
                }});
        }
     if (v.getId()==dialogRateUsBinding.supmitBtn.getId()) {
         String rating=String.valueOf(dialogRateUsBinding.rateBar.getRating());
         try {
             jsonArray.put(new JSONObject().put("rating",rating));
             recycleAdabter.notifyDataSetChanged();

         } catch (JSONException e) {
             throw new RuntimeException(e);
         }
         dialog.dismiss();
         dialogRateUsBinding.rateBar.setRating(0f);

     }

    }}


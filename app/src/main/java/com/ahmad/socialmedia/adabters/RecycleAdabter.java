package com.ahmad.socialmedia.adabters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmad.socialmedia.databinding.ItemsRecycleviewBinding;

import org.json.JSONArray;
import org.json.JSONObject;

public class RecycleAdabter extends RecyclerView.Adapter<RecycleAdabter.RateHolder> {
    JSONArray jsonArray;
    Context context;

    public RecycleAdabter(JSONArray jsonArray, Context context) {
        this.jsonArray = jsonArray;
       this.context=context;    }

    @NonNull
    @Override
    public RateHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
ItemsRecycleviewBinding itemsRecycleviewBinding=ItemsRecycleviewBinding.
        inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new RateHolder(itemsRecycleviewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RateHolder holder, int position) {

try {
    JSONObject jsonObject=jsonArray.getJSONObject(position);
String Rating=jsonObject.getString("rating");
holder.RvBinding.RateUser.setText(Rating);
    holder.RvBinding.rateUs.setRating(Float.parseFloat(Rating));

}catch (Exception e){
    e.printStackTrace();
}
    }

    @Override
    public int getItemCount() {
        return jsonArray.length();
    }

    public class RateHolder extends RecyclerView.ViewHolder{
        ItemsRecycleviewBinding RvBinding;
        RateHolder(  ItemsRecycleviewBinding RvBinding){
            super(RvBinding.getRoot());
          this.RvBinding=RvBinding;
        }
    }
}

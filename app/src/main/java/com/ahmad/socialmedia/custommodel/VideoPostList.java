package com.ahmad.socialmedia.custommodel;

import android.net.Uri;

public class VideoPostList {

    public Uri videoUri;
   public String post;
float rate;
String name;


    public VideoPostList(Uri videoUri, String post, float rate, String name) {
        this.videoUri = videoUri;
        this.post = post;
        this.rate = rate;
        this.name = name;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Uri getVideoUri() {
        return videoUri;
    }

    public void setVideoUri(Uri videoUri) {
        this.videoUri = videoUri;
    }



    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }


}

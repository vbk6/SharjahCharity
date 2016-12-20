package com.example.vishnubk.sharjahcharity.Models;

/**
 * Created by vishnubk on 9/11/16.
 */
public class Post {

    byte[] image;

    String title;

//    public Post(byte[] image, String title){
//        this.image=image;
//        this.title=title;
//    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }





}

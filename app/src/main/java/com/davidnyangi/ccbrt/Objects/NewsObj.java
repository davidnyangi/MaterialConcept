package com.davidnyangi.ccbrt.Objects;

public class NewsObj {

    String title;
    int image;
    String date;

    public NewsObj(){}


    public NewsObj(String title, int image, String date){
        this.title = title;
        this.image = image;
        this.date = date;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getTitle(){
        return title;
    }

    public void setImage(int image){
        this.image= image;
    }

    public int getImage(){
        return image;
    }

    public void setDate(String date){
        this.date = date;
    }

    public String getDate(){
        return date;
    }
}

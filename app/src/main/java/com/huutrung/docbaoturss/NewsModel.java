package com.huutrung.docbaoturss;

/**
 * Created by Admin on 10/26/2017.
 */

public class NewsModel {
    private String title;
    private String link;
    private String imageURL;

    public NewsModel(String title, String link, String imageURL){
        this.title = title;
        this.link = link;
        this.imageURL = imageURL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}

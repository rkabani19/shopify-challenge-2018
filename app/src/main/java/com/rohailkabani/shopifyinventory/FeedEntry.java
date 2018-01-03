package com.rohailkabani.shopifyinventory;

import java.net.URI;

/**
 * Created by rohailkabani on 2018-01-03.
 */

public class FeedEntry {
    private String description, title, image;
//    private URI image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

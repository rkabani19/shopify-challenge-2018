package com.rohailkabani.shopifyinventory;

/**
 * Created by rohailkabani on 2018-01-03.
 */

public class ListItem {
    private String title;
    private String description;
    private String imgURL;
    private String productId;
    private String colours;
    private String typeProduct;

    public ListItem(String title, String description, String imgURL) {
        this.title = title;
        this.description = description;
        this.imgURL = imgURL;
    }

    public ListItem(String title, String description, String imgURL, String productId, String colours, String typeProduct) {
        this.title = title;
        this.description = description;
        this.imgURL = imgURL;
        this.productId = productId;
        this.colours = colours;
        this.typeProduct = typeProduct;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getColours() {
        return colours;
    }

    public void setColours(String colours) {
        this.colours = colours;
    }

    public String getTypeProduct() {
        return typeProduct;
    }

    public void setTypeProduct(String typeProduct) {
        this.typeProduct = typeProduct;
    }
}

package com.shoeARstore;

/* loaded from: classes9.dex */
public class Beanlist {
    private String cutprice;
    private String discount;
    private String favorites;
    private String id;
    private String image;
    private String price;
    private float rating;
    private String ratingtext;
    private String title;

    public Beanlist(String image, String title, String price, String cutprice, String discount, String ratingtext, float rating, String id, String favorites) {
        this.image = image;
        this.title = title;
        this.price = price;
        this.cutprice = cutprice;
        this.discount = discount;
        this.ratingtext = ratingtext;
        this.rating = rating;
        this.id = id;
        this.favorites = favorites;
    }

    public String getFavorites() {
        return this.favorites;
    }

    public void setFavorites(String favorites) {
        this.favorites = favorites;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCutprice() {
        return this.cutprice;
    }

    public void setCutprice(String cutprice) {
        this.cutprice = cutprice;
    }

    public String getDiscount() {
        return this.discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getRatingtext() {
        return this.ratingtext;
    }

    public void setRatingtext(String ratingtext) {
        this.ratingtext = ratingtext;
    }

    public float getRating() {
        return this.rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
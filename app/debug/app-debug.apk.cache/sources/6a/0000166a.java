package com.shoeARstore;

/* loaded from: classes9.dex */
public class CartListBeanlist {
    private String id;
    private String image;
    private String piece;
    private String price;
    private String size;
    private String stockcode;
    private String title;

    public CartListBeanlist(String image, String title, String price, String stockcode, String piece, String size, String id) {
        this.image = image;
        this.title = title;
        this.price = price;
        this.stockcode = stockcode;
        this.piece = piece;
        this.size = size;
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSize() {
        return this.size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getPiece() {
        return this.piece;
    }

    public void setPiece(String piece) {
        this.piece = piece;
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

    public String getStockcode() {
        return this.stockcode;
    }

    public void setStockcode(String stockcode) {
        this.stockcode = stockcode;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
package com.shoeARstore;

/* loaded from: classes9.dex */
public class BeanclassNotification {
    private String time;
    private String title;

    public BeanclassNotification(String title, String time) {
        this.title = title;
        this.time = time;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
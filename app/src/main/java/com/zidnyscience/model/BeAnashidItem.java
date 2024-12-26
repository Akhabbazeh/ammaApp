package com.zidnyscience.model;

public class BeAnashidItem {
    private int id;
    private String title;
    private String moshid_title;
    private String moshid_name;

    public BeAnashidItem(int id, String title, String moshid_title, String moshid_name) {
        this.id = id;
        this.title = title;
        this.moshid_title = moshid_title;
        this.moshid_name = moshid_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMoshid_title() {
        return moshid_title;
    }

    public void setMoshid_title(String moshid_title) {
        this.moshid_title = moshid_title;
    }

    public String getMoshid_name() {
        return moshid_name;
    }

    public void setMoshid_name(String moshid_name) {
        this.moshid_name = moshid_name;
    }
}

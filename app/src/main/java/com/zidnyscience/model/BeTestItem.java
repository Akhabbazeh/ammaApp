package com.zidnyscience.model;

public class BeTestItem {
    private int id;
    private int makhtota_img;

    public BeTestItem(int id, int makhtota_img) {
        this.id = id;
        this.makhtota_img = makhtota_img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMakhtota_img() {
        return makhtota_img;
    }

    public void setMakhtota_img(int makhtota_img) {
        this.makhtota_img = makhtota_img;
    }
}

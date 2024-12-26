package com.zidnyscience.model;

public class BeSwarIndexItem {
    private int id;
    private int makhtota_img;
    private int page_number;
    private String type;
    private int aya_numbers;
    private int sura_numbers;


    public BeSwarIndexItem(int id, int makhtota_img, int page_number, String type, int aya_numbers, int sura_numbers) {
        this.id = id;
        this.makhtota_img = makhtota_img;
        this.page_number = page_number;
        this.type = type;
        this.aya_numbers = aya_numbers;
        this.sura_numbers = sura_numbers;
    }

    public BeSwarIndexItem(int id, int makhtota_img) {
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

    public int getPage_number() {
        return page_number;
    }

    public void setPage_number(int page_number) {
        this.page_number = page_number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAya_numbers() {
        return aya_numbers;
    }

    public void setAya_numbers(int aya_numbers) {
        this.aya_numbers = aya_numbers;
    }

    public int getSura_numbers() {
        return sura_numbers;
    }

    public void setSura_numbers(int sura_numbers) {
        this.sura_numbers = sura_numbers;
    }
}

package com.zidnyscience.model;

public class BeTeacherKoran {
    private int id;
    private int makhtota_img;
    private int sura_numbers;
    private String audio_url;
    private String audio_hosary_url;

    public BeTeacherKoran(int id, int makhtota_img, int sura_numbers, String audio_url,String audio_hosary_url) {
        this.id = id;
        this.makhtota_img = makhtota_img;
        this.sura_numbers = sura_numbers;
        this.audio_url = audio_url;
        this.audio_hosary_url = audio_hosary_url;
    }

    public String getAudio_hosary_url() {
        return audio_hosary_url;
    }

    public void setAudio_hosary_url(String audio_hosary_url) {
        this.audio_hosary_url = audio_hosary_url;
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

    public int getSura_numbers() {
        return sura_numbers;
    }

    public void setSura_numbers(int sura_numbers) {
        this.sura_numbers = sura_numbers;
    }

    public String getAudio_url() {
        return audio_url;
    }

    public void setAudio_url(String audio_url) {
        this.audio_url = audio_url;
    }
}

package com.zidnyscience.model;

public class BeWordMeaningItem {
    private int id;
    private String aya;
    private String word;
    private String mean;
    private String audio_url;

    public BeWordMeaningItem(int id, String aya, String word, String mean, String audio_url) {
        this.id = id;
        this.aya = aya;
        this.word = word;
        this.mean = mean;
        this.audio_url = audio_url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAya() {
        return aya;
    }

    public void setAya(String aya) {
        this.aya = aya;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMean() {
        return mean;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }

    public String getAudio_url() {
        return audio_url;
    }

    public void setAudio_url(String audio_url) {
        this.audio_url = audio_url;
    }
}

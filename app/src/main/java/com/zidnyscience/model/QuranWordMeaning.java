package com.zidnyscience.model;

public class QuranWordMeaning {
    private String word;
    private String word_mean;

    public QuranWordMeaning(String word, String word_mean) {
        this.word = word;
        this.word_mean = word_mean;
    }

    public String getword() {
        return word;
    }

    public void setword(String word) {
        this.word = word;
    }

    public String getWord_mean() {
        return word_mean;
    }

    public void setWord_mean(String word_mean) {
        this.word_mean = word_mean;
    }
}

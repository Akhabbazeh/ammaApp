package com.zidnyscience.model;

public class WordMeaning {
    private String surah_number;
    private String aya_number;
    private String surah_name;
    private String page_number;
    private String aya_glyph;
    private String word_glyph;
    private String word;
    private String aya_words;
    private String mean;

    // Getters and Setters
    public String getSurahNumber() {
        return surah_number;
    }

    public void setSurahNumber(String surah_number) {
        this.surah_number = surah_number;
    }

    public String getAyaNumber() {
        return aya_number;
    }

    public void setAyaNumber(String aya_number) {
        this.aya_number = aya_number;
    }

    public String getSurahName() {
        return surah_name;
    }

    public void setSurahName(String surah_name) {
        this.surah_name = surah_name;
    }

    public String getPageNumber() {
        return page_number;
    }

    public void setPageNumber(String page_number) {
        this.page_number = page_number;
    }

    public String getAyaGlyph() {
        return aya_glyph;
    }

    public void setAyaGlyph(String aya_glyph) {
        this.aya_glyph = aya_glyph;
    }

    public String getWordGlyph() {
        return word_glyph;
    }

    public void setWordGlyph(String word_glyph) {
        this.word_glyph = word_glyph;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getAyaWords() {
        return aya_words;
    }

    public void setAyaWords(String aya_words) {
        this.aya_words = aya_words;
    }

    public String getMean() {
        return mean;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }
}

package com.zidnyscience.model;

public class QuranWord {
    private String wordId;
    private String english_lemma;
    private String english_translation;
    private String sura_number;
    private String ayah_number;
    private String page_number;
    private String line_number;
    private String order;
    private String glyph_type_id;
    private String glyph_code;
    private String word_arabic_id;
    private boolean selected;


    // Constructor

    public QuranWord(String wordId, String english_lemma, String english_translation, String sura_number, String ayah_number, String page_number, String line_number, String order, String glyph_type_id, String glyph_code, String word_arabic_id) {
        this.wordId = wordId;
        this.english_lemma = english_lemma;
        this.english_translation = english_translation;
        this.sura_number = sura_number;
        this.ayah_number = ayah_number;
        this.page_number = page_number;
        this.line_number = line_number;
        this.order = order;
        this.glyph_type_id = glyph_type_id;
        this.glyph_code = glyph_code;
        this.word_arabic_id = word_arabic_id;
        this.selected = false;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getWordId() {
        return wordId;
    }

    public void setWordId(String wordId) {
        this.wordId = wordId;
    }

    public String getEnglish_lemma() {
        return english_lemma;
    }

    public void setEnglish_lemma(String english_lemma) {
        this.english_lemma = english_lemma;
    }

    public String getEnglish_translation() {
        return english_translation;
    }

    public void setEnglish_translation(String english_translation) {
        this.english_translation = english_translation;
    }

    public String getSura_number() {
        return sura_number;
    }

    public void setSura_number(String sura_number) {
        this.sura_number = sura_number;
    }

    public String getAyah_number() {
        return ayah_number;
    }

    public void setAyah_number(String ayah_number) {
        this.ayah_number = ayah_number;
    }

    public String getPage_number() {
        return page_number;
    }

    public void setPage_number(String page_number) {
        this.page_number = page_number;
    }

    public String getLine_number() {
        return line_number;
    }

    public void setLine_number(String line_number) {
        this.line_number = line_number;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getGlyph_type_id() {
        return glyph_type_id;
    }

    public void setGlyph_type_id(String glyph_type_id) {
        this.glyph_type_id = glyph_type_id;
    }

    public String getGlyph_code() {
        return glyph_code;
    }

    public void setGlyph_code(String glyph_code) {
        this.glyph_code = glyph_code;
    }

    public String getWord_arabic_id() {
        return word_arabic_id;
    }

    public void setWord_arabic_id(String word_arabic_id) {
        this.word_arabic_id = word_arabic_id;
    }
}

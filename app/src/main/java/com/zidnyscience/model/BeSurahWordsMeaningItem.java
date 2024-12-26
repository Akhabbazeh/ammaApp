package com.zidnyscience.model;

import java.util.List;

public class BeSurahWordsMeaningItem {
    private int surah_number;
    private List<WordMeaning> wordMeaningList;

    public BeSurahWordsMeaningItem(int surah_number, List<WordMeaning> wordMeaningList) {
        this.surah_number = surah_number;
        this.wordMeaningList = wordMeaningList;
    }

    public int getSurah_number() {
        return surah_number;
    }

    public void setSurah_number(int surah_number) {
        this.surah_number = surah_number;
    }

    public List<WordMeaning> getWordMeaningList() {
        return wordMeaningList;
    }

    public void setWordMeaningList(List<WordMeaning> wordMeaningList) {
        this.wordMeaningList = wordMeaningList;
    }
}

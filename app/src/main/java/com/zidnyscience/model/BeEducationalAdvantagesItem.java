package com.zidnyscience.model;

import java.util.List;

public class BeEducationalAdvantagesItem {
    private int surah_number;
    private List<EducationalAdvantages> educationalAdvantagesList;


    public BeEducationalAdvantagesItem(int surah_number, List<EducationalAdvantages> educationalAdvantagesList) {
        this.surah_number = surah_number;
        this.educationalAdvantagesList = educationalAdvantagesList;
    }

    public int getSurah_number() {
        return surah_number;
    }

    public void setSurah_number(int surah_number) {
        this.surah_number = surah_number;
    }

    public List<EducationalAdvantages> getEducationalAdvantagesList() {
        return educationalAdvantagesList;
    }

    public void setEducationalAdvantagesList(List<EducationalAdvantages> educationalAdvantagesList) {
        this.educationalAdvantagesList = educationalAdvantagesList;
    }
}

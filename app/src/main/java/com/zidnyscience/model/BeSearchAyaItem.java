package com.zidnyscience.model;

import com.google.gson.annotations.SerializedName;

public class BeSearchAyaItem {
        @SerializedName("Surah Number")
        private String surahNumber;

        @SerializedName("Surah Name (Arabic)")
        private String surahNameArabic;

        @SerializedName("Ayah Number In Surah")
        private String ayahNumberInSurah;

        @SerializedName("Ayah Text (Arabic)")
        private String ayahTextArabic;

        @SerializedName("Ayah Text (Emlaey)")
        private String ayahTextEmlaey;

        @SerializedName("Page")
        private String page;

        // Getters
        public String getSurahNumber() {
            return surahNumber;
        }

        public String getSurahNameArabic() {
            return surahNameArabic;
        }

        public String getAyahNumberInSurah() {
            return ayahNumberInSurah;
        }

        public String getAyahTextArabic() {
            return ayahTextArabic;
        }

        public String getAyahTextEmlaey() {
            return ayahTextEmlaey;
        }

        public String getPage() {
            return page;
        }

        @Override
        public String toString() {
                return surahNameArabic;
        }

}

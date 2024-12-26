package com.zidnyscience.utils;

import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zidnyscience.model.WordMeaning;
import com.zidnyscience.model.BeSurahWordsMeaningItem;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordMeaningLoader {

    private final Context context;

    public WordMeaningLoader(Context context) {
        this.context = context;
    }

    /**
     * Loads a list of WordMeaning objects from a JSON file in the assets folder.
     *
     * @param fileName The name of the JSON file located in the assets folder.
     * @return A list of WordMeaning objects or null if an error occurs.
     */
    public List<WordMeaning> getWordMeaning(String fileName) {
        InputStream is = null;
        BufferedReader reader = null;
        try {
            is = context.getAssets().open(fileName);
            reader = new BufferedReader(new InputStreamReader(is));

            // Read JSON data into a StringBuilder
            StringBuilder jsonString = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonString.append(line);
            }

            // Convert JSON to List<WordMeaning> using Gson
            Gson gson = new Gson();
            Type listType = new TypeToken<List<WordMeaning>>() {}.getType();
            return gson.fromJson(jsonString.toString(), listType);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (reader != null) reader.close();
                if (is != null) is.close();
            } catch (Exception ignored) {
            }
        }
    }

    /**
     * Converts a list of WordMeaning objects into a list of BeSurahWordsMeaningItem objects.
     *
     * @param wordMeaningList The list of WordMeaning objects.
     * @return A list of BeSurahWordsMeaningItem objects grouped by surah number.
     */
    public List<BeSurahWordsMeaningItem> getBeSurahWordsMeaningItems(List<WordMeaning> wordMeaningList) {
        Map<Integer, List<WordMeaning>> groupedBySurah = new HashMap<>();
        for (WordMeaning wordMeaning : wordMeaningList) {
            int surahNumber = Integer.parseInt(wordMeaning.getSurahNumber());
            if (!groupedBySurah.containsKey(surahNumber)) {
                groupedBySurah.put(surahNumber, new ArrayList<>());
            }
            groupedBySurah.get(surahNumber).add(wordMeaning);
        }

        List<BeSurahWordsMeaningItem> surahWordsMeaningItems = new ArrayList<>();
        for (Map.Entry<Integer, List<WordMeaning>> entry : groupedBySurah.entrySet()) {
            int surahNumber = entry.getKey();
            List<WordMeaning> meanings = entry.getValue();
            surahWordsMeaningItems.add(new BeSurahWordsMeaningItem(surahNumber, meanings));
        }

        return surahWordsMeaningItems;
    }

    /**
     * Loads WordMeaning data from a JSON file and converts it to a list of BeSurahWordsMeaningItem objects.
     *
     * @param fileName The name of the JSON file located in the assets folder.
     * @return A list of BeSurahWordsMeaningItem objects or null if an error occurs.
     */
    public List<BeSurahWordsMeaningItem> loadBeSurahWordsMeaningItems(String fileName) {
        List<WordMeaning> wordMeaningList = getWordMeaning(fileName);
        if (wordMeaningList != null) {
            return getBeSurahWordsMeaningItems(wordMeaningList);
        }
        return null;
    }
}

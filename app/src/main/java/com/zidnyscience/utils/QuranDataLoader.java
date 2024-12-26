package com.zidnyscience.utils;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zidnyscience.model.QuranWord;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class QuranDataLoader {

    public static List<QuranWord> loadDataFromAsset(Context context) {
        StringBuilder json = new StringBuilder();
        try {

            InputStream is = context.getAssets().open("amma.json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = reader.readLine()) != null) {
                json.append(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        Gson gson = new Gson();
        Type listType = new TypeToken<List<QuranWord>>() {}.getType();
        return gson.fromJson(json.toString(), listType);
    }

    public static List<QuranWord> getWordsForPage(Context context, String pageNumber) {

        List<QuranWord> words = loadDataFromAsset(context);


        List<QuranWord> filteredWords = new ArrayList<>();
        for (QuranWord word : words) {

            if (word.getPage_number() != null && !word.getPage_number().isEmpty() &&
                    word.getPage_number().equals(pageNumber)) {
                filteredWords.add(word);
            }
        }
        return filteredWords;
    }


}

package com.zidnyscience.utils;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zidnyscience.model.BeSearchAyaItem;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SearchDataLoader {

    private Context context;
    public SearchDataLoader(Context context) {
        this.context = context;
    }

    public List<BeSearchAyaItem> readAyahsFromJson(String fileName) {
        List<BeSearchAyaItem> ayahList = new ArrayList<>();
        try {

            InputStream is = context.getAssets().open(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder json = new StringBuilder();
            String line;


            while ((line = reader.readLine()) != null) {
                json.append(line);
            }
            reader.close();

            Gson gson = new Gson();
            Type listType = new TypeToken<List<BeSearchAyaItem>>() {}.getType();
            ayahList = gson.fromJson(json.toString(), listType);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ayahList;
    }
}


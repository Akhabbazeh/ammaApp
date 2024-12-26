package com.zidnyscience.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.net.NetworkCapabilities;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.FragmentActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.zidnyscience.ammaApp.R;
import com.zidnyscience.model.QuranWord;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.paperdb.Paper;


public class Tools {
    private static final String quran_data = "QuranData";


    public static void set_quran_data(List<List<List<QuranWord>>> data)
    {
        Paper.book().write(quran_data,data);
    }


    public static List<List<List<QuranWord>>> get_quran_data()
    {
        return Paper.book().read(quran_data);
    }

    public static Typeface getFrontsFromPageNumber(Context context, String page_number) {
        switch (page_number) {
            case "1":
                return ResourcesCompat.getFont(context, R.font.page_01);
            case "582":
                return ResourcesCompat.getFont(context, R.font.page_582);
            case "583":
                return ResourcesCompat.getFont(context, R.font.page_583);
           
            case "584":
                return ResourcesCompat.getFont(context, R.font.page_584);
           
            case "585":
                return ResourcesCompat.getFont(context, R.font.page_585);
           
            case "586":
                return ResourcesCompat.getFont(context, R.font.page_586);
           
            case "587":
                return ResourcesCompat.getFont(context, R.font.page_587);
           
            case "588":
                return ResourcesCompat.getFont(context, R.font.page_588);
           
            case "589":
                return ResourcesCompat.getFont(context, R.font.page_589);
           
            case "590":
                return ResourcesCompat.getFont(context, R.font.page_590);
           
            case "591":
                return ResourcesCompat.getFont(context, R.font.page_591);
           
            case "592":
                return ResourcesCompat.getFont(context, R.font.page_592);
           
            case "593":
                return ResourcesCompat.getFont(context, R.font.page_593);
           
            case "594":
                return ResourcesCompat.getFont(context, R.font.page_594);
           
            case "595":
                return ResourcesCompat.getFont(context, R.font.page_595);
           
            case "596":
                return ResourcesCompat.getFont(context, R.font.page_596);
           
            case "597":
                return ResourcesCompat.getFont(context, R.font.page_597);
           
            case "598":
                return ResourcesCompat.getFont(context, R.font.page_598);
           
            case "599":
                return ResourcesCompat.getFont(context, R.font.page_599);
           
            case "600":
                return ResourcesCompat.getFont(context, R.font.page_600);
           
            case "601":
                return ResourcesCompat.getFont(context, R.font.page_601);
           
            case "602":
                return ResourcesCompat.getFont(context, R.font.page_602);
           
            case "603":
                return ResourcesCompat.getFont(context, R.font.page_603);
           
            case "604":
                return ResourcesCompat.getFont(context, R.font.page_604);
           
            default:
               
        }

    return null;
    
    }

    public static int getHeaderFromPageNumber(Context context, String surah_number) {
        switch (Integer.parseInt(surah_number)) {
            case 1:
                return R.drawable.banner000;
            case 78:
                return R.drawable.banner0078;
            case 79:
                return R.drawable.banner0079;
            case 80:
                return R.drawable.banner0080;
            case 81:
                return R.drawable.banner0081;
            case 82:
                return R.drawable.banner0082;
            case 83:
                return R.drawable.banner0083;
            case 84:
                return R.drawable.banner0084;
            case 85:
                return R.drawable.banner0085;
            case 86:
                return R.drawable.banner0086;
            case 87:
                return R.drawable.banner0087;
            case 88:
                return R.drawable.banner0088;
            case 89:
                return R.drawable.banner0089;
            case 90:
                return R.drawable.banner0090;
            case 91:
                return R.drawable.banner0091;
            case 92:
                return R.drawable.banner0092;
            case 93:
                return R.drawable.banner0093;
            case 94:
                return R.drawable.banner0094;
            case 95:
                return R.drawable.banner0095;
            case 96:
                return R.drawable.banner0096;
            case 97:
                return R.drawable.banner0097;
            case 98:
                return R.drawable.banner0098;
            case 99:
                return R.drawable.banner0099;
            case 100:
                return R.drawable.banner00100;
            case 101:
                return R.drawable.banner0101;
            case 102:
                return R.drawable.banner00102;
            case 103:
                return R.drawable.banner00103;
            case 104:
                return R.drawable.banner00104;
            case 105:
                return R.drawable.banner00105;
            case 106:
                return R.drawable.banner00106;
            case 107:
                return R.drawable.banner00107;
            case 108:
                return R.drawable.banner00108;
            case 109:
                return R.drawable.banner00109;
            case 110:
                return R.drawable.banner00110;
            case 111:
                return R.drawable.banner00111;
            case 112:
                return R.drawable.banner00112;
            case 113:
                return R.drawable.banner00113;
            case 114:
                return R.drawable.banner00114;
            default:
                // مورد افتراضي في حالة لم يكن هناك تطابق مع رقم السورة
                return -1;
        }
    }


    public static List<List<QuranWord>> getQuranPageData(Context context, String numberPage) {

        List<QuranWord> wordsForPage = QuranDataLoader.getWordsForPage(context, numberPage);
        Map<String, List<QuranWord>> linesMap = new HashMap<>();

        for (QuranWord word : wordsForPage) {
            String lineNumber = word.getLine_number();
            if (!linesMap.containsKey(lineNumber)) {
                linesMap.put(lineNumber, new ArrayList<>());
            }
            linesMap.get(lineNumber).add(word);
        }
        for (Map.Entry<String, List<QuranWord>> entry : linesMap.entrySet()) {
            List<QuranWord> wordsInLine = entry.getValue();

            Collections.sort(wordsInLine, new Comparator<QuranWord>() {
                @Override
                public int compare(QuranWord word1, QuranWord word2) {
                    return Integer.compare(Integer.parseInt(word2.getOrder()), Integer.parseInt(word1.getOrder()));
                }
            });
        }

        List<List<QuranWord>> lines = new ArrayList<>(linesMap.values());
        Collections.sort(lines, new Comparator<List<QuranWord>>() {
            @Override
            public int compare(List<QuranWord> line1, List<QuranWord> line2) {
                String lineNumber1 = line1.get(0).getLine_number();
                String lineNumber2 = line2.get(0).getLine_number();
                return Integer.compare(Integer.parseInt(lineNumber1), Integer.parseInt(lineNumber2));
            }
        });

        for (int i = 0; i < lines.size(); i++) {
            Collections.reverse(lines.get(i));
        }


        return lines;
    }

    public static void displayImageDrawable(Context context, ImageView img, @DrawableRes int drawable) {
        try {
            Glide.with(context).load(drawable)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(img);
        } catch (Exception e) {
        }
    }

    public static int getAudioFileByTitle(Context context, String aya) {
        try {
            String resourceName = aya.toLowerCase();

            int resourceId = context.getResources().getIdentifier(resourceName, "raw", context.getPackageName());


            if (resourceId == 0) {
                throw new Resources.NotFoundException("Resource not found for: " + aya);
            }

            return resourceId;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static boolean isInternetAvailable(Context context) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                android.net.Network network = connectivityManager.getActiveNetwork();
                if (network != null) {
                    NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(network);
                    return networkCapabilities != null &&
                            (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                                    networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                                    networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET));
                }
            } else {

                android.net.NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                return networkInfo != null && networkInfo.isConnected();
            }
        }
        return false;
    }


    public static void makeSnakbar(Context context, FragmentActivity fragmentActivity, View root, String message, int color)
    {
        Snackbar snackbar = Snackbar.make( root, message , BaseTransientBottomBar.LENGTH_LONG)
                .setBackgroundTint(context.getResources().getColor(color))
                .setTextColor(context.getResources().getColor(R.color.white));

        View view = snackbar.getView();
        TextView txtSnakbarText = view.findViewById(com.google.android.material.R.id.snackbar_text);
        txtSnakbarText.setTypeface(ResourcesCompat.getFont(fragmentActivity, R.font.hekaya));
        snackbar.show();
    }}

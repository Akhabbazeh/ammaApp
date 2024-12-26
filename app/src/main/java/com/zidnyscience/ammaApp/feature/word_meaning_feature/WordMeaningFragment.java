package com.zidnyscience.ammaApp.feature.word_meaning_feature;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zidnyscience.ammaApp.R;
import com.zidnyscience.ammaApp.feature.anashid_feature.AdapterRecycleAnasid;
import com.zidnyscience.ammaApp.feature.moshaf_almoallem_feature.AdapterRecycleSwarTeacher;
import com.zidnyscience.ammaApp.feature.moshaf_almoallem_feature.AdapterSpinnerTeacherTopic;
import com.zidnyscience.ammaApp.feature.npages_feature.nPagesFragment;
import com.zidnyscience.model.BeSurahWordsMeaningItem;
import com.zidnyscience.model.BeWordMeaningItem;
import com.zidnyscience.model.WordMeaning;
import com.zidnyscience.utils.Tools;
import com.zidnyscience.utils.WordMeaningLoader;

import java.util.ArrayList;
import java.util.List;


public class WordMeaningFragment extends Fragment
{
    private View view;
    private ImageView img_amma_logo;
    private ImageView word_meaning_fragment_background;
    private Spinner spinnerSwar;
    private RecyclerView recycle_word_meaning;
    private List<WordMeaning> wordMeaningList;
    private AdapterRecycleWordMeaning adapterRecycleWordMeaning;
    private AdapterSpinnerSwarTopic adapterSpinnerSwarTopic;
    private List<String> swarList;
    private WordMeaningLoader wordMeaningLoader;
    private List<BeSurahWordsMeaningItem> surahWordsMeaningItems;


    public WordMeaningFragment()
    {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.word_meaning_fragment, container, false);
        img_amma_logo = view.findViewById(R.id.img_amma_logo);
        word_meaning_fragment_background = view.findViewById(R.id.word_meaning_fragment_background);
        spinnerSwar = view.findViewById(R.id.spinnerSwar);
        recycle_word_meaning = view.findViewById(R.id.recycle_word_meaning);



        initStatuesBar();
        initComponenets();
        initChooseSwarSpinner();
        intiRecycleSwarMeaning();
        getData();
        return view;
    }

    private void getData() {
        wordMeaningLoader = new WordMeaningLoader(getContext());
        surahWordsMeaningItems = wordMeaningLoader.loadBeSurahWordsMeaningItems("hasanen_makhlouf_words.json");
        spinnerSwar.setSelection(0);
        adapterRecycleWordMeaning.setWordMeaningList(surahWordsMeaningItems.get(0).getWordMeaningList());
    }

    private void initComponenets() {
        Tools.displayImageDrawable(getContext(),img_amma_logo,R.drawable.amma_logo);
        Tools.displayImageDrawable(getContext(),word_meaning_fragment_background,R.drawable.background_pattern);
    }

    private void initStatuesBar()
    {
        getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.background_color));


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    private void initChooseSwarSpinner()
    {

        swarList = new ArrayList<>();
        swarList.add("سورة النبأ");
        swarList.add("سورة النازعات");
        swarList.add("سورة عبس");
        swarList.add("سورة التكوير");
        swarList.add("سورة الانفطار");
        swarList.add("سورة المطففين");
        swarList.add("سورة الانشقاق");
        swarList.add("سورة البروج");
        swarList.add("سورة الطارق");
        swarList.add("سورة الأعلى");
        swarList.add("سورة الغاشية");
        swarList.add("سورة الفجر");
        swarList.add("سورة البلد");
        swarList.add("سورة الشمس");
        swarList.add("سورة الليل");
        swarList.add("سورة الضحى");
        swarList.add("سورة الشرح");
        swarList.add("سورة التين");
        swarList.add("سورة العلق");
        swarList.add("سورة القدر");
        swarList.add("سورة البينة");
        swarList.add("سورة الزلزلة");
        swarList.add("سورة العاديات");
        swarList.add("سورة القارعة");
        swarList.add("سورة التكاثر");
        swarList.add("سورة العصر");
        swarList.add("سورة الهمزة");
        swarList.add("سورة الفيل");
        swarList.add("سورة قريش");
        swarList.add("سورة الماعون");
        swarList.add("سورة الكوثر");
        swarList.add("سورة الكافرون");
        swarList.add("سورة النصر");
        swarList.add("سورة المسد");
        swarList.add("سورة الإخلاص");
        swarList.add("سورة الفلق");
        swarList.add("سورة الناس");

        adapterSpinnerSwarTopic = new AdapterSpinnerSwarTopic(getContext(), swarList);

        spinnerSwar.setAdapter(adapterSpinnerSwarTopic);

        spinnerSwar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                recycle_word_meaning.smoothScrollToPosition(0);
                adapterRecycleWordMeaning.setWordMeaningList(surahWordsMeaningItems.get(position).getWordMeaningList());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void intiRecycleSwarMeaning(){
        wordMeaningList = new ArrayList<>();
        adapterRecycleWordMeaning= new AdapterRecycleWordMeaning(getContext(),wordMeaningList);
        recycle_word_meaning.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        recycle_word_meaning.setHasFixedSize(true);
        recycle_word_meaning.setAdapter(adapterRecycleWordMeaning);

        adapterRecycleWordMeaning.setOnClickListener(new AdapterRecycleWordMeaning.OnClickListener() {
            @Override
            public void OnSuraClickListener(int page_number, int anim_loading) {
                nPagesFragment pagesFragment = new nPagesFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("page_number", page_number);
                bundle.putInt("anim_loading", R.drawable.amma_logo);
                pagesFragment.setArguments(bundle);
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .replace(R.id.FrameLayout_main_activity, pagesFragment)
                        .addToBackStack("")
                        .commit();
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        adapterRecycleWordMeaning.stopAya();
    }

    @Override
    public void onPause() {
        super.onPause();
        adapterRecycleWordMeaning.stopAya();
    }


}


package com.zidnyscience.ammaApp.feature.start_feature;

import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.zidnyscience.ammaApp.R;
import com.zidnyscience.ammaApp.feature.about_us_feature.AboutUsFragment;
import com.zidnyscience.ammaApp.feature.anashid_feature.AnashidFragment;
import com.zidnyscience.ammaApp.feature.azkar_feature.AzkarFragment;
import com.zidnyscience.ammaApp.feature.educational_advantages_feature.EducationalAdvantagesFragment;
import com.zidnyscience.ammaApp.feature.moshaf_almoallem_feature.MoshafAlmoallemFragment;
import com.zidnyscience.ammaApp.feature.suwar_and_pages_feature.SuwarAndPagesFragment;
import com.zidnyscience.ammaApp.feature.choose_test_feature.ChooseTestFragment;
import com.zidnyscience.ammaApp.feature.word_meaning_feature.WordMeaningFragment;
import com.zidnyscience.utils.Tools;


public class StartFragment extends Fragment
{
    private View view;
    private ImageView start_fragment_background;
    private ImageView img_amma_logo;
    private ImageView img_quran_icon;
    private CardView btn_tests;
    private CardView btn_indix;
    private CardView btn_azkar;
    private CardView btn_the_teacher_koran;
    private CardView btn_anashid;
    private ImageView btn_about_us;
    private CardView btn_word_meaning;
    private CardView btn_educational_advantages;

    public StartFragment()
    {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.start_fragment, container, false);
        start_fragment_background = view.findViewById(R.id.start_fragment_background);
        img_amma_logo = view.findViewById(R.id.img_amma_logo);
        img_quran_icon = view.findViewById(R.id.img_quran_icon);
        btn_tests = view.findViewById(R.id.btn_tests);
        btn_indix = view.findViewById(R.id.btn_indix);
        btn_azkar = view.findViewById(R.id.btn_azkar);
        btn_the_teacher_koran = view.findViewById(R.id.btn_the_teacher_koran);
        btn_anashid = view.findViewById(R.id.btn_anashid);
        btn_about_us = view.findViewById(R.id.btn_about_us);
        btn_word_meaning = view.findViewById(R.id.btn_word_meaning);
        btn_educational_advantages = view.findViewById(R.id.btn_educational_advantages);

        initStatuesBar();
        initComponenets();
        return view;
    }

    private void initComponenets() {
        Tools.displayImageDrawable(getContext(),start_fragment_background,R.drawable.background_pattern);
        Tools.displayImageDrawable(getContext(),img_amma_logo,R.drawable.amma_logo);
        Tools.displayImageDrawable(getContext(),img_quran_icon,R.drawable.quran_icon);

        btn_tests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new ChooseTestFragment());
            }
        });

        btn_indix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new SuwarAndPagesFragment());
            }
        });

        btn_azkar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new AzkarFragment());
            }
        });

        btn_the_teacher_koran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new MoshafAlmoallemFragment());
            }
        });

        btn_anashid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new AnashidFragment());
            }
        });

        btn_about_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new AboutUsFragment());
            }
        });

        btn_word_meaning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new WordMeaningFragment());
            }
        });

        btn_educational_advantages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new EducationalAdvantagesFragment());
            }
        });
    }


    private void initStatuesBar()
    {
        requireActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.background_color));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requireActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    private void setFragment(Fragment fragment) {
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.FrameLayout_main_activity, fragment)
                .addToBackStack("")
                .commit(); }

}


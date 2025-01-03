package com.zidnyscience.ammaApp.feature.suwar_and_pages_feature;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.zidnyscience.ammaApp.R;
import com.zidnyscience.ammaApp.feature.pages_feature.nPagesFragment;
import com.zidnyscience.ammaApp.feature.pages_index.PagesIndexFragment;
import com.zidnyscience.ammaApp.feature.swar_feature.SwarFragment;
import com.zidnyscience.model.BeSearchAyaItem;
import com.zidnyscience.utils.SearchDataLoader;
import com.zidnyscience.utils.Tools;

import java.util.ArrayList;
import java.util.List;


public class SuwarAndPagesFragment extends Fragment
{
    private View view;
    private ImageView img_amma_logo;
    private ImageView img_quran_icon;
    private TextView txt_pages;
    private TextView txt_swar;
    private String selectedFragment = "";
    private FrameLayout framelayout_tab_layout;
    private View view_swar;
    private View view_pages;
    private LinearLayout txt_swar_layout;
    private LinearLayout txt_pages_layout;
    private ImageView fragment_background;
    private LinearLayout search_layout;
    private boolean isNew = true;
    boolean isPagesSelected = false;
    private AutoCompleteTextView auto_complete;
    private SearchAdapter searchAdapter;
    private List<BeSearchAyaItem> beSearchAyaItems;
    private Handler handler = new Handler();

    public SuwarAndPagesFragment()
    {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.suwar_and_pages_fragment, container, false);
        img_amma_logo = view.findViewById(R.id.img_amma_logo);
        txt_pages = view.findViewById(R.id.txt_pages);
        txt_swar = view.findViewById(R.id.txt_swar);
        framelayout_tab_layout = view.findViewById(R.id.framelayout_tab_layout);
        view_pages = view.findViewById(R.id.view_pages);
        view_swar  = view.findViewById(R.id.view_swar);
        txt_swar_layout = view.findViewById(R.id.txt_swar_layout);
        txt_pages_layout  = view.findViewById(R.id.txt_pages_layout);
        fragment_background  = view.findViewById(R.id.fragment_background);
        search_layout = view.findViewById(R.id.search_layout);
        auto_complete = view.findViewById(R.id.auto_complete);
        Tools.displayImageDrawable(getContext(),fragment_background,R.drawable.background_pattern);


        initStatuesBar();
        initTabLayout();
        initComponenets();
        handler.postDelayed(fetchDataRunnable, 2000);

        return view;
    }


    private void intiAutoCompleteTextView() {
        beSearchAyaItems = new ArrayList<>();
        SearchDataLoader searchDataLoader = new SearchDataLoader(getContext());
        List<BeSearchAyaItem> beSearchAyaItems = searchDataLoader.readAyahsFromJson("search_aya_info.json");
        searchAdapter = new SearchAdapter(getContext(), beSearchAyaItems);
        auto_complete.setThreshold(1);
        auto_complete.setAdapter(searchAdapter);

        searchAdapter.setOnClickListener(new SearchAdapter.OnClickListener() {
            @Override
            public void OnSuraClickListener(int page_number, int anim_loading) {
                nPagesFragment pagesFragment = new nPagesFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("page_number", page_number);
                bundle.putInt("anim_loading", R.drawable.amma_logo);
                pagesFragment.setArguments(bundle);
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left,R.anim.enter_from_left,R.anim.exit_to_right)
                        .replace(R.id.FrameLayout_main_activity, pagesFragment)
                        .addToBackStack("")
                        .commit();
            }
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initTabLayout() {
        
        framelayout_tab_layout.setOnTouchListener(new OnSwipeTouchListener(requireActivity()) {
            public void onSwipeTop() {
            }
            public void onSwipeRight() {
                txt_pages.callOnClick();
               }
            public void onSwipeLeft() {
                txt_swar.callOnClick();
            }
            public void onSwipeBottom() {
            }

        });

        txt_pages.setOnClickListener(v -> switchToFragment("pages"));
        txt_swar.setOnClickListener(v -> switchToFragment("swar"));

        switchToFragment(selectedFragment.isEmpty() ? "swar" : selectedFragment);
    }

    private void switchToFragment(String fragmentName) {
        if (!selectedFragment.equals(fragmentName)) {
            selectedFragment = fragmentName;

            isPagesSelected = "pages".equals(fragmentName);
            txt_pages_layout.setSelected(isPagesSelected);
            txt_swar_layout.setSelected(!isPagesSelected);
            search_layout.setVisibility(!isPagesSelected ? View.VISIBLE : View.GONE);
            view_swar.setVisibility(!isPagesSelected ? View.VISIBLE : View.GONE);
            view_pages.setVisibility(isPagesSelected ? View.VISIBLE : View.GONE);
            txt_pages.setTextColor(getContext().getResources().getColor(isPagesSelected ? R.color.white : R.color.black));
            txt_swar.setTextColor(getContext().getResources().getColor(isPagesSelected ? R.color.black : R.color.white));

            if (isPagesSelected){
                replaceFragment(new PagesIndexFragment());
            }else {
                replaceFragment(new SwarFragment());
            }

        }
    }

    private Runnable fetchDataRunnable = () -> {
        if (isAdded()) {
            intiAutoCompleteTextView();
        }
    };

    public void replaceFragment(Fragment child_fragment) {
        FragmentManager childFragMan = getChildFragmentManager();
        FragmentTransaction childFragTrans = childFragMan.beginTransaction();
        childFragTrans.replace(R.id.framelayout_tab_layout, child_fragment);
        childFragTrans.commit();

    }

    private void initComponenets() {
        Tools.displayImageDrawable(getContext(),img_amma_logo,R.drawable.amma_logo);
        if (!isNew){
            txt_pages_layout.setSelected(isPagesSelected);
            txt_swar_layout.setSelected(!isPagesSelected);
            search_layout.setVisibility(!isPagesSelected ? View.VISIBLE : View.GONE);
            view_swar.setVisibility(!isPagesSelected ? View.VISIBLE : View.GONE);
            view_pages.setVisibility(isPagesSelected ? View.VISIBLE : View.GONE);
            txt_pages.setTextColor(getContext().getResources().getColor(isPagesSelected ? R.color.white : R.color.black));
            txt_swar.setTextColor(getContext().getResources().getColor(isPagesSelected ? R.color.black : R.color.white));

        }else {
            isNew = false;
        }
    }


    private void initStatuesBar()
    {
        requireActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.background_color));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requireActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

}


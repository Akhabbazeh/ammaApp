package com.zidnyscience.ammaApp.feature.choose_test_feature;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zidnyscience.ammaApp.R;
import com.zidnyscience.ammaApp.feature.tests_feature.TestsFragment;
import com.zidnyscience.model.BeTestItem;
import com.zidnyscience.utils.Tools;

import java.util.ArrayList;
import java.util.List;


public class ChooseTestFragment extends Fragment
{
    private View view;
    private ImageView start_fragment_background;
    private ImageView img_amma_logo;
    private ImageView img_tests_icon;
    private LinearLayout no_tests_added_layout;
    private RecyclerView recycle_tests_added;
    private RecyclerView recycle_tests;
    private AdapterRecycleChooseTest adapterRecycleChooseTest;
    private AdapterRecycleChooseTest adapterRecycleChooseTestAdded;
    private List<BeTestItem> beTestItemList;
    private List<BeTestItem> beTestItemListAdded;
    private LinearLayout recycle_tests_added_layout;
    private ImageView btn_play_test;


    public ChooseTestFragment()
    {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.choose_test_fragment, container, false);
        start_fragment_background = view.findViewById(R.id.start_fragment_background);
        img_amma_logo = view.findViewById(R.id.img_amma_logo);
        img_tests_icon = view.findViewById(R.id.img_tests_icon);
        no_tests_added_layout = view.findViewById(R.id.no_tests_added_layout);
        recycle_tests_added = view.findViewById(R.id.recycle_tests_added);
        recycle_tests = view.findViewById(R.id.recycle_tests);
        recycle_tests_added_layout = view.findViewById(R.id.recycle_tests_added_layout);
        btn_play_test = view.findViewById(R.id.btn_play_test);

        initStatuesBar();
        initComponenets();
        intiRecycleTestsAdded();
        intiRecycleTests();
        return view;
    }

    private void initComponenets() {
        recycle_tests_added_layout.setVisibility(View.GONE);
        no_tests_added_layout.setVisibility(View.VISIBLE);
        Tools.displayImageDrawable(getContext(),start_fragment_background,R.drawable.background_pattern);
        Tools.displayImageDrawable(getContext(),img_amma_logo,R.drawable.amma_logo);
        Tools.displayImageDrawable(getContext(),img_tests_icon,R.drawable.tests_icon);

        btn_play_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .replace(R.id.FrameLayout_main_activity, new TestsFragment())
                        .addToBackStack("")
                        .commit();
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

    private void intiRecycleTestsAdded(){
        beTestItemListAdded = new ArrayList<>();
        adapterRecycleChooseTestAdded = new AdapterRecycleChooseTest(getContext(),true,beTestItemListAdded);
        recycle_tests_added.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        recycle_tests_added.setHasFixedSize(true);
        recycle_tests_added.setAdapter(adapterRecycleChooseTestAdded);

        adapterRecycleChooseTestAdded.setNoItemAddedListener(new AdapterRecycleChooseTest.NoItemAddedListener() {
            @Override
            public void OnNoItemAddedListener() {
                recycle_tests_added_layout.setVisibility(View.GONE);
                no_tests_added_layout.setVisibility(View.VISIBLE);
            }
        });
    }

    private void intiRecycleTests(){
        beTestItemList = new ArrayList<>();
        for (int i = 1; i <= 37; i++) {
            int drawableResourceId = getContext().getResources().getIdentifier("p00" + (i + 77), "drawable", getContext().getPackageName());
            beTestItemList.add(new BeTestItem(i, drawableResourceId));
        }

        adapterRecycleChooseTest = new AdapterRecycleChooseTest(getContext(),false,beTestItemList);
        recycle_tests.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        recycle_tests.setHasFixedSize(true);
        recycle_tests.setAdapter(adapterRecycleChooseTest);

        adapterRecycleChooseTest.setOnClickListener(new AdapterRecycleChooseTest.OnClickListener() {
            @Override
            public void OnSuraClickListener(BeTestItem beTestItem) {
                recycle_tests_added_layout.setVisibility(View.VISIBLE);
                no_tests_added_layout.setVisibility(View.GONE);
                if (!isItemInList(beTestItem.getId(), adapterRecycleChooseTestAdded.getBeTestItemList())){
                    beTestItemListAdded.add(0,beTestItem);
                    adapterRecycleChooseTestAdded.notifyDataSetChanged();
                }else {
                    Toast.makeText(getContext(),"العنصر الذي تحاول اضافته مضاف بالفعل",Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private boolean isItemInList(int id, List<BeTestItem> itemList) {
        for (BeTestItem item : itemList) {
            if (item.getId() == id) {
                return true;
            }
        }
        return false;
    }


}


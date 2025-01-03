package com.zidnyscience.ammaApp.feature.pages_feature;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.duolingo.open.rtlviewpager.RtlViewPager;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.zidnyscience.ammaApp.R;
import com.zidnyscience.model.QuranWord;
import com.zidnyscience.utils.Tools;

import java.util.ArrayList;
import java.util.List;

public class nPagesFragment extends Fragment
{
    private View view;
    private RtlViewPager viewPager;
    private List<List<List<QuranWord>>> ammaPages;
    private AmmaPagerAdapter ammaPagerAdapter;
    private ShimmerFrameLayout shimmer_view;
    private ImageView makhtota_img;
    private int position = -1;
    private int anim_loading = -1;
    private ImageView img_background_pattern;
    private Handler handler = new Handler();

    public nPagesFragment()
    {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.npages_fragment, container, false);
        viewPager = view.findViewById(R.id.viewPager);
        shimmer_view = view.findViewById(R.id.shimmer_view);
        makhtota_img = view.findViewById(R.id.makhtota_img);
        img_background_pattern = view.findViewById(R.id.img_background_pattern);
        Tools.displayImageDrawable(getContext(),img_background_pattern,R.drawable.background_pattern);


        Bundle bundle = getArguments();
        if(bundle != null)
        {
            position = bundle.getInt("page_number",0);
            anim_loading = bundle.getInt("anim_loading",0);
            Tools.displayImageDrawable(getContext(),makhtota_img,anim_loading);


        }

        initStatuesBar();
        initAmmaPages();
        getData();

        return view;
    }


    private Runnable fetchDataRunnable = () -> {
        if (isAdded()) {
            for (int i = 582; i <= 604; i++) {
                ammaPages.add(Tools.getQuranPageData(getContext(), String.valueOf(i)));
            }
            Tools.set_quran_data(ammaPages);
            ammaPagerAdapter.setAmmaPages(ammaPages);
            viewPager.setCurrentItem(position, false);
            hideLoadingData();
        }
    };

    private void getData() {

        if (Tools.get_quran_data() == null){
            showLoadingData();
            handler.postDelayed(fetchDataRunnable, 2000);

        }else {
            ammaPages = Tools.get_quran_data();
            ammaPagerAdapter.setAmmaPages(ammaPages);
            viewPager.setCurrentItem(position, false);
        }

    }

    private void showLoadingData() {
    shimmer_view.setVisibility(View.VISIBLE);
    shimmer_view.startShimmer();
    }

    private void hideLoadingData() {
        shimmer_view.setVisibility(View.GONE);
        shimmer_view.stopShimmer();
    }

    private void initAmmaPages() {
    ammaPages =new ArrayList<>();
    ammaPagerAdapter = new AmmaPagerAdapter(getContext(),ammaPages);
    viewPager.setAdapter(ammaPagerAdapter);

    }

    private void initStatuesBar()
    {
        getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.primary_color));


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        handler.removeCallbacks(fetchDataRunnable);
    }

}


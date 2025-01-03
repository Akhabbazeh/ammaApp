package com.zidnyscience.ammaApp.feature.swar_feature;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zidnyscience.ammaApp.R;
import com.zidnyscience.ammaApp.feature.pages_feature.nPagesFragment;
import com.zidnyscience.model.BeSwarIndexItem;

import java.util.ArrayList;
import java.util.List;


public class SwarFragment extends Fragment
{
    private View view;
    private RecyclerView recycle_swar;
    private List<BeSwarIndexItem> beSwarIndexItems;
    private AdapterRecycleSwarIndex adapterRecycleSwarIndex;


    public SwarFragment()
    {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.swar_fragment, container, false);
        recycle_swar = view.findViewById(R.id.recycle_swar);



        initStatuesBar();
        intiRecycleSwarIndex();
        getData();
        return view;
    }

    private void getData() {

        beSwarIndexItems.add(new BeSwarIndexItem(1, R.drawable.p0078, 582, "مكية", 40, 78)); // النبأ
        beSwarIndexItems.add(new BeSwarIndexItem(2, R.drawable.p0079, 583, "مكية", 46, 79)); // النازعات
        beSwarIndexItems.add(new BeSwarIndexItem(3, R.drawable.p0080, 585, "مكية", 42, 80)); // عبس
        beSwarIndexItems.add(new BeSwarIndexItem(4, R.drawable.p0081, 586, "مكية", 29, 81)); // التكوير
        beSwarIndexItems.add(new BeSwarIndexItem(5, R.drawable.p0082, 587, "مكية", 19, 82)); // الانفطار
        beSwarIndexItems.add(new BeSwarIndexItem(6, R.drawable.p0083, 587, "مكية", 36, 83)); // المطففين
        beSwarIndexItems.add(new BeSwarIndexItem(7, R.drawable.p0084, 589, "مكية", 25, 84)); // الانشقاق
        beSwarIndexItems.add(new BeSwarIndexItem(8, R.drawable.p0085, 590, "مكية", 22, 85)); // البروج
        beSwarIndexItems.add(new BeSwarIndexItem(9, R.drawable.p0086, 591, "مكية", 17, 86)); // الطارق
        beSwarIndexItems.add(new BeSwarIndexItem(10, R.drawable.p0087, 591, "مكية", 19, 87)); // الأعلى
        beSwarIndexItems.add(new BeSwarIndexItem(11, R.drawable.p0088, 592, "مكية", 26, 88)); // الغاشية
        beSwarIndexItems.add(new BeSwarIndexItem(12, R.drawable.p0089, 593, "مكية", 30, 89)); // الفجر
        beSwarIndexItems.add(new BeSwarIndexItem(13, R.drawable.p0090, 594, "مكية", 20, 90)); // البلد
        beSwarIndexItems.add(new BeSwarIndexItem(14, R.drawable.p0091, 595, "مكية", 15, 91)); // الشمس
        beSwarIndexItems.add(new BeSwarIndexItem(15, R.drawable.p0092, 595, "مكية", 21, 92)); // الليل
        beSwarIndexItems.add(new BeSwarIndexItem(16, R.drawable.p0093, 596, "مكية", 11, 93)); // الضحى
        beSwarIndexItems.add(new BeSwarIndexItem(17, R.drawable.p0094, 596, "مكية", 8, 94));  // الشرح
        beSwarIndexItems.add(new BeSwarIndexItem(18, R.drawable.p0095, 597, "مكية", 8, 95));  // التين
        beSwarIndexItems.add(new BeSwarIndexItem(19, R.drawable.p0096, 597, "مكية", 19, 96)); // العلق
        beSwarIndexItems.add(new BeSwarIndexItem(20, R.drawable.p0097, 598, "مكية", 5, 97));  // القدر
        beSwarIndexItems.add(new BeSwarIndexItem(21, R.drawable.p0098, 598, "مدنية", 8, 98)); // البينة
        beSwarIndexItems.add(new BeSwarIndexItem(22, R.drawable.p0099, 599, "مدنية", 8, 99)); // الزلزلة
        beSwarIndexItems.add(new BeSwarIndexItem(23, R.drawable.p00100, 599, "مكية", 11, 100)); // العاديات
        beSwarIndexItems.add(new BeSwarIndexItem(24, R.drawable.p00101, 600, "مكية", 11, 101)); // القارعة
        beSwarIndexItems.add(new BeSwarIndexItem(25, R.drawable.p00102, 600, "مكية", 8, 102));  // التكاثر
        beSwarIndexItems.add(new BeSwarIndexItem(26, R.drawable.p00103, 601, "مكية", 3, 103));  // العصر
        beSwarIndexItems.add(new BeSwarIndexItem(27, R.drawable.p00104, 601, "مكية", 9, 104));  // الهمزة
        beSwarIndexItems.add(new BeSwarIndexItem(28, R.drawable.p00105, 601, "مكية", 5, 105));  // الفيل
        beSwarIndexItems.add(new BeSwarIndexItem(29, R.drawable.p00106, 602, "مكية", 4, 106));  // قريش
        beSwarIndexItems.add(new BeSwarIndexItem(30, R.drawable.p00107, 602, "مكية", 7, 107));  // الماعون
        beSwarIndexItems.add(new BeSwarIndexItem(31, R.drawable.p00108, 602, "مكية", 3, 108));  // الكوثر
        beSwarIndexItems.add(new BeSwarIndexItem(32, R.drawable.p00109, 603, "مكية", 6, 109));  // الكافرون
        beSwarIndexItems.add(new BeSwarIndexItem(33, R.drawable.p00110, 603, "مدنية", 3, 110)); // النصر
        beSwarIndexItems.add(new BeSwarIndexItem(34, R.drawable.p00111, 603, "مكية", 5, 111));  // المسد
        beSwarIndexItems.add(new BeSwarIndexItem(35, R.drawable.p00112, 604, "مكية", 4, 112));  // الإخلاص
        beSwarIndexItems.add(new BeSwarIndexItem(36, R.drawable.p00113, 604, "مكية", 5, 113));  // الفلق
        beSwarIndexItems.add(new BeSwarIndexItem(37, R.drawable.p00114, 604, "مكية", 6, 114));  // الناس
        adapterRecycleSwarIndex.setSwarList(beSwarIndexItems);
    }

    private void initStatuesBar()
    {
        getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.background_color));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    private void intiRecycleSwarIndex(){

        beSwarIndexItems = new ArrayList<>();
        adapterRecycleSwarIndex= new AdapterRecycleSwarIndex(getContext(),beSwarIndexItems);
        recycle_swar.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        recycle_swar.setHasFixedSize(true);
        recycle_swar.setAdapter(adapterRecycleSwarIndex);

        adapterRecycleSwarIndex.setOnClickListener(new AdapterRecycleSwarIndex.OnClickListener() {
            @Override
            public void OnSuraClickListener(int page_number,int anim_loading) {
                nPagesFragment pagesFragment = new nPagesFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("page_number", page_number);
                bundle.putInt("anim_loading", anim_loading);
                pagesFragment.setArguments(bundle);
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .replace(R.id.FrameLayout_main_activity, pagesFragment)
                        .addToBackStack("")
                        .commit();

            }
        });
    }



}


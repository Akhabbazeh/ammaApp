package com.zidnyscience.ammaApp.feature.pages_index;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zidnyscience.ammaApp.R;
import com.zidnyscience.ammaApp.feature.npages_feature.nPagesFragment;
import com.zidnyscience.model.BeSwarIndexItem;

import java.util.ArrayList;
import java.util.List;


public class PagesIndexFragment extends Fragment
{
    private View view;
    private RecyclerView recycle_index;
    private List<Integer> bePagesIndexItems;
    private AdapterRecyclePagesIndex adapterRecyclePagesIndex;


    public PagesIndexFragment()
    {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.pages_index_fragment, container, false);
        recycle_index = view.findViewById(R.id.recycle_index);



        initStatuesBar();
        intiRecyclePagesIndex();
        getData();
        return view;
    }


    private void intiRecyclePagesIndex(){

        bePagesIndexItems = new ArrayList<>();
        adapterRecyclePagesIndex= new AdapterRecyclePagesIndex(getContext(),bePagesIndexItems);
        recycle_index.setLayoutManager(new GridLayoutManager(getContext(),4));
        recycle_index.setAdapter(adapterRecyclePagesIndex);
        
        adapterRecyclePagesIndex.setOnClickListener(new AdapterRecyclePagesIndex.OnClickListener() {
            @Override
            public void OnSuraClickListener(int page_number,int anim_loading) {
                nPagesFragment pagesFragment = new nPagesFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("page_number", page_number);
                bundle.putInt("anim_loading", anim_loading);
                pagesFragment.setArguments(bundle);
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left,R.anim.enter_from_left,R.anim.exit_to_right)
                        .replace(R.id.FrameLayout_main_activity, pagesFragment)
                        .addToBackStack("")
                        .commit();
            }
        });


    }

    private void getData() {
        for (int i=1; i<=23; i++){
            bePagesIndexItems.add(i);
        }
        adapterRecyclePagesIndex.setPagesList(bePagesIndexItems);
    }
    
    private void initStatuesBar()
    {
        getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.background_color));


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }



}


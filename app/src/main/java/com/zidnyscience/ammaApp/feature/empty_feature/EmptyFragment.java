package com.zidnyscience.ammaApp.feature.empty_feature;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.fragment.app.Fragment;

import com.zidnyscience.ammaApp.R;


public class EmptyFragment extends Fragment
{
    private View view;


    public EmptyFragment()
    {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.empty_fragment, container, false);

        initStatuesBar();
        return view;
    }

    private void initStatuesBar()
    {
        getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.accent_color));


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }



}


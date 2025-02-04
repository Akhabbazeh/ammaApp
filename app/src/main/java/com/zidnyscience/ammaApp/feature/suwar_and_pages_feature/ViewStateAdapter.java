package com.zidnyscience.ammaApp.feature.suwar_and_pages_feature;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.zidnyscience.ammaApp.feature.empty_feature.EmptyFragment;

public class ViewStateAdapter extends FragmentStateAdapter {

    public ViewStateAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // Hardcoded in this order, you'll want to use lists and make sure the titles match
        if (position == 0) {
            return new EmptyFragment();
        }
        return new EmptyFragment();
    }

    @Override
    public int getItemCount() {
        // Hardcoded, use lists
        return 2;
    }
}
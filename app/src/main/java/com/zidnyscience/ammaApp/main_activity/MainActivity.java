package com.zidnyscience.ammaApp.main_activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.zidnyscience.ammaApp.R;
import com.zidnyscience.ammaApp.feature.empty_feature.EmptyFragment;
import com.zidnyscience.ammaApp.feature.start_feature.StartFragment;
import com.zidnyscience.ammaApp.feature.tests_feature.TestsFragment;

import io.paperdb.Paper;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Paper.init(this);


     setFragment(new StartFragment());
    }

    private void setFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.FrameLayout_main_activity, fragment)
                .commit(); }
}
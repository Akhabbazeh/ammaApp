package com.zidnyscience.ammaApp.feature.tests_feature;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import com.akexorcist.roundcornerprogressbar.TextRoundCornerProgressBar;
import com.duolingo.open.rtlviewpager.RtlViewPager;
import com.zidnyscience.ammaApp.R;
import com.zidnyscience.model.BeTest;
import com.zidnyscience.utils.Tools;

import java.util.ArrayList;
import java.util.List;


public class TestsFragment extends Fragment
{
    private View view;
    private ImageView img_amma_logo;
    private ImageView start_fragment_background;
    private TextRoundCornerProgressBar progressBarTextCustom;
    private LinearLayout progressBarTextCustomLayout;
    private int progress = 0;
    private Handler handler = new Handler();
    private RtlViewPager viewPager;
    private LinearLayout btn_more_details_about_question;
    private TextView txt_question_number;
    private ImageView btn_next_question;
    private ImageView btn_pre_question;
    private List<BeTest> testsList;
    private TestsPagerAdapter testsPagerAdapter;


    public TestsFragment()
    {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.tests_fragment, container, false);
        img_amma_logo = view.findViewById(R.id.img_amma_logo);
        start_fragment_background = view.findViewById(R.id.start_fragment_background);
        progressBarTextCustom = view.findViewById(R.id.progressBarTextCustom);
        progressBarTextCustomLayout = view.findViewById(R.id.progressBarTextCustomLayout);
        viewPager = view.findViewById(R.id.viewPager);
        btn_pre_question = view.findViewById(R.id.btn_pre_question);
        btn_next_question = view.findViewById(R.id.btn_next_question);
        btn_more_details_about_question = view.findViewById(R.id.btn_more_details_about_question);
        txt_question_number = view.findViewById(R.id.txt_question_number);


        initStatuesBar();
        initComponenets();
        initQuizComponents();
        startProgress();
        return view;
    }


    private void initQuizComponents() {

        testsList = new ArrayList<>();
        List<BeTest.Options> optionsList = new ArrayList<>();
        optionsList.add(new BeTest.Options(BeTest.Answer.A,"تثبيت العقيدة الإيمانية الإسلامية بأركان الإيمان كلها.", BeTest.TestStatus.UNSelected));
        optionsList.add(new BeTest.Options(BeTest.Answer.B,"بشارة للمتقين بالنعيم المقيم", BeTest.TestStatus.UNSelected));
        optionsList.add(new BeTest.Options(BeTest.Answer.C,"إنذار الكفار بالعذاب الأليم", BeTest.TestStatus.UNSelected));
        optionsList.add(new BeTest.Options(BeTest.Answer.D,"تفصيل أحكام الزواج والطلاق في الإسلام وأحكام المرأة", BeTest.TestStatus.UNSelected));

        testsList.add(new BeTest(0,"ما الموضوع العام الذي تؤكد عليه سور جزء عم ؟",optionsList, BeTest.Answer.A));
        testsList.add(new BeTest(0,"ما الموضوع العام الذي تؤكد عليه سور جزء عم ؟",optionsList, BeTest.Answer.A));
        testsList.add(new BeTest(0,"ما الموضوع العام الذي تؤكد عليه سور جزء عم ؟",optionsList, BeTest.Answer.A));
        testsList.add(new BeTest(0,"ما الموضوع العام الذي تؤكد عليه سور جزء عم ؟",optionsList, BeTest.Answer.A));


        testsPagerAdapter = new TestsPagerAdapter(getContext(),testsList);
        viewPager.setAdapter(testsPagerAdapter);


        btn_next_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_question_number.setText("");

            }
        });

        btn_pre_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_question_number.setText("");
            }
        });

    }

    private void initStatuesBar()
    {
        requireActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.accent_color));


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requireActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    private void initComponenets() {

        Tools.displayImageDrawable(getContext(),start_fragment_background,R.drawable.background_pattern);
        Tools.displayImageDrawable(getContext(),img_amma_logo,R.drawable.amma_logo);
        btn_more_details_about_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // fun
            }
        });

    }

    private void startProgress() {
        progress = 0;

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (progress <= 100) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            int remainingSeconds = (int) Math.ceil(60 - (progress * 0.6));
                            progressBarTextCustom.setProgress(progress);
                            if (progress>75){
                                progressBarTextCustom.setTextProgressColor(getContext().getResources().getColor(R.color.progress_error_text_color));
                                progressBarTextCustom.setProgressColor(getContext().getResources().getColor(R.color.progress_error_color));
                                progressBarTextCustomLayout.getBackground().setTint(getContext().getResources().getColor(R.color.progress_error_text_color));
                            }else {
                                progressBarTextCustom.setTextProgressColor(getContext().getResources().getColor(R.color.primary_color));
                                progressBarTextCustom.setProgressColor(getContext().getResources().getColor(R.color.progress_color));
                                progressBarTextCustomLayout.getBackground().setTint(getContext().getResources().getColor(R.color.primary_color));
                            }
                            progressBarTextCustom.setProgressText(remainingSeconds + " ث");
                        }
                    });

                    try {
                        Thread.sleep(600);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                    progress++;
                }
            }
        }).start();
    }



}


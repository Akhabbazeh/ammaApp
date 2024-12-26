package com.zidnyscience.ammaApp.feature.tests_feature;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import com.zidnyscience.ammaApp.R;
import com.zidnyscience.model.BeTest;
import java.util.List;

public class TestsPagerAdapter extends PagerAdapter {

    private Context context;
    private List<BeTest> beTestList;

    public TestsPagerAdapter(Context context, List<BeTest> beTestList) {
        this.context = context;
        this.beTestList = beTestList;
    }


    @Override
    public int getCount() {
        return beTestList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.test_item, container, false);
        TextView txt_question = view.findViewById(R.id.txt_question);
        RecyclerView recycle_ansewers = view.findViewById(R.id.recycle_ansewers);
        AnswersAdapter answersAdapter = new AnswersAdapter(beTestList.get(position).getOptions(),context);
        recycle_ansewers.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
        recycle_ansewers.setHasFixedSize(true);
        recycle_ansewers.setAdapter(answersAdapter);

        answersAdapter.setOnClickListener(new AnswersAdapter.OnClickListener() {
            @Override
            public void OnTestClickListener(List<BeTest.Options> optionsList, int i, int item_count) {

                for (int j=0; j<optionsList.size(); j++){
                    if (optionsList.get(j).getOption_char() == beTestList.get(position).getAnswer_char()){
                        optionsList.get(j).setTestStatus(BeTest.TestStatus.TURE);
                    }
                }

                if (optionsList.get(i).getOption_char() == beTestList.get(position).getAnswer_char()){
                    optionsList.get(i).setTestStatus(BeTest.TestStatus.TURE);
                }else {
                    optionsList.get(i).setTestStatus(BeTest.TestStatus.FALSE);
                }
                answersAdapter.setOptionsList(optionsList);
            }
        });


        txt_question.setText("س"+ (position + 1) + " - " + beTestList.get(position).getThe_question());



        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}

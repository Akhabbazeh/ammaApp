package com.zidnyscience.ammaApp.feature.tests_feature;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.zidnyscience.ammaApp.R;
import com.zidnyscience.model.BeTest;
import com.zidnyscience.utils.Tools;
import com.zidnyscience.model.BeTest.Options;

import java.util.List;

public class AnswersAdapter extends RecyclerView.Adapter<AnswersAdapter.QuestionViewHolder> {
    private List<Options> optionsList;
    private Context context;
    private OnClickListener onClickListener;
    public AnswersAdapter(List<Options> optionsList, Context context) {
        this.context = context;
        this.optionsList = optionsList;
    }

    public List<Options> getOptionsList() {
        return optionsList;
    }

    public void setOptionsList(List<Options> optionsList) {
        this.optionsList = optionsList;
        notifyDataSetChanged();
    }

    public interface OnClickListener
    {
        void OnTestClickListener(List<Options> optionsList, int position,int item_count);

    }

    public void setOnClickListener(OnClickListener onClickListener)
    {
        this.onClickListener = onClickListener;
    }


    @Override
    public QuestionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycle_answers, parent, false);
        return new QuestionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(QuestionViewHolder holder, int position) {
        holder.txt_question.setText(optionsList.get(position).getOption());

        if (optionsList.get(position).getOption_char() == BeTest.Answer.A) {
            holder.txt_option_char.setText("أ");
        }else if (optionsList.get(position).getOption_char() == BeTest.Answer.B){
            holder.txt_option_char.setText("ب");
        }else if (optionsList.get(position).getOption_char() == BeTest.Answer.C){
            holder.txt_option_char.setText("ج");
        }else if (optionsList.get(position).getOption_char() == BeTest.Answer.D){
            holder.txt_option_char.setText("د");
        }



        if (optionsList.get(position).getTestStatus() == BeTest.TestStatus.UNSelected){
             holder.answer_background_layout.setBackground(context.getResources().getDrawable(R.drawable.background_answer_un_selected));
             Tools.displayImageDrawable(context,holder.test_status_icon,R.drawable.answer_un_selected_icon);
        }else if (optionsList.get(position).getTestStatus() == BeTest.TestStatus.TURE){
            holder.answer_background_layout.setBackground(context.getResources().getDrawable(R.drawable.background_answer_ture));
            Tools.displayImageDrawable(context,holder.test_status_icon,R.drawable.answer_ture_icon);

        }else {
            holder.answer_background_layout.setBackground(context.getResources().getDrawable(R.drawable.background_answer_false));
            Tools.displayImageDrawable(context,holder.test_status_icon,R.drawable.answer_false_icon);

        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener != null){
                    onClickListener.OnTestClickListener(optionsList,position,optionsList.size());
                }
            }
        });


    }


    @Override
    public int getItemCount() {
        return optionsList.size();
    }

    public static class QuestionViewHolder extends RecyclerView.ViewHolder {
        public FrameLayout answer_background_layout;
        private TextView txt_question;
        private ImageView test_status_icon;
        private TextView txt_option_char;

        public QuestionViewHolder(View itemView) {
            super(itemView);
            txt_option_char = itemView.findViewById(R.id.txt_option_char);
            answer_background_layout = itemView.findViewById(R.id.answer_background_layout);
            txt_question = itemView.findViewById(R.id.txt_question);
            test_status_icon = itemView.findViewById(R.id.test_status_icon);
        }
    }
}


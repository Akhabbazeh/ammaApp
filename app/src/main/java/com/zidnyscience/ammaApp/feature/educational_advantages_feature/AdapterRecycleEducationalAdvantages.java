package com.zidnyscience.ammaApp.feature.educational_advantages_feature;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.zidnyscience.ammaApp.R;
import com.zidnyscience.model.EducationalAdvantages;
import java.util.List;

public class AdapterRecycleEducationalAdvantages extends RecyclerView.Adapter<AdapterRecycleEducationalAdvantages.ViewHolder>{

    private List<EducationalAdvantages> educationalAdvantagesList;
    private Context context;
    private OnClickListener onClickListener;
    private MediaPlayer mediaPlayer;
    public AdapterRecycleEducationalAdvantages(Context context, List<EducationalAdvantages> educationalAdvantagesList) {
        this.educationalAdvantagesList = educationalAdvantagesList;
        this.context = context;
    }


    public interface OnClickListener
    {
        void OnSuraClickListener(int page_number,int anim_loading);

    }

    public void setOnClickListener(OnClickListener onClickListener)
    {
        this.onClickListener = onClickListener;
    }


    public void setWordMeaningList(List<EducationalAdvantages> educationalAdvantagesList) {
        this.educationalAdvantagesList = educationalAdvantagesList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Initialize view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycle_educational_advantages, parent, false);
        // Pass holder view
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txt_question.setText(educationalAdvantagesList.get(position).getQuestion());
        holder.txt_word_answer.setText(educationalAdvantagesList.get(position).getAnswer());
    }


    @Override
    public int getItemCount() {
        return educationalAdvantagesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txt_word_answer;
        private TextView txt_question;
        public ViewHolder(View itemView) {
            super(itemView);
            txt_word_answer = itemView.findViewById(R.id.txt_word_answer);
            txt_question = itemView.findViewById(R.id.txt_question);
        }
    }



}

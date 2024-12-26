package com.zidnyscience.ammaApp.feature.word_meaning_feature;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.zidnyscience.ammaApp.R;
import com.zidnyscience.model.BeAnashidItem;
import com.zidnyscience.model.BeWordMeaningItem;
import com.zidnyscience.model.QuranWord;
import com.zidnyscience.model.WordMeaning;
import com.zidnyscience.utils.Tools;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class AdapterRecycleWordMeaning extends RecyclerView.Adapter<AdapterRecycleWordMeaning.ViewHolder>{

    private List<WordMeaning> wordMeaningList;
    private Context context;
    private OnClickListener onClickListener;
    private MediaPlayer mediaPlayer;
    public AdapterRecycleWordMeaning(Context context, List<WordMeaning> wordMeaningList) {
        this.wordMeaningList = wordMeaningList;
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


    public void setWordMeaningList(List<WordMeaning> wordMeaningList) {

        this.wordMeaningList = wordMeaningList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Initialize view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycle_word_meaning, parent, false);
        // Pass holder view
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txt_aya.setText(wordMeaningList.get(position).getAyaGlyph());
        holder.txt_word.setText(wordMeaningList.get(position).getWordGlyph());
        holder.txt_word_mean.setText(wordMeaningList.get(position).getMean());

        holder.btn_play_aya_sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleWordClick(wordMeaningList.get(position).getSurahNumber(),wordMeaningList.get(position).getAyaNumber());
            }
        });

        holder.btn_go_to_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener != null){
                    onClickListener.OnSuraClickListener((Integer.parseInt(wordMeaningList.get(position).getPageNumber()) - 582),0);

                }
            }
        });

    }

    private void handleWordClick(String sura_number,String ayah_number) {

        String aya = "s" + sura_number + "_" + (Integer.parseInt(ayah_number) < 10 ? "0" : "") + ayah_number;
        //  Toast.makeText(context,aya,Toast.LENGTH_LONG).show();
        int audioFile = Tools.getAudioFileByTitle(context, aya);

        if (audioFile != -1) {

            if (mediaPlayer != null) {
                mediaPlayer.release();
                mediaPlayer = null;
            }

            try {

                mediaPlayer = MediaPlayer.create(context, audioFile);
                mediaPlayer.setOnCompletionListener(mp -> {

                    mediaPlayer.release();
                    mediaPlayer = null;
                });
                mediaPlayer.start();
            } catch (Exception e) {
                Log.e("AudioUtils", "Error while playing audio: " + e.getMessage());
            }
        } else {

            Log.e("AudioUtils", "Audio file not found for: " + aya);
        }
    }

    public void stopAya(){
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    public int getItemCount() {
        return wordMeaningList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txt_aya;
        private TextView txt_word;
        private TextView txt_word_mean;
        private LinearLayout aya_layout;
        private ImageView btn_play_aya_sound;
        private ImageView btn_go_to_page;
        public ViewHolder(View itemView) {
            super(itemView);
            aya_layout = itemView.findViewById(R.id.aya_layout);
            txt_aya = itemView.findViewById(R.id.txt_aya);
            txt_word = itemView.findViewById(R.id.txt_word);
            txt_word_mean = itemView.findViewById(R.id.txt_word_mean);
            btn_play_aya_sound = itemView.findViewById(R.id.btn_play_aya_sound);
            btn_go_to_page = itemView.findViewById(R.id.btn_go_to_page);
        }
    }



}

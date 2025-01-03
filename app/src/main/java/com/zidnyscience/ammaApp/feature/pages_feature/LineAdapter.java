package com.zidnyscience.ammaApp.feature.pages_feature;

import android.content.Context;
import android.media.MediaPlayer;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zidnyscience.ammaApp.R;
import com.zidnyscience.model.QuranWord;
import com.zidnyscience.utils.JustifiedTextView;
import com.zidnyscience.utils.Tools;

import java.util.List;

public class LineAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_BSM = 1;
    private static final int TYPE_SURAH_WORD = 2;
    private List<List<QuranWord>> lines;
    private Context context;
    private MediaPlayer mediaPlayer;
    private OnAyaClickListener onAyaClickListener;

    public interface OnAyaClickListener {
        void OnAyaClickListener(int aya_number);
    }

    public void setOnAyaClickListener(OnAyaClickListener onAyaClickListener) {
        this.onAyaClickListener = onAyaClickListener;
    }


    public LineAdapter(List<List<QuranWord>> lines, Context context) {
        this.context = context;
        this.lines = lines;
    }

    @Override
    public int getItemViewType(int position) {
        if (lines.get(position).size() == 1 && lines.get(position).get(0).getGlyph_type_id().equals("6")) {
            return TYPE_HEADER;
        } else if (lines.get(position).size() == 1 && lines.get(position).get(0).getGlyph_type_id().equals("8")) {
            return TYPE_BSM;
        } else {
            return TYPE_SURAH_WORD;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == TYPE_HEADER) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_header, parent, false);
            return new HEADERViewHolder(view);
        } else if (viewType == TYPE_BSM) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bsm, parent, false);
            return new BSIMViewHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_line, parent, false);
            return new LineViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);

        if (viewType == TYPE_HEADER) {
            HEADERViewHolder headerViewHolder = (HEADERViewHolder) holder;
            Tools.displayImageDrawable(context,headerViewHolder.img_header_surah,Tools.getHeaderFromPageNumber(context,lines.get(position).get(0).getSura_number()));

        } else if (viewType == TYPE_BSM) {
            List<QuranWord> words = lines.get(position);
            BSIMViewHolder bsimViewHolder = (BSIMViewHolder) holder;
            if (words.get(0).getSura_number().equals("95") || words.get(0).getSura_number().equals("97")){
                Tools.displayImageDrawable(context,bsimViewHolder.img_bsm_surah,R.drawable.besm_allah_shadda);
            }else {
                Tools.displayImageDrawable(context,bsimViewHolder.img_bsm_surah,R.drawable.besm_allah);
            }


        }
        else {
            LineViewHolder lineViewHolder = (LineViewHolder) holder;
            List<QuranWord> words = lines.get(position);

            StringBuilder line = new StringBuilder();
            for (int i = words.size() - 1; i >= 0; i--) {
                line.append(words.get(i).getGlyph_code());
            }

            lineViewHolder.tv_justified_paragraph.setTypeface(
                    Tools.getFrontsFromPageNumber(context, words.get(0).getPage_number())
            );

            SpannableString spannableString = new SpannableString(line.toString());

            int currentIndex = 0;
            for (int i = words.size() - 1; i >= 0; i--) {
                QuranWord word = words.get(i);
                int start = currentIndex;
                int end = start + word.getGlyph_code().length();

                spannableString.setSpan(new ClickableSpan() {
                    @Override
                    public void onClick(@NonNull View widget) {
                        handleWordClick(word);
                    }

                    @Override
                    public void updateDrawState(@NonNull TextPaint ds) {
                        super.updateDrawState(ds);
                        ds.setUnderlineText(false);
                    }
                }, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                currentIndex = end;
            }

            lineViewHolder.tv_justified_paragraph.setText(spannableString);
            lineViewHolder.tv_justified_paragraph.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }

    private void handleWordClick(QuranWord word) {

        String aya = "s" + word.getSura_number() + "_" + (Integer.parseInt(word.getAyah_number()) < 10 ? "0" : "") + word.getAyah_number();
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


    @Override
    public int getItemCount() {
        return lines.size();
    }

    public static class LineViewHolder extends RecyclerView.ViewHolder {
        private JustifiedTextView tv_justified_paragraph;

        public LineViewHolder(View itemView) {
            super(itemView);
            tv_justified_paragraph = itemView.findViewById(R.id.tv_justified_paragraph);
        }
    }

    public static class BSIMViewHolder extends RecyclerView.ViewHolder {
        private ImageView img_bsm_surah;

        public BSIMViewHolder(View itemView) {
            super(itemView);
            img_bsm_surah = itemView.findViewById(R.id.img_bsm_surah);
        }
    }

    public static class HEADERViewHolder extends RecyclerView.ViewHolder {
        private ImageView img_header_surah;

        public HEADERViewHolder(View itemView) {
            super(itemView);
            img_header_surah = itemView.findViewById(R.id.img_header_surah);
        }
    }

    public void stopAya(){
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}

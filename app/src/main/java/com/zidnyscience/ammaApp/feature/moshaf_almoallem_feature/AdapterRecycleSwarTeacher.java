package com.zidnyscience.ammaApp.feature.moshaf_almoallem_feature;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.zidnyscience.ammaApp.R;
import com.zidnyscience.model.BeTeacherKoran;
import com.zidnyscience.utils.Tools;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class AdapterRecycleSwarTeacher extends RecyclerView.Adapter<AdapterRecycleSwarTeacher.ViewHolder>{

    private List<BeTeacherKoran> beSwarIndexItems;
    private Context context;
    private OnClickListener onClickListener;
    private boolean isMonshawi = false;
    public AdapterRecycleSwarTeacher(Context context, List<BeTeacherKoran> beSwarIndexItems) {
        this.beSwarIndexItems = beSwarIndexItems;
        this.context = context;
        this.isMonshawi = false;
    }

    public void setMonshawi(boolean monshawi) {
        isMonshawi = monshawi;
    }

    public interface OnClickListener
    {
        void OnSuraClickListener(String url,int makhtota_img);

    }

    public void setOnClickListener(OnClickListener onClickListener)
    {
        this.onClickListener = onClickListener;
    }


    public void setSwarList(List<BeTeacherKoran> beSwarIndexItems) {
        this.beSwarIndexItems = beSwarIndexItems;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Initialize view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycle_swar_teacher, parent, false);
        // Pass holder view
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


        Tools.displayImageDrawable(context,holder.makhtota_img,beSwarIndexItems.get(position).getMakhtota_img());
        holder.txt_sura_number.setText(holder.convertToArabicNumbers(beSwarIndexItems.get(position).getId())+"");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             if (onClickListener != null){
                 if (isMonshawi){
                     onClickListener.OnSuraClickListener(beSwarIndexItems.get(position).getAudio_url(),beSwarIndexItems.get(position).getMakhtota_img());

                 }else {
                     onClickListener.OnSuraClickListener(beSwarIndexItems.get(position).getAudio_hosary_url(),beSwarIndexItems.get(position).getMakhtota_img());

                 }
             }
            }
        });


    }



    @Override
    public int getItemCount() {
        return beSwarIndexItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
       private TextView txt_sura_number;
       private ImageView makhtota_img;
       private FrameLayout lottie_animation;
        public ViewHolder(View itemView) {
            super(itemView);
            txt_sura_number = itemView.findViewById(R.id.txt_sura_number);
            makhtota_img = itemView.findViewById(R.id.makhtota_img);
            lottie_animation = itemView.findViewById(R.id.lottie_animation);
        }

        private String convertToArabicNumbers(int number) {
            NumberFormat numberFormat = NumberFormat.getInstance(new Locale("ar"));
            return numberFormat.format(number);
        }
    }



}

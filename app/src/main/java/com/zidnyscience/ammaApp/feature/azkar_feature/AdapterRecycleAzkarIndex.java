package com.zidnyscience.ammaApp.feature.azkar_feature;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.zidnyscience.ammaApp.R;
import com.zidnyscience.model.BeAzkarIndexIteam;
import com.zidnyscience.model.BeTeacherKoran;
import com.zidnyscience.utils.Tools;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class AdapterRecycleAzkarIndex extends RecyclerView.Adapter<AdapterRecycleAzkarIndex.ViewHolder>{

    private List<BeAzkarIndexIteam> beAzkarIndexIteamList;
    private Context context;
    private OnClickListener onClickListener;
    public AdapterRecycleAzkarIndex(Context context, List<BeAzkarIndexIteam> beAzkarIndexIteamList) {
        this.beAzkarIndexIteamList = beAzkarIndexIteamList;
        this.context = context;
    }


    public interface OnClickListener
    {
        void OnSuraClickListener(BeAzkarIndexIteam beAzkarIndexIteam);

    }

    public void setOnClickListener(OnClickListener onClickListener)
    {
        this.onClickListener = onClickListener;
    }


    public void setAzkarIndexList(List<BeAzkarIndexIteam> beAzkarIndexIteamList) {
        this.beAzkarIndexIteamList = beAzkarIndexIteamList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Initialize view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycle_azkar_other, parent, false);
        // Pass holder view
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.txt_number.setText(holder.convertToArabicNumbers(beAzkarIndexIteamList.get(position).getId()));
        holder.txt_title.setText(beAzkarIndexIteamList.get(position).getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener != null){
                    onClickListener.OnSuraClickListener(beAzkarIndexIteamList.get(position));
                }
            }
        });
    }



    @Override
    public int getItemCount() {
        return beAzkarIndexIteamList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
       private TextView txt_title;
       private TextView txt_number;

        public ViewHolder(View itemView) {
            super(itemView);
            txt_title = itemView.findViewById(R.id.txt_title);
            txt_number = itemView.findViewById(R.id.txt_number);
        }

        private String convertToArabicNumbers(int number) {
            NumberFormat numberFormat = NumberFormat.getInstance(new Locale("ar"));
            return numberFormat.format(number);
        }
    }



}

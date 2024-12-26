package com.zidnyscience.ammaApp.feature.anashid_feature;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.zidnyscience.ammaApp.R;
import com.zidnyscience.model.BeAnashidItem;
import com.zidnyscience.model.BeSwarIndexItem;
import com.zidnyscience.utils.Tools;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class AdapterRecycleAnasid extends RecyclerView.Adapter<AdapterRecycleAnasid.ViewHolder>{

    private List<BeAnashidItem> anashidItemList;
    private Context context;
    private OnClickListener onClickListener;
    public AdapterRecycleAnasid(Context context, List<BeAnashidItem> anashidItemList) {
        this.anashidItemList = anashidItemList;
        this.context = context;
    }


    public interface OnClickListener
    {
        void OnSuraClickListener(int audio_path);

    }

    public void setOnClickListener(OnClickListener onClickListener)
    {
        this.onClickListener = onClickListener;
    }


    public void setAnashidItemList(List<BeAnashidItem> anashidItemList) {
        this.anashidItemList = anashidItemList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Initialize view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycle_anashid, parent, false);
        // Pass holder view
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.txt_number.setText(holder.convertToArabicNumbers(anashidItemList.get(position).getId()));
        holder.txt_title.setText(anashidItemList.get(position).getTitle());
        holder.txt_details_line_1.setText(anashidItemList.get(position).getMoshid_title());
        holder.txt_details_line_2.setText(anashidItemList.get(position).getMoshid_name());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener != null && position == 0){
                    onClickListener.OnSuraClickListener(0);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return anashidItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txt_number;
        private TextView txt_title;
        private TextView txt_details_line_1;
        private TextView txt_details_line_2;
        public ViewHolder(View itemView) {
            super(itemView);
            txt_number = itemView.findViewById(R.id.txt_number);
            txt_title = itemView.findViewById(R.id.txt_title);
            txt_details_line_1 = itemView.findViewById(R.id.txt_details_line_1);
            txt_details_line_2 = itemView.findViewById(R.id.txt_details_line_2);

        }

        private String convertToArabicNumbers(int number) {
            NumberFormat numberFormat = NumberFormat.getInstance(new Locale("ar"));
            return numberFormat.format(number);
        }
    }



}

package com.zidnyscience.ammaApp.feature.swar_feature;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.zidnyscience.ammaApp.R;
import com.zidnyscience.model.BeSwarIndexItem;
import com.zidnyscience.utils.Tools;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class AdapterRecycleSwarIndex extends RecyclerView.Adapter<AdapterRecycleSwarIndex.ViewHolder>{

    private List<BeSwarIndexItem> beSwarIndexItems;
    private Context context;
    private OnClickListener onClickListener;
    public AdapterRecycleSwarIndex(Context context, List<BeSwarIndexItem> beSwarIndexItems) {
        this.beSwarIndexItems = beSwarIndexItems;
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


    public void setSwarList(List<BeSwarIndexItem> beSwarIndexItems) {
        this.beSwarIndexItems = beSwarIndexItems;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Initialize view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycle_swar_index, parent, false);
        // Pass holder view
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


        Tools.displayImageDrawable(context,holder.makhtota_img,beSwarIndexItems.get(position).getMakhtota_img());
        holder.txt_sura_number.setText(holder.convertToArabicNumbers(beSwarIndexItems.get(position).getId())+"");
        holder.txt_details_line_1.setText("صفحة السورة: " + holder.convertToArabicNumbers(beSwarIndexItems.get(position).getPage_number())+" - " + " ترتيبها: " + holder.convertToArabicNumbers(beSwarIndexItems.get(position).getSura_numbers()));
        holder.txt_details_line_2.setText("عدد الآيات: " + holder.convertToArabicNumbers(beSwarIndexItems.get(position).getAya_numbers())+ " - "+ beSwarIndexItems.get(position).getType());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             if (onClickListener != null){
                 onClickListener.OnSuraClickListener((beSwarIndexItems.get(position).getPage_number() - 582),beSwarIndexItems.get(position).getMakhtota_img());
             }
            }
        });

    }

    @Override
    public int getItemCount() {
        return beSwarIndexItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

       private FrameLayout decoration_icon;
       private TextView txt_sura_number;
       private ImageView makhtota_img;
       private TextView txt_details_line_1;
        private TextView txt_details_line_2;
        public ViewHolder(View itemView) {
            super(itemView);
            decoration_icon = itemView.findViewById(R.id.decoration_icon);
            txt_sura_number = itemView.findViewById(R.id.txt_sura_number);
            makhtota_img = itemView.findViewById(R.id.makhtota_img);
            txt_details_line_1 = itemView.findViewById(R.id.txt_details_line_1);
            txt_details_line_2 = itemView.findViewById(R.id.txt_details_line_2);


        }

        private String convertToArabicNumbers(int number) {
            NumberFormat numberFormat = NumberFormat.getInstance(new Locale("ar"));
            return numberFormat.format(number);
        }
    }



}

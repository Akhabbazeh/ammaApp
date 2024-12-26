package com.zidnyscience.ammaApp.feature.choose_test_feature;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.zidnyscience.ammaApp.R;
import com.zidnyscience.model.BeTestItem;
import com.zidnyscience.utils.Tools;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class AdapterRecycleChooseTest extends RecyclerView.Adapter<AdapterRecycleChooseTest.ViewHolder>{

    private List<BeTestItem> beTestItemList;
    private boolean isAdded;
    private Context context;
    private OnClickListener onClickListener;
    private NoItemAddedListener noItemAddedListener;
    public AdapterRecycleChooseTest(Context context, boolean isAdded, List<BeTestItem> beTestItemList) {
        this.beTestItemList = beTestItemList;
        this.context = context;
        this.isAdded = isAdded;
    }


    public interface OnClickListener
    {
        void OnSuraClickListener(BeTestItem beTestItem);

    }

    public void setOnClickListener(OnClickListener onClickListener)
    {
        this.onClickListener = onClickListener;
    }

    public void setNoItemAddedListener(NoItemAddedListener noItemAddedListener)
    {
        this.noItemAddedListener = noItemAddedListener;
    }

    public interface NoItemAddedListener
    {
        void OnNoItemAddedListener();

    }

    public void setTestsList(List<BeTestItem> beTestItemList) {
        this.beTestItemList = beTestItemList;
        notifyDataSetChanged();
    }

    public List<BeTestItem> getBeTestItemList() {
        return beTestItemList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Initialize view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycle_tests, parent, false);
        // Pass holder view
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (isAdded){
            holder.decoration_icon.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.background_color)));
            holder.view.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.background_color)));
            holder.btn_plus.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.background_color)));
            holder.makhtota_img.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.background_color)));
            holder.txt_sura_number.setTextColor(context.getResources().getColor(R.color.background_color));
            Tools.displayImageDrawable(context,holder.btn_plus,R.drawable.remove_icon);

        }
        Tools.displayImageDrawable(context,holder.makhtota_img,beTestItemList.get(position).getMakhtota_img());
        holder.txt_sura_number.setText(holder.convertToArabicNumbers(beTestItemList.get(position).getId())+"");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isAdded){
                    if (onClickListener != null){
                        onClickListener.OnSuraClickListener(beTestItemList.get(position));
                    }
                }else {
                    beTestItemList.remove(position);
                    notifyDataSetChanged();

                    if (beTestItemList.isEmpty()){
                        if (noItemAddedListener != null){
                            noItemAddedListener.OnNoItemAddedListener();
                        }
                    }
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return beTestItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

       private FrameLayout decoration_icon;
       private TextView txt_sura_number;
       private ImageView makhtota_img;
       private ImageView btn_plus;
       private View view;
        public ViewHolder(View itemView) {
            super(itemView);
            decoration_icon = itemView.findViewById(R.id.decoration_icon);
            txt_sura_number = itemView.findViewById(R.id.txt_sura_number);
            makhtota_img = itemView.findViewById(R.id.makhtota_img);
            btn_plus = itemView.findViewById(R.id.btn_plus);
            view = itemView.findViewById(R.id.view);


        }

        private String convertToArabicNumbers(int number) {
            NumberFormat numberFormat = NumberFormat.getInstance(new Locale("ar"));
            return numberFormat.format(number);
        }
    }



}

package com.zidnyscience.ammaApp.feature.pages_index;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.zidnyscience.ammaApp.R;
import com.zidnyscience.model.BeSwarIndexItem;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class AdapterRecyclePagesIndex extends RecyclerView.Adapter<AdapterRecyclePagesIndex.ViewHolder>{

    private List<Integer> bePagesIndexItems;
    private Context context;
    private OnClickListener onClickListener;
    public AdapterRecyclePagesIndex(Context context, List<Integer> bePagesIndexItems) {
        this.bePagesIndexItems = bePagesIndexItems;
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


    public void setPagesList(List<Integer> bePagesIndexItems) {
        this.bePagesIndexItems = bePagesIndexItems;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Initialize view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycle_pages_index, parent, false);
        // Pass holder view
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


    holder.txt_page_number.setText(holder.convertToArabicNumbers(bePagesIndexItems.get(position)));

    holder.card_view_layout.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (onClickListener != null){
                onClickListener.OnSuraClickListener((bePagesIndexItems.get(position)-1),R.drawable.amma_logo);
            }
        }
    });

    }

    @Override
    public int getItemCount() {
        return bePagesIndexItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txt_page_number;
        private CardView card_view_layout;
        public ViewHolder(View itemView) {
            super(itemView);
            card_view_layout = itemView.findViewById(R.id.card_view_layout);
            txt_page_number = itemView.findViewById(R.id.txt_page_number);
        }

        private String convertToArabicNumbers(int number) {
            NumberFormat numberFormat = NumberFormat.getInstance(new Locale("ar"));
            return numberFormat.format(number);
        }
    }



}

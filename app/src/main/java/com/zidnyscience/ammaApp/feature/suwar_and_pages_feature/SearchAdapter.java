package com.zidnyscience.ammaApp.feature.suwar_and_pages_feature;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zidnyscience.ammaApp.R;
import com.zidnyscience.ammaApp.feature.anashid_feature.AdapterRecycleAnasid;
import com.zidnyscience.model.BeSearchAyaItem;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends ArrayAdapter<BeSearchAyaItem> {

    private List<BeSearchAyaItem> originalItems;
    private List<BeSearchAyaItem> filteredItems;
    private Filter searchFilter;
    private OnClickListener onClickListener;


    public SearchAdapter(Context context, List<BeSearchAyaItem> items) {
        super(context, 0, items);
        this.originalItems = new ArrayList<>(items);
        this.filteredItems = new ArrayList<>(items);
    }

    public interface OnClickListener
    {
        void OnSuraClickListener(int page_number,int anim_loading);

    }

    public void setOnClickListener(OnClickListener onClickListener)
    {
        this.onClickListener = onClickListener;
    }

    @Override
    public int getCount() {
        return filteredItems.size();
    }

    @Override
    public BeSearchAyaItem getItem(int position) {
        return filteredItems.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BeSearchAyaItem currentItem = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_search_aya, parent, false);
        }

        TextView txt_aya = convertView.findViewById(R.id.txt_aya);
        TextView txt_aya_details = convertView.findViewById(R.id.txt_aya_details);

        if (currentItem != null) {
            txt_aya.setText(currentItem.getAyahTextArabic());
            txt_aya_details.setText(currentItem.getSurahNameArabic() +": "+ currentItem.getAyahNumberInSurah() + " " + "(صفحة" + currentItem.getSurahNumber() + ")");
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onClickListener != null){
                        onClickListener.OnSuraClickListener(Integer.parseInt(currentItem.getPage()) - 582,0);
                    }
                }
            });
        }

        return convertView;
    }

    @Override
    public Filter getFilter() {
        if (searchFilter == null) {
            searchFilter = new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence constraint) {
                    FilterResults results = new FilterResults();
                    if (constraint == null || constraint.length() == 0) {

                        results.values = originalItems;
                        results.count = originalItems.size();
                    } else {

                        List<BeSearchAyaItem> filteredList = new ArrayList<>();
                        String filterPattern = constraint.toString().toLowerCase().trim();

                        for (BeSearchAyaItem item : originalItems) {

                            if (item.getAyahTextEmlaey().toLowerCase().contains(filterPattern) ||
                                    item.getSurahNameArabic().toLowerCase().contains(filterPattern)) {
                                filteredList.add(item);
                            }
                        }

                        results.values = filteredList;
                        results.count = filteredList.size();
                    }
                    return results;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {
                    filteredItems = (List<BeSearchAyaItem>) results.values;
                    notifyDataSetChanged();
                }
            };
        }
        return searchFilter;
    }
}

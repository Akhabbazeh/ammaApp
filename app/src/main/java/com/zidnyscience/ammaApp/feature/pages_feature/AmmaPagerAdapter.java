package com.zidnyscience.ammaApp.feature.pages_feature;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import com.zidnyscience.ammaApp.R;
import com.zidnyscience.model.QuranWord;
import com.zidnyscience.utils.Tools;

import java.util.List;

public class AmmaPagerAdapter extends PagerAdapter {

    private Context context;
    private List<List<List<QuranWord>>> ammaPages;

    public AmmaPagerAdapter(Context context, List<List<List<QuranWord>>> ammaPages) {
        this.context = context;
        this.ammaPages = ammaPages;
    }

    public void setAmmaPages(List<List<List<QuranWord>>> ammaPages) {
        this.ammaPages = ammaPages;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return ammaPages.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.page_item, container, false);

        RecyclerView recycle_lines = view.findViewById(R.id.recycle_lines);
        ImageView img_background = view.findViewById(R.id.img_background);

        if (position % 2 == 0){
            Tools.displayImageDrawable(context,img_background,R.drawable.page_left_img);
        }else {
            Tools.displayImageDrawable(context,img_background,R.drawable.page_right_background);
        }

        LineAdapter lineAdapter = new LineAdapter(ammaPages.get(position),context);
        recycle_lines.setLayoutManager(new LinearLayoutManager(context));
        recycle_lines.setNestedScrollingEnabled(false);
        recycle_lines.setAdapter(lineAdapter);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}

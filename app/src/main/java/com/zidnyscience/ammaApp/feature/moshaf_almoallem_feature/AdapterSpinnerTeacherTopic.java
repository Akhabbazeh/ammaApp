package com.zidnyscience.ammaApp.feature.moshaf_almoallem_feature;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zidnyscience.ammaApp.R;

import java.util.List;

public class AdapterSpinnerTeacherTopic extends BaseAdapter
{
    private Context context;
    private List<String> teacherList;

    public AdapterSpinnerTeacherTopic(Context context, List<String> teacherList)
    {
        this.context = context;
        this.teacherList = teacherList;
    }

    @Override
    public int getCount()
    {
        return teacherList.size();
    }

    @Override
    public Object getItem(int position)
    {
        return teacherList.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.item_spinner_teacher_close, null);
        TextView txtSpinnerCategoriesClose = view.findViewById(R.id.txtSpinnerCategoriesClose);
        txtSpinnerCategoriesClose.setText(teacherList.get(position));
        return view;
    }
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.item_spinner_teacher_open, null);
        TextView txtSpinnerCategoriesOpen = view.findViewById(R.id.txtSpinnerCategoriesOpen);
        txtSpinnerCategoriesOpen.setText(teacherList.get(position));
        return view;
    }
    public void setTeacherList(List<String> teacherList)
    {
        this.teacherList = teacherList;
        notifyDataSetChanged();
    }
}

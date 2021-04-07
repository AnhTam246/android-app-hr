package com.example.hr.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hr.R;
import com.example.hr.model.SpecialDate;

import java.text.SimpleDateFormat;
import java.util.List;

public class SpecialDateAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<SpecialDate> listSpecialDate;

    public SpecialDateAdapter(Context context, int layout, List<SpecialDate> listSpecialDate) {
        this.context = context;
        this.layout = layout;
        this.listSpecialDate = listSpecialDate;
    }

    @Override
    public int getCount() {
        return listSpecialDate.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder {
        TextView tvFromDateSpecialDate, tvToDateSpecialDate, tvNoteSpecialDate;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SpecialDateAdapter.ViewHolder holder;
        if(convertView == null) {
            holder = new SpecialDateAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            holder.tvFromDateSpecialDate = (TextView) convertView.findViewById(R.id.tvFromDateSpecialDate);
            holder.tvToDateSpecialDate = (TextView) convertView.findViewById(R.id.tvToDateSpecialDate);
            holder.tvNoteSpecialDate = (TextView) convertView.findViewById(R.id.tvNoteSpecialDate);
            convertView.setTag(holder);
        } else {
            holder = (SpecialDateAdapter.ViewHolder) convertView.getTag();
        }

        SpecialDate specialDate = listSpecialDate.get(position);

        holder.tvFromDateSpecialDate.setText(specialDate.getDay_special_from());
        holder.tvToDateSpecialDate.setText(specialDate.getDay_special_to());
        holder.tvNoteSpecialDate.setText(specialDate.getNote());

        return convertView;
    }
}

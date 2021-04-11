    package com.example.hr.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hr.R;
import com.example.hr.model.CheckInOut;

import java.text.SimpleDateFormat;
import java.util.List;

public class CheckInAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<CheckInOut> listCheckIn;

    public CheckInAdapter(Context context, int layout, List<CheckInOut> listCheckIn) {
        this.context = context;
        this.layout = layout;
        this.listCheckIn = listCheckIn;
    }

    @Override
    public int getCount() {
        return listCheckIn.size();
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
         TextView tvDate, tvCheckIn, tvCheckOut;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            holder.tvDate = (TextView) convertView.findViewById(R.id.tvDate);
            holder.tvCheckIn = (TextView) convertView.findViewById(R.id.tvCheckIn);
            holder.tvCheckOut = (TextView) convertView.findViewById(R.id.tvCheckOut);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        CheckInOut checkInOut = listCheckIn.get(position);
        String check_in = new SimpleDateFormat("HH:mm:ss").format(checkInOut.check_in);
        if(check_in.equals("00:00:00")) {
            check_in = "";
        }
        String check_out = new SimpleDateFormat("HH:mm:ss").format(checkInOut.check_out);
        if(check_out.equals("00:00:00")) {
            check_out = "";
        }
        holder.tvDate.setText(new SimpleDateFormat("dd-MM-yyyy").format(checkInOut.check_in_day));
        holder.tvCheckIn.setText(check_in);
        holder.tvCheckOut.setText(check_out);

        return convertView;
    }
}

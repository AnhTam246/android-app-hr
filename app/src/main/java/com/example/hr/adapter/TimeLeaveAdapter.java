package com.example.hr.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hr.R;
import com.example.hr.model.TimeLeave;

import java.text.SimpleDateFormat;
import java.util.List;

public class TimeLeaveAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<TimeLeave> listTimeLeave;

    public TimeLeaveAdapter(Context context, int layout, List<TimeLeave> listTimeLeave) {
        this.context = context;
        this.layout = layout;
        this.listTimeLeave = listTimeLeave;
    }

    @Override
    public int getCount() {
        return listTimeLeave.size();
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
        TextView tvDateTimeLeave, tvNumberTimeLeave, tvTypeTimeLeave, tvApproveTimeLeave;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TimeLeaveAdapter.ViewHolder holder;
        if(convertView == null) {
            holder = new TimeLeaveAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            holder.tvDateTimeLeave = (TextView) convertView.findViewById(R.id.tvDateTimeLeave);
            holder.tvNumberTimeLeave = (TextView) convertView.findViewById(R.id.tvNumberTimeLeave);
            holder.tvTypeTimeLeave = (TextView) convertView.findViewById(R.id.tvTypeTimeLeave);
            holder.tvApproveTimeLeave = (TextView) convertView.findViewById(R.id.tvApproveTimeLeave);
            convertView.setTag(holder);
        } else {
            holder = (TimeLeaveAdapter.ViewHolder) convertView.getTag();
        }

        TimeLeave time_leave = listTimeLeave.get(position);
        String number_time = "";
        if(time_leave.time.equals("08:00:00")) {
            number_time = "1";
        } else if(time_leave.time.equals("04:00:00")) {
            number_time = "0.5";
        }

        String type = "";
        switch (time_leave.type) {
            case 0:
                type = "Bổ sung công";
                break;
            case 1:
                type = "Phép năm tính lương";
                break;
            case 2:
                type = "Phép nghỉ không lương";
                break;
            case 3:
                type = "Phép ốm đau ngắn ngày";
                break;
            case 4:
                type = "Phép nghỉ ốm dài ngày";
                break;
            case 5:
                type = "Phép thai sản";
                break;
            case 6:
                type = "Phép nghỉ kết hôn";
                break;
            case 7:
                type = "Phép nghỉ ma chay";
                break;
            default:
                break;
        }

        String approve = "Chưa phê duyệt";
        if(time_leave.is_approved == 1) {
            approve = "Giám đốc đã duyệt";
        } else if(time_leave.is_approved == 2) {
            approve = "Quản lý đã duyệt";
        }

        holder.tvDateTimeLeave.setText(time_leave.day_time_leave);
        holder.tvNumberTimeLeave.setText(number_time);
        holder.tvTypeTimeLeave.setText(type);
        holder.tvApproveTimeLeave.setText(approve);

        return convertView;
    }
}

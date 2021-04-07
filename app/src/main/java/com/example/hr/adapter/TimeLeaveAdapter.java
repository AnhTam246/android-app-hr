package com.example.hr.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hr.R;
import com.example.hr.model.TimeLeave;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
                String day_from = time_leave.day_time_leave.substring(0, 10);
                String day_to = time_leave.day_time_leave.substring(15);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    Date first_date = sdf.parse(day_from);
                    Date second_date = sdf.parse(day_to);

                    long difference  = Math.abs(second_date.getTime() - first_date.getTime());
                    long differenceDates = difference / (24 * 60 * 60 * 1000) + 1;
                    number_time = Long.toString(differenceDates);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                break;
            case 7:
                type = "Phép nghỉ ma chay";
                String day_from_type_7 = time_leave.day_time_leave.substring(0, 10);
                String day_to_type_7 = time_leave.day_time_leave.substring(15);
                SimpleDateFormat sdf_type_7 = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    Date first_date = sdf_type_7.parse(day_from_type_7);
                    Date second_date = sdf_type_7.parse(day_to_type_7);

                    long difference_type_7  = Math.abs(second_date.getTime() - first_date.getTime());
                    long differenceDates_type_7 = difference_type_7 / (24 * 60 * 60 * 1000) + 1;
                    number_time = Long.toString(differenceDates_type_7);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

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

    public static long getDateDiff(SimpleDateFormat format, String oldDate, String newDate) {
        try {
            return TimeUnit.DAYS.convert(format.parse(newDate).getTime() - format.parse(oldDate).getTime(), TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}

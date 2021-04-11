package com.example.hr.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hr.R;
import com.example.hr.model.CheckInOut;
import com.example.hr.model.Contract;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class ListContractAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Contract> listContract;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    DecimalFormat decimalFormat = new DecimalFormat("#,##");

    public ListContractAdapter(Context context, int layout, List<Contract> listContract) {
        this.context = context;
        this.layout = layout;
        this.listContract = listContract;
    }

    @Override
    public int getCount() {
        return listContract.size();
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
        TextView tvFromDate, tvToDate, tvEndSoon, tvSalary;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);

            holder.tvFromDate = convertView.findViewById(R.id.tvFromDate);
            holder.tvToDate = convertView.findViewById(R.id.tvToDate);
            holder.tvEndSoon = convertView.findViewById(R.id.tvEndSoon);
            holder.tvSalary = convertView.findViewById(R.id.tvSalary);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Contract contract = listContract.get(position);
        String startDate = simpleDateFormat.format(contract.getStartDate());
        String endDate = simpleDateFormat.format(contract.getEndDate());
        String stopDate = simpleDateFormat.format(contract.getStopDate());
        String salary = decimalFormat.format(contract.getBaseSalary());

        holder.tvFromDate.setText(startDate);
        holder.tvToDate.setText(endDate);
        holder.tvEndSoon.setText(stopDate);
        holder.tvSalary.setText(salary);

        return convertView;
    }
}

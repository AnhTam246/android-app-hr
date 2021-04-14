package com.example.hr.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hr.R;
import com.example.hr.model.Data;
import com.example.hr.model.SalaryDetail;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class ListSalaryDetailAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private Data data;
    private List<SalaryDetail> listSalaryDetail;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    DecimalFormat decimalFormat = new DecimalFormat("#,###");

    public ListSalaryDetailAdapter(Context context, int layout, List<SalaryDetail> listSalaryDetail) {
        this.context = context;
        this.layout = layout;
        this.listSalaryDetail = listSalaryDetail;
    }


    public ListSalaryDetailAdapter(List<SalaryDetail> listSalaryDetail) {
        this.listSalaryDetail = listSalaryDetail;
    }

    @Override
    public int getCount() {
        return listSalaryDetail.size();
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
        TextView tvFromDate, tvEndDate, tvBaseSalary, tvTotalAllowance, tvTotalInsurance, tvSalaryActuallyReceived;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);

            holder.tvFromDate = convertView.findViewById(R.id.tvFromDate);
            holder.tvEndDate = convertView.findViewById(R.id.tvEndDate);
            holder.tvBaseSalary = convertView.findViewById(R.id.tvBaseSalary);
            holder.tvTotalAllowance = convertView.findViewById(R.id.tvTotalAllowance);
            holder.tvTotalInsurance = convertView.findViewById(R.id.tvTotalInsurance);
            holder.tvSalaryActuallyReceived = convertView.findViewById(R.id.tvSalaryActuallyReceive);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        SalaryDetail detail = listSalaryDetail.get(position);
        String fromDate = simpleDateFormat.format(detail.getSalaryDetail().getFromDate());
        String endDate = simpleDateFormat.format(detail.getSalaryDetail().getToDate());
        String baseSalary = decimalFormat.format(detail.getBaseSalaryContract());
        String totalAllowance = decimalFormat.format(detail.getTotalAllowance());
        String totalInsurance = decimalFormat.format(detail.getTotalInsurance());
        String salaryActuallyReceive = decimalFormat.format(detail.getSalaryActuallyReceived());

        holder.tvFromDate.setText(fromDate);
        holder.tvEndDate.setText(endDate);
        holder.tvBaseSalary.setText(baseSalary);
        holder.tvTotalAllowance.setText(totalAllowance);
        holder.tvTotalInsurance.setText(totalInsurance);
        holder.tvSalaryActuallyReceived.setText(salaryActuallyReceive);

        return convertView;
    }
}

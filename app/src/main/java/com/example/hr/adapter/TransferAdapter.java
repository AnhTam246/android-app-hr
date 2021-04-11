package com.example.hr.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.hr.R;
import com.example.hr.model.Transfer;
import java.util.List;


public class TransferAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Transfer> listTransfers;

    public TransferAdapter(Context context, int layout, List<Transfer> listTransfers) {
        this.context = context;
        this.layout = layout;
        this.listTransfers = listTransfers;
    }

    @Override
    public int getCount() {
        return listTransfers.size();
    }

    @Override
    public Object getItem(int position) {
       return null;
    }

    @Override
    public long getItemId(int position) {return 0;}

    private class ViewHolder {
        TextView txtStaffID, txtOld, txtNew;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TransferAdapter.ViewHolder holder;
        if(convertView == null) {
            holder = new TransferAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            holder.txtStaffID = (TextView) convertView.findViewById(R.id.tvStaffID);
            holder.txtOld = (TextView) convertView.findViewById(R.id.tvOld);
            holder.txtNew = (TextView) convertView.findViewById(R.id.tvNew);
            convertView.setTag(holder);
        } else {
            holder = (TransferAdapter.ViewHolder) convertView.getTag();
        }

        Transfer transfer = listTransfers.get(position);
        holder.txtStaffID.setText(transfer.getStaff_id().toString());
        holder.txtOld.setText(transfer.getOld_department().toString());
        holder.txtNew.setText(transfer.getNew_department().toString());

        return convertView;
    }
}

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
        TextView txtStaffID, txtOld, txtNew,txtOlApp,txtNewApp,txtMaAPP;
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
            holder.txtOlApp = (TextView) convertView.findViewById(R.id.tvOldApp);
            holder.txtNewApp = (TextView) convertView.findViewById(R.id.tvnewApp);
            holder.txtMaAPP = (TextView) convertView.findViewById(R.id.tvManager);

            convertView.setTag(holder);
        } else {
            holder = (TransferAdapter.ViewHolder) convertView.getTag();
        }

        Transfer transfer = listTransfers.get(position);
        holder.txtStaffID.setText(transfer.getStaff_transfer());
        holder.txtOld.setText(transfer.getOld_department_name().toString());
        holder.txtNew.setText(transfer.getNew_department_name());

        String approve = "Chưa duyệt";
        if(transfer.isOld_manager_approved() == true) {
            approve = "duyệt";
        } else if(transfer.isOld_manager_approved() == false) {
            approve = "chưa duyệt";
        }

        String approve1 = "Chưa duyệt";
        if(transfer.isNew_manager_approved() == true) {
            approve1 = "duyệt";
        } else if(transfer.isNew_manager_approved() == false) {
            approve1 = "chưa duyệt";
        }

        String approve2 = "Chưa duyệt";
        if(transfer.isManager_approved() == true) {
            approve2 = "duyệt";
        } else if(transfer.isManager_approved() == false) {
            approve2 = "chưa duyệt";
        }

        holder.txtOlApp.setText(approve);
        holder.txtNewApp.setText(approve1);
        holder.txtMaAPP.setText(approve2);

        return convertView;
    }
}

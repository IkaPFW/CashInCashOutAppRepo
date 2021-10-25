package com.example.cashincashoutapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.HolderItem> {
    private List<Cashflow> cashList;
    private Context context;
    LayoutInflater inflate;

    public ListAdapter(List<Cashflow> list, Context cont){
        this.cashList = list;
        this.context = cont;
        this.inflate = LayoutInflater.from(this.context);
    }

    @NonNull
    @Override
    public ListAdapter.HolderItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View v = inflate.inflate(R.layout.activity_detail, parent, false);
        return new HolderItem(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.HolderItem holder, int pos){
        Cashflow dh = cashList.get(pos);

        holder.date.setText(dh.GetDate());
        holder.desc.setText(dh.GetDesc());
        holder.nominal.setText(dh.GetNominal());
    }

    @Override
    public int getItemCount(){
        return (cashList != null) ? cashList.size() : 0;
    }

    public class HolderItem extends RecyclerView.ViewHolder{
        public TextView date, desc, nominal;
        public ImageView cashImg;

        public HolderItem(@NonNull View itemView){
            super(itemView);

            date = itemView.findViewById(R.id.detailIncomeItemFooter);
            desc = itemView.findViewById(R.id.detailIncomeItem);
            nominal = itemView.findViewById(R.id.detailIncomeItemHeader);
            cashImg = itemView.findViewById(R.id.arrowIncome);
        }
    }
}

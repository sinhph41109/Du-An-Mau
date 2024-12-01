package com.example.duanmau.Adapter;

import android.app.Activity;
import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau.Model.Sach;
import com.example.duanmau.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Top10Adapter extends RecyclerView.Adapter<Top10Adapter.ViewHolder>{

    private Context context;
    private ArrayList<Sach> list;

    public Top10Adapter(Context context, ArrayList<Sach> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_recycler_top10, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtMasach.setText(String.valueOf(list.get(position).getMasach()));
        holder.txtTenSach.setText(list.get(position).getTensach());
        holder.txtSoLuong.setText(String.valueOf(list.get(position).getSoluongdamuon()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtMasach, txtTenSach, txtSoLuong
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMasach = itemView.findViewById(R.id.txtMasach);
            txtTenSach = itemView.findViewById(R.id.txtTensach);
            txtSoLuong = itemView.findViewById(R.id.txtSoLuong);
        }
    }
}

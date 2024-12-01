package com.example.duanmau.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau.Adapter.LoaiSachAdapter;
import com.example.duanmau.Dao.LoaiSachDAO;
import com.example.duanmau.Model.LoaiSach;
import com.example.duanmau.R;

import java.util.ArrayList;

public class QLLoaiSachFragment extends Fragment {
    RecyclerView recyclerLoaiSach;
    LoaiSachDAO dao;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_qlloaisach, container, false);

        RecyclerView recyclerLoaiSach = view.findViewById(R.id.rclLoaisach);
        EditText edtLoaiSach = view.findViewById(R.id.edtLoaiSach);
        Button btnThem = view.findViewById(R.id.btThem);

        LoaiSachDAO dao = new LoaiSachDAO(getContext());


        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenloai = edtLoaiSach.getText().toString();

                if (dao.themLoaiSach(tenloai)){
                    loadData();
                    edtLoaiSach.setText("");
                }else{
                    Toast.makeText(getContext(), "khong thanh cong", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
    private void loadData(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerLoaiSach.setLayoutManager(linearLayoutManager);

        ArrayList<LoaiSach> list = dao.getDSLoaiSach();
        LoaiSachAdapter adapter = new LoaiSachAdapter(getContext(), list);
        recyclerLoaiSach.setAdapter(adapter);
    }
}

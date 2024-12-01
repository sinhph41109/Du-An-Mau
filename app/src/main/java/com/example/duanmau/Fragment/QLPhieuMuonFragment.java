package com.example.duanmau.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau.Adapter.PhieuMuonAdapter;
import com.example.duanmau.Dao.PhieuMuonDAO;
import com.example.duanmau.Dao.SachDAO;
import com.example.duanmau.Dao.ThanhVienDAO;
import com.example.duanmau.Model.PhieuMuon;
import com.example.duanmau.Model.Sach;
import com.example.duanmau.Model.ThanhVien;
import com.example.duanmau.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class QLPhieuMuonFragment extends Fragment {
    PhieuMuonDAO phieuMuonDAO;
    ArrayList<PhieuMuon> list;
    RecyclerView rclQLPhieuMuon;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_qlphieumuon, container, false);
        RecyclerView rclQLPhieuMuon = view.findViewById(R.id.rclQLPhieuMuon);
        FloatingActionButton floatAdd = view.findViewById(R.id.floatAdd);

        PhieuMuonDAO phieuMuonDAO = new PhieuMuonDAO(getContext());
        list = phieuMuonDAO.getDSPhieuMuon();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rclQLPhieuMuon.setLayoutManager(linearLayoutManager);
        PhieuMuonAdapter adapter = new PhieuMuonAdapter(list, getContext());
        rclQLPhieuMuon.setAdapter(adapter);

        loadData();

        floatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

        return view;
    }
    private void loadData(){
        phieuMuonDAO = new PhieuMuonDAO(getContext());
        list = phieuMuonDAO.getDSPhieuMuon();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rclQLPhieuMuon.setLayoutManager(linearLayoutManager);
        PhieuMuonAdapter adapter = new PhieuMuonAdapter(list, getContext());
        rclQLPhieuMuon.setAdapter(adapter);

    }
    private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_them, null);
        Spinner spnThanhVien = view.findViewById(R.id.spnTV);
        Spinner spnSach = view.findViewById(R.id.spnSach);
        EditText edtTien = view.findViewById(R.id.edtTien);
        getDataThanhVien(spnThanhVien);
        getDataSach(spnSach);
        builder.setView(view);

        builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                HashMap<String, Object> hsTV = (HashMap<String, Object>) spnThanhVien.getSelectedItem();
                int matv = (int) hsTV.get("matv");

                HashMap<String,Object> hsSach = (HashMap<String, Object>) spnSach.getSelectedItem();
                int masach = (int) hsSach.get("masach");

                int tien = Integer.parseInt(edtTien.getText().toString());
                ThemPhieuMuon(matv,masach,tien);
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    private void getDataThanhVien(Spinner spnThanhVien){
        ThanhVienDAO thanhVienDAO = new ThanhVienDAO(getContext());
        ArrayList<ThanhVien> list = thanhVienDAO.getDSThanhVien();

        ArrayList<HashMap<String, Object>> listHM = new ArrayList<>();
        for (ThanhVien tv : list){
            HashMap<String, Object> hs = new HashMap<>();
            hs.put("matv", tv.getMatv());
            hs.put("hoten",tv.getHoten());
            listHM.add(hs);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(
                getContext(),listHM, android.R.layout.simple_list_item_1,new String[]{"hoten"},new int[]{android.R.id.text1}
        );
        spnThanhVien.setAdapter(simpleAdapter);
    }
    private void getDataSach(Spinner spnSach){
        SachDAO SachDAO = new SachDAO(getContext());
        ArrayList<Sach> list = SachDAO.getDSDauSach();

        ArrayList<HashMap<String, Object>> listHM = new ArrayList<>();
        for (Sach sc : list){
            HashMap<String, Object> hs = new HashMap<>();
            hs.put("masach", sc.getMasach());
            hs.put("tensach",sc.getTensach());
            listHM.add(hs);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(
                getContext(),listHM, android.R.layout.simple_list_item_1,new String[]{"tensach"},new int[]{android.R.id.text1}
        );
        spnSach.setAdapter(simpleAdapter);
    }
    private void ThemPhieuMuon(int matv, int masach, int tien){
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("THONGTIN", Context.MODE_PRIVATE);
        String matt = sharedPreferences.getString("matt", "");

        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String ngay = simpleDateFormat.format(currentTime);

        PhieuMuon phieuMuon = new PhieuMuon(matv,matt,masach,ngay, 0, tien);
        boolean kiemtra = phieuMuonDAO.themPhieuMuon(phieuMuon);
        if (kiemtra){
            Toast.makeText(getContext(), "them thanh cong", Toast.LENGTH_SHORT).show();
            loadData();
        }else{
            Toast.makeText(getContext(), "them that bai", Toast.LENGTH_SHORT).show();
        }
    }
}

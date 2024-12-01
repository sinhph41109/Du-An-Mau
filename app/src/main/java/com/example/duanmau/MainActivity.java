package com.example.duanmau;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.duanmau.Dao.SachDAO;
import com.example.duanmau.Dao.ThuThuDAO;
import com.example.duanmau.Fragment.QLLoaiSachFragment;
import com.example.duanmau.Fragment.QLPhieuMuonFragment;
import com.example.duanmau.Fragment.ThongKeTop10Fragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolBar);
        FrameLayout frameLayout = findViewById(R.id.frameLayout);
        NavigationView navigationView = findViewById(R.id.navigationView);
        drawerLayout = findViewById(R.id.drawerLayout);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()){
                    case R.id.mQLPhieuMuon:
                        fragment = new QLPhieuMuonFragment();
                        break;
                    case R.id.mQLLoaiSach:
                        fragment = new QLLoaiSachFragment();
                        break;
                    case R.id.mThoat:
                        Intent intent = new Intent(MainActivity.this,DangNhapActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    case R.id.mDoiMatKhau:
                        showDialogDoiMatKHau();
                    case R.id.mTop10:
                        fragment = new ThongKeTop10Fragment();
                        break;
                    default:
                        fragment = new QLPhieuMuonFragment();
                        break;
                }

                if(fragment != null){
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.frameLayout, fragment)
                            .commit();
                    toolbar.setTitle(item.getTitle());
                }

                drawerLayout.closeDrawer(GravityCompat.START);

                return false;

            }
        });

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }
    private void showDialogDoiMatKHau(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_doimatkhau,null);
        EditText edtoldPass = view.findViewById(R.id.edtOldPass);
        EditText edtnewPass = view.findViewById(R.id.edtnewPass);
        EditText edtReNewPass = view.findViewById(R.id.edtRenewPass);

        builder.setView(view);

        builder.setPositiveButton("huy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.setPositiveButton("cap nhat", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
             String oldPass = edtoldPass.getText().toString();
             String newPass = edtnewPass.getText().toString();
             String reNewPass = edtReNewPass.getText().toString();
             if(newPass.equals(reNewPass)){
                 SharedPreferences sharedPreferences = getSharedPreferences("THONGTIN",MODE_PRIVATE);
                 String matt = sharedPreferences.getString("matt", "");
                 ThuThuDAO thuThuDAO = new ThuThuDAO(MainActivity.this);
                 boolean check = thuThuDAO.capNhatMatKhau(matt, oldPass, newPass);
                 if(check){
                     Toast.makeText(MainActivity.this, "cap nhat thanh cong", Toast.LENGTH_SHORT).show();
                     Intent intent = new Intent(MainActivity.this, DangNhapActivity.class);
                     intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                 }else{
                     Toast.makeText(MainActivity.this, "cap nhat that bai", Toast.LENGTH_SHORT).show();
                 }
             }else {
                 Toast.makeText(MainActivity.this, "mat khau moi khong khop", Toast.LENGTH_SHORT).show();
             }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
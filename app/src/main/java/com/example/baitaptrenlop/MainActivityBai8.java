package com.example.baitaptrenlop;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivityBai8 extends AppCompatActivity {

    EditText etMaSP, etTenSP, etGiaSP;
    Spinner spLoaiSP;
    List<String> data_lsp = new ArrayList<>();
    ArrayAdapter adapter_lsp;
    ImageView ivHinh;
    Button btThem, btSua, btXoa, btThoat;
    ListView lvDssp;

    List<SanPham> data_sp = new ArrayList<>();
    CustomAdapter_Sp adapter_sp;

    private int index = -1;

    DatabaseSP databaseSP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_bai7);
        setControl();
        setEvent();


        adapter_sp = new CustomAdapter_Sp(this, R.layout.layout_item_sp, data_sp);
        lvDssp.setAdapter(adapter_sp);
        DocDL();
        btThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TaoMoiSanPham();
                DocDL();
            }


        });
        lvDssp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SanPham sp = data_sp.get(position);
                etMaSP.setText(sp.getMaSP());
                etTenSP.setText(sp.getTenSP());
                etGiaSP.setText(sp.getGiaSP());
                if (sp.getLoaiSP().equals("Samsung"))
                    spLoaiSP.setSelection(0);
                if (sp.getLoaiSP().equals("Iphone"))
                    spLoaiSP.setSelection(1);
                if (sp.getLoaiSP().equals("Nokia"))
                    spLoaiSP.setSelection(2);
                index = position;
            }
        });

        lvDssp.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                data_sp.remove(position);
                adapter_sp.notifyDataSetChanged();
                return false;
            }
        });

        btXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SanPham sp = new SanPham();
                sp.setMaSP(etMaSP.getText().toString());
                databaseSP.xoaDl(sp);
                DocDL();
            }
        });

        btSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SanPham sp = data_sp.get(index);
                sp.setMaSP(etMaSP.getText().toString());
                sp.setTenSP(etTenSP.getText().toString());
                sp.setGiaSP(etGiaSP.getText().toString());
                sp.setLoaiSP(spLoaiSP.getSelectedItem().toString());
                databaseSP.suaDL(sp);
                DocDL();
            }
        });

        btThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void DocDL(){
        data_sp.clear();
        data_sp.addAll(databaseSP.DocDL());
        adapter_sp.notifyDataSetChanged();
    }
    private void TaoMoiSanPham() {
        SanPham sp  = new SanPham();
        sp.setMaSP(etMaSP.getText().toString());
        sp.setTenSP(etTenSP.getText().toString());
        sp.setGiaSP(etGiaSP.getText().toString());
        sp.setLoaiSP(spLoaiSP.getSelectedItem().toString());

//        data_sp.add(sp);
//        adapter_sp.notifyDataSetChanged();
        databaseSP.ThemDL(sp);
        Toast.makeText(this, "Them thanh cong", Toast.LENGTH_SHORT).show();

    }

    private void setEvent() {
        KhoiTao();
        databaseSP = new DatabaseSP(this);
        adapter_lsp = new ArrayAdapter(this, android.R.layout.simple_list_item_1, data_lsp);
        spLoaiSP.setAdapter(adapter_lsp);

        spLoaiSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spLoaiSP.getSelectedItem().equals("Samsung"))
                    ivHinh.setImageResource(R.drawable.samsung);
                if (spLoaiSP.getSelectedItem().equals("Nokia"))
                    ivHinh.setImageResource(R.drawable.nokia);
                if (spLoaiSP.getSelectedItem().equals("Iphone"))
                    ivHinh.setImageResource(R.drawable.iphone);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }



    private void KhoiTao() {
        data_lsp.add("Samsung");
        data_lsp.add("Iphone");
        data_lsp.add("Nokia");

    }

    private void setControl() {
        etMaSP = findViewById(R.id.etNhapMaSP);
        etTenSP = findViewById(R.id.etNhapTenSP);
        etGiaSP = findViewById(R.id.etNhapGiaSP);
        spLoaiSP = findViewById(R.id.spLoaiSP);
        ivHinh = findViewById(R.id.ivHinh);
        btThem = findViewById(R.id.btThem);
        btSua = findViewById(R.id.btSua);
        btXoa = findViewById(R.id.btXoa);
        btThoat = findViewById(R.id.btThoat);
        lvDssp = findViewById(R.id.lvDssp);
    }


}
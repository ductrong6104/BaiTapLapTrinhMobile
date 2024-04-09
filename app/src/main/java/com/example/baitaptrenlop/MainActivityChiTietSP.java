package com.example.baitaptrenlop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivityChiTietSP extends AppCompatActivity {

    EditText maSP, tenSP, giaSP;
    Button btnSua, btnXoa, btnQuayLai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chi_tiet_sp);
        setControl();
        setEvent();
    }

    private void setEvent() {
        SanPham sp = (SanPham) getIntent().getSerializableExtra("sp");
        maSP.setText(sp.getMaSP());
        tenSP.setText(sp.getTenSP());
        giaSP.setText(sp.getGiaSP());

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void setControl() {
        maSP = findViewById(R.id.etNhapMaSP);
        tenSP = findViewById(R.id.etNhapTenSP);
        giaSP = findViewById(R.id.etNhapGiaSP);
        btnSua = findViewById(R.id.btSua);
        btnXoa = findViewById(R.id.btXoa);
        btnQuayLai = findViewById(R.id.btQuayLai);
    }

}
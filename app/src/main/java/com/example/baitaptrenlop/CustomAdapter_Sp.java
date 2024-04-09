package com.example.baitaptrenlop;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomAdapter_Sp extends ArrayAdapter {
    Context context;
    int resource;
    List<SanPham> data;

    public CustomAdapter_Sp(@NonNull Context context, int resource, List<SanPham> data) {
        super(context, resource, data);
        this.context = context;
        this.resource = resource;
        this.data = data;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource, null);
        ImageView ivHinh = convertView.findViewById(R.id.ivHinh);
        TextView tvTenSp = convertView.findViewById(R.id.tvTenSP);
        Button btnChiTiet = convertView.findViewById(R.id.btnCTSP);
        SanPham sp = data.get(position);
        tvTenSp.setText(sp.getTenSP());
        if (sp.getLoaiSP().equals("Samsung"))
            ivHinh.setImageResource(R.drawable.samsung);
        if (sp.getLoaiSP().equals("Iphone"))
            ivHinh.setImageResource(R.drawable.iphone);
        if (sp.getLoaiSP().equals("Nokia"))
            ivHinh.setImageResource(R.drawable.nokia);
        btnChiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivityChiTietSP.class);
                intent.putExtra("sp", sp);
                context.startActivity(intent);
            }
        });
        return convertView;


    }
}

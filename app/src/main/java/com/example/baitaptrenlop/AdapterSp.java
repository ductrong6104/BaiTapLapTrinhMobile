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

public class AdapterSp extends ArrayAdapter {
    Context context;
    int resource;
    List<SanPham> data;
    public AdapterSp(@NonNull Context context, int resource, @NonNull List<SanPham> data) {
        super(context, resource, data);
        this.context = context;
        this.resource = resource;
        this.data = data;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource, null);
        TextView tvTenSp = convertView.findViewById(R.id.tvTenSP);
        SanPham sp = data.get(position);
        tvTenSp.setText(sp.getTenSP());

        return convertView;
    }
}

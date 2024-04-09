package com.example.baitaptrenlop;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class FragmentGioHang extends Fragment {

    GridView gvGioHang;
    public static List<SanPham> data = new ArrayList<>();

    CustomAdapter_Sp customAdapterSp;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gio_hang, container, false);
        setControl(view);
        setEvent();
        return view;
    }

    private void setEvent() {
        customAdapterSp = new CustomAdapter_Sp(getActivity(), R.layout.layout_item_sp, data);
        gvGioHang.setAdapter(customAdapterSp);
    }

    private void setControl(View view) {
        gvGioHang = view.findViewById(R.id.gvDanhSach);

    }
}
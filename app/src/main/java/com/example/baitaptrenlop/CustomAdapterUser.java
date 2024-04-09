package com.example.baitaptrenlop;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.net.ContentHandler;
import java.util.ArrayList;
import java.util.List;

public class CustomAdapterUser extends ArrayAdapter {
    Context context;
    int resource;

    List<User> data;
    List<User> data_all;
    public CustomAdapterUser(@NonNull Context context, int resource, @NonNull List<User> data) {
        super(context, resource, data);
        this.context = context;
        this.resource = resource;
        this.data = data;
        this.data_all = data;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource, null);
        ImageView hinh= convertView.findViewById(R.id.ivHinh);
        TextView tvLogin = convertView.findViewById(R.id.tvLogin);
        TextView tvUrl = convertView.findViewById(R.id.tvURL);
        User user = data.get(position);
        tvLogin.setText(user.getLogin());
        tvUrl.setText(user.getUrl());
        Glide.with(context)
                .load(user.getAvatar()).centerCrop()
                .into(hinh);
        return convertView;
    }


}

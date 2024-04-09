package com.example.baitaptrenlop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivityBai10 extends AppCompatActivity {
    ListView lvDanhSach;
    List<User> data;
    CustomAdapterUser adapterUser;
    SearchView searchView;
    List<User> data_all;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_bai10);
        setControl();
        setEvent();
    }

    private void setControl() {
        lvDanhSach = findViewById(R.id.ds);
        searchView = findViewById(R.id.search);
    }

    private void setEvent() {
        DocDL();
        data = new ArrayList<>();
        adapterUser = new CustomAdapterUser(this, R.layout.layout_item_user, data);
        lvDanhSach.setAdapter(adapterUser);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d("search", newText);
//                Toast.makeText(MainActivityBai10.this, "Da nhap: \n" + newText, Toast.LENGTH_SHORT).show();
                data.clear();
                if (newText.equals("")) {

                    data.addAll(data_all);

                }
                else{
                    Log.d("dataall", String.valueOf(data_all.size()));
                    Log.d("containsss", newText);
                    for (User user: data_all){
                        Log.d("getlogin", user.getLogin());
                        if (user.getLogin().contains(newText)){
                            Log.d("contain", newText);
                            data.add(user);
                        }
                    }
                }
                adapterUser.notifyDataSetChanged();
                return false;
            }
        });

    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        DocDL();
//    }

    private void DocDL() {
        data.clear();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "https://api.github.com/search/users?q=mojombo";
        JsonObjectRequest request1 = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Log.d("anc", jsonObject.toString());
                try{
                    String total_count = jsonObject.getString("total_count");
                    JSONArray jsonArray = jsonObject.getJSONArray("items");
                    for (int i = 0; i <jsonArray.length(); i++){
                        try {
                            JSONObject item = jsonArray.getJSONObject(i);
                            User user = new User();
                            user.setLogin(item.getString("login"));
                            user.setUrl(item.getString("url"));
                            user.setAvatar(item.getString("avatar_url"));
                            data.add(user);

                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    data_all = data;
                    adapterUser.notifyDataSetChanged();
                }
                catch (JSONException e){
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error", error.toString());
            }
        });
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null
                , new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("leng data", String.valueOf(response.length()));
                for (int i = 0; i <response.length(); i++){
                    try {
                        JSONObject item = response.getJSONObject(i);
                        User user = new User();
                        user.setLogin(item.getString("login"));
                        user.setUrl(item.getString("url"));
                        user.setAvatar(item.getString("avatar_url"));
                        data.add(user);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
                adapterUser.notifyDataSetChanged();
            }
        }
        , new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivityBai10.this, "Loi\n" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
        requestQueue.add(request1);
    }
}
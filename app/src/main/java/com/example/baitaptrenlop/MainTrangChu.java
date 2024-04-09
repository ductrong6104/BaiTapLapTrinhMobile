package com.example.baitaptrenlop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainTrangChu extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_trang_chu);
        setControl();
        setEvent();
    }

    private void setEvent() {
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.app_name, R.string.app_name);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                if (item.getItemId() == R.id.mnDanhSach){
                    Toast.makeText(MainTrangChu.this, "Danh sach", Toast.LENGTH_SHORT).show();

                }
                if (item.getItemId() == R.id.mnGioHang){
                    Toast.makeText(MainTrangChu.this, "Gio hang", Toast.LENGTH_SHORT).show();
                    fragment = new FragmentGioHang();
                }
                if (item.getItemId() == R.id.mnCaiDat){
                    Toast.makeText(MainTrangChu.this, "Cai dat", Toast.LENGTH_SHORT).show();

                }
                drawerLayout.closeDrawers();
                getSupportFragmentManager().beginTransaction().replace(R.id.flContent, fragment).commit();
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            if (item.getItemId() == R.id.mnDanhSach){
                Toast.makeText(MainTrangChu.this, "Danh sach", Toast.LENGTH_SHORT).show();

            }
            if (item.getItemId() == R.id.mnGioHang){
                Toast.makeText(MainTrangChu.this, "Gio hang", Toast.LENGTH_SHORT).show();

            }
            if (item.getItemId() == R.id.mnCaiDat){
                Toast.makeText(MainTrangChu.this, "Cai dat", Toast.LENGTH_SHORT).show();

            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setControl() {
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigation);
    }
}
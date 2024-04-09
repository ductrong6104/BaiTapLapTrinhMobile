package com.example.baitaptrenlop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivityBai11 extends AppCompatActivity {
    EditText etThongBao;
    EditText etTenSp, etGiasp;
    Button btnThongBao, btnThem, btnXoa;
    GridView gvDSSP;
    List<SanPham> sanPhamList = new ArrayList<>();
    AdapterSp adapterSp;
    String idCurrent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_bai11);
        setControl();
        setEvent();

    }

    private void setControl() {
        etThongBao = findViewById(R.id.etThongBao);
        btnThongBao = findViewById(R.id.btnThongBao);
        gvDSSP = findViewById(R.id.gvDSSP);
        btnThem = findViewById(R.id.btnThem);
        etTenSp = findViewById(R.id.etTenSP);
        etGiasp = findViewById(R.id.etGiaSP);
        btnXoa = findViewById(R.id.btnXoa);
    }

    private void setEvent() {
        adapterSp = new AdapterSp(this, R.layout.layout_item_sp, sanPhamList);
        gvDSSP.setAdapter(adapterSp);
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference myRef = database.getReference("san_pham");
                SanPham sp = new SanPham();
                sp.setMaSP(myRef.push().getKey());
                sp.setTenSP(etTenSp.getText().toString());
                sp.setGiaSP(etGiasp.getText().toString());
                sanPhamList.add(sp);
                adapterSp.notifyDataSetChanged();




// Thêm dữ liệu vào Firebase Realtime Database
                // cho key san pham la node cha dai dien cho doi tuong san pham
                myRef.child(sp.getMaSP()).setValue(sp);
            }
        });
        // Write a message to the database

        DatabaseReference myRef = database.getReference("message");
        btnThongBao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef.setValue(etThongBao.getText().toString());
            }
        });

        String TAG = "firebase";
        DatabaseReference myRef1 = database.getReference("san_pham");
        // Read from the database
        myRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                sanPhamList.clear();
//                etThongBao.setText(value);
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    SanPham sp = snapshot.getValue(SanPham.class);

                    sanPhamList.add(sp);
                }
                adapterSp.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        gvDSSP.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SanPham sp = (SanPham)parent.getItemAtPosition(position);

                // Thực hiện các thao tác với đối tượng được chọn
                etTenSp.setText(sp.getTenSP());
                etGiasp.setText(sp.getGiaSP());
                Log.d("id", sp.getMaSP());
                idCurrent = sp.getMaSP();
            }
        });
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef1.child(idCurrent).removeValue()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                // Xóa dữ liệu thành công
                                Log.d("Firebase", "Data successfully deleted.");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Xảy ra lỗi trong quá trình xóa dữ liệu
                                Log.e("Firebase", "Error deleting data.", e);
                            }
                        });
            }
        });
    }
}
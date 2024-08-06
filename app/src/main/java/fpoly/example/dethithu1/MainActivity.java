package fpoly.example.dethithu1;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fpoly.example.dethithu1.API.InterFaceSanPham;
import fpoly.example.dethithu1.API.XeMay;
import fpoly.example.dethithu1.Adapter.SanPhamAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "SanPham";
    private RecyclerView rcView;
    private List<XeMay> xeMayList;

    private EditText tenXeMay;
    private EditText mauSac;
    private EditText giaBan;
    private EditText moTa;
    private EditText hinhAnh;
    private Button btnThem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Ánh xạ các EditText từ layout
        tenXeMay = findViewById(R.id.tenXeMay);
        mauSac = findViewById(R.id.mauSac);
        giaBan = findViewById(R.id.giaBan);
        moTa = findViewById(R.id.moTa);
        hinhAnh = findViewById(R.id.hinhAnh);
        btnThem = findViewById(R.id.btnThem);

        rcView = findViewById(R.id.recyclerView);
        fetchSanPham();
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = tenXeMay.getText().toString();
                String mau = mauSac.getText().toString();
                Double gia = Double.valueOf(giaBan.getText().toString());
                String mota = moTa.getText().toString();
                String hinh = hinhAnh.getText().toString();
                XeMay xeMay = new XeMay(ten, mau, gia, mota, hinh);
                addXeMay(xeMay);

                tenXeMay.setText("");
                mauSac.setText("");
                giaBan.setText("");
                moTa.setText("");
                hinhAnh.setText("");
            }
        });
    }

    private void fetchSanPham(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        InterFaceSanPham getSanPham = retrofit.create(InterFaceSanPham.class);
        Call<List<XeMay>> call = getSanPham.listxemay();
        call.enqueue(new Callback<List<XeMay>>() {
            @Override
            public void onResponse(Call<List<XeMay>> call, Response<List<XeMay>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<XeMay> xeMays = response.body();
                    Toast.makeText(MainActivity.this, "Hiển thị sản phẩm thành công", Toast.LENGTH_SHORT).show();
                    if (xeMays != null && !xeMays.isEmpty()) {
                        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
                        rcView.setLayoutManager(layoutManager);
                        SanPhamAdapter sanPhamAdapter = new SanPhamAdapter(xeMays, MainActivity.this);
                        rcView.setAdapter(sanPhamAdapter);
                    } else {
                        Toast.makeText(MainActivity.this, "Hiển thị sản phẩm thất bại ", Toast.LENGTH_SHORT).show();
                        Log.e(TAG, "Response error: " + response.code() + " " + response.message());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<XeMay>> call, Throwable throwable) {
                Toast.makeText(MainActivity.this, "Lỗi kết nối: " + throwable.getMessage(), Toast.LENGTH_LONG).show();
                Log.e(TAG, "Network error", throwable);
            }
        });
    }
    private void addXeMay(XeMay xeMay) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        InterFaceSanPham interFaceSanPham = retrofit.create(InterFaceSanPham.class);
        Call<XeMay> call = interFaceSanPham.addXeMay(xeMay);
        call.enqueue(new Callback<XeMay>() {
            @Override
            public void onResponse(Call<XeMay> call, Response<XeMay> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Thêm xe máy thành công", Toast.LENGTH_SHORT).show();
                    fetchSanPham(); // Refresh the list after adding a new item
                } else {
                    Toast.makeText(MainActivity.this, "Thêm xe máy thất bại", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<XeMay> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Lỗi: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e(TAG, "onFailure: ", t);
            }
        });
    }
}


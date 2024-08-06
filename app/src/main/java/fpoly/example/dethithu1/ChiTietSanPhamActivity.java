package fpoly.example.dethithu1;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;

public class ChiTietSanPhamActivity extends AppCompatActivity {
    private TextView txtName, txtPrice, txtDescription, txtMauSac;
    private ImageView imageSanPham;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chi_tiet_san_pham);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.chitietsanpham), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txtName = findViewById(R.id.productName);
        txtPrice = findViewById(R.id.productPrice);
        txtDescription = findViewById(R.id.productDescription);
        txtMauSac = findViewById(R.id.productmausac);
        imageSanPham = findViewById(R.id.productImage);

        String name = getIntent().getStringExtra("sanPhamName");
        String mausac = getIntent().getStringExtra("mausac");
        String giaString = getIntent().getStringExtra("gia");
        Double price = null;
        if (giaString != null && !giaString.isEmpty()) {
            price = Double.parseDouble(giaString);
        }
        String description = getIntent().getStringExtra("mota");
        String imageUrl = getIntent().getStringExtra("hinhanh");

        txtName.setText(name);

        if (price != null) {
            DecimalFormat decimalFormat = new DecimalFormat("#,###.##");
            String formattedPrice = decimalFormat.format(price);
            txtPrice.setText(formattedPrice + " VND");
        } else {
            txtPrice.setText("N/A");
        }

        txtMauSac.setText(mausac);
        txtDescription.setText(description);
        Glide.with(this).load(imageUrl).into(imageSanPham);
    }
}

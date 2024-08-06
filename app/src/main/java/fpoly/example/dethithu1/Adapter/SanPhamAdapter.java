package fpoly.example.dethithu1.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.util.List;

import fpoly.example.dethithu1.API.XeMay;
import fpoly.example.dethithu1.ChiTietSanPhamActivity;
import fpoly.example.dethithu1.R;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.ViewHolder> {
    private List<XeMay> xeMaylist;
    private Context context;

    public SanPhamAdapter(List<XeMay> xeMaylist, Context context) {
        this.xeMaylist = xeMaylist;
        this.context = context;
    }

    @NonNull
    @Override
    public SanPhamAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sanpham, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SanPhamAdapter.ViewHolder holder, int position) {
        XeMay xeMay = xeMaylist.get(position);
        holder.txtTenSp.setText(xeMay.getTenxeph44929());
        holder.txtmauSP.setText(xeMay.getMausacph44929());

        // Format the price to avoid scientific notation
        Double price = xeMay.getGiabanph44929();
        if (price != null) {
            DecimalFormat decimalFormat = new DecimalFormat("#,###");
            String formattedPrice = decimalFormat.format(price);
            holder.txtGiaSP.setText(formattedPrice + " VND");
        } else {
            holder.txtGiaSP.setText("N/A");
        }

        holder.txtMota.setText(xeMay.getMotaph44929());
        Glide.with(holder.itemView.getContext()).load(xeMay.getHinhanhph44929()).into(holder.imageSanPham);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ChiTietSanPhamActivity.class);
                intent.putExtra("sanPhamName", xeMay.getTenxeph44929());
                intent.putExtra("mausac", xeMay.getMausacph44929());
                intent.putExtra("gia", String.valueOf(xeMay.getGiabanph44929()));
                intent.putExtra("mota", xeMay.getMotaph44929());
                intent.putExtra("hinhanh", xeMay.getHinhanhph44929());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return xeMaylist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenSp, txtmauSP, txtGiaSP, txtMota;
        ImageView imageSanPham;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenSp = itemView.findViewById(R.id.txtTenSP);
            txtmauSP = itemView.findViewById(R.id.txtmauSP);
            txtGiaSP = itemView.findViewById(R.id.txtGiaSP);
            txtMota = itemView.findViewById(R.id.txtMotaSP);
            imageSanPham = itemView.findViewById(R.id.imageSanPham);
        }
    }
}

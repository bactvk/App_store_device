package com.example.storedeviceonline.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.storedeviceonline.R;
import com.example.storedeviceonline.model.SanPham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.ItemHolder> {

    Context context;
    ArrayList<SanPham> sanPhamArrayList;

    public SanPhamAdapter(Context context, ArrayList<SanPham> sanPhamArrayList) {
        this.context = context;
        this.sanPhamArrayList = sanPhamArrayList;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_sanpham_latest,null);
        ItemHolder itemHolder = new ItemHolder(view);

        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        SanPham sanPham = sanPhamArrayList.get(position);

        holder.tvNameSP.setText(sanPham.getNameSP());

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.tvPriceSP.setText("Giá: "+decimalFormat.format(sanPham.getPriceSP()) + " Đ" );
        Picasso.with(context).load(sanPham.getImageSP()).placeholder(R.drawable.ic_restaurant_menu).into(holder.imgSP);

    }

    @Override
    public int getItemCount() {
        return sanPhamArrayList.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder{
        ImageView imgSP;
        TextView tvNameSP, tvPriceSP;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);

            imgSP = (ImageView) itemView.findViewById(R.id.imageSP);
            tvNameSP = (TextView) itemView.findViewById(R.id.tvNameSP);
            tvPriceSP = (TextView) itemView.findViewById(R.id.tvPriceSP);

        }
    }
}

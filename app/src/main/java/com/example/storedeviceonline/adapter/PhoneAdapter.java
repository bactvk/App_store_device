package com.example.storedeviceonline.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.storedeviceonline.R;
import com.example.storedeviceonline.model.SanPham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class PhoneAdapter extends BaseAdapter {
    ArrayList<SanPham> sanPhamArrayList;
    Context context;

    public PhoneAdapter(ArrayList<SanPham> sanPhamArrayList, Context context) {
        this.sanPhamArrayList = sanPhamArrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return sanPhamArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public class ViewHolder{
        public TextView tvNamePhone , tvPricePhone , tvDescriptPhone;
        public ImageView imgPhone;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        if(convertView == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.row_phone,null);

            holder.tvNamePhone = convertView.findViewById(R.id.tvNamePhone);
            holder.tvDescriptPhone = convertView.findViewById(R.id.tvDescriptPhone);
            holder.tvPricePhone = convertView.findViewById(R.id.tvPricePhone);
            holder.imgPhone = convertView.findViewById(R.id.imgPhone);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        SanPham sanPham = sanPhamArrayList.get(position);
        holder.tvNamePhone.setText(sanPham.getNameSP());

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.tvPricePhone.setText("Giá : "+decimalFormat.format(sanPham.getPriceSP()) + " Đ");
        holder.tvDescriptPhone.setMaxLines(2);
        holder.tvDescriptPhone.setEllipsize(TextUtils.TruncateAt.END); // dấu ... khi quá dài
        holder.tvDescriptPhone.setText(sanPham.getDescriptSP());


        Picasso.with(context).load(sanPham.getImageSP()).placeholder(R.drawable.ic_restaurant_menu).into(holder.imgPhone);
        return convertView;
    }
}

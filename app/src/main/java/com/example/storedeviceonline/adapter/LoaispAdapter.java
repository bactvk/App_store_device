package com.example.storedeviceonline.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.storedeviceonline.R;
import com.example.storedeviceonline.model.LoaiSP;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class LoaispAdapter extends BaseAdapter {

    ArrayList<LoaiSP> arrayListLoaisp; // bản vẻ
    Context context;    // màn hình

    public LoaispAdapter(ArrayList<LoaiSP> arrayListLoaisp, Context context) {
        this.arrayListLoaisp = arrayListLoaisp;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayListLoaisp.size();
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
        TextView tvNameCategory;
        ImageView imgCategory;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.row_listview_loaisp,null);

            holder.tvNameCategory = convertView.findViewById(R.id.tvLoaisp);
            holder.imgCategory = convertView.findViewById(R.id.imageViewLoaisp);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();

        }
        LoaiSP loaiSP = arrayListLoaisp.get(position);

        holder.tvNameCategory.setText(loaiSP.getNameCategory());
        Picasso.with(context).load(loaiSP.getImageCategory()).placeholder(R.drawable.ic_restaurant_menu).into(holder.imgCategory);


        return convertView;
    }
}

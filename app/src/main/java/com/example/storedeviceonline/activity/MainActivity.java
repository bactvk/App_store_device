package com.example.storedeviceonline.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
//import android.widget.Toolbar;
import androidx.appcompat.widget.Toolbar;

import android.widget.Toast;
import android.widget.ViewFlipper;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.storedeviceonline.R;
import com.example.storedeviceonline.adapter.LoaispAdapter;
import com.example.storedeviceonline.adapter.SanPhamAdapter;
import com.example.storedeviceonline.model.LoaiSP;
import com.example.storedeviceonline.model.SanPham;
import com.example.storedeviceonline.utils.Server;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    ViewFlipper viewFlipper;
    RecyclerView recyclerViewHome;
    NavigationView navigationView;
    ListView listViewHome;

    DrawerLayout drawerLayout;


    ArrayList<LoaiSP> mangLoaiSP;
    LoaispAdapter loaispAdapter;

    ArrayList<SanPham> mangSP;
    SanPhamAdapter sanPhamAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        map();
        //bat su kien toolbar 
        ActionBar();
        // truot quang cao
        ActionViewFlipper();

        //
        getDataLoaiSP();
        listViewHome.setAdapter(loaispAdapter);

        //
        getDataSPLatest();
//        recyclerViewHome.setLayoutManager(new LinearLayoutManager(this));
    }

    private void getDataLoaiSP()
    {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        Log.d("url: ",Server.UrlLoaiSP);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.UrlLoaiSP,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if(response != null){
                            for ( int i = 0; i < response.length() ; i++){
                                try {
                                    JSONObject obj = response.getJSONObject(i);
                                    mangLoaiSP.add(new LoaiSP(
                                            obj.getInt("id"),
                                            obj.getString("nameCategory"),
                                            obj.getString("imageCategory")
                                    ) );
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                loaispAdapter.notifyDataSetChanged();
                            }

                            mangLoaiSP.add(response.length() + 1, new LoaiSP(0,"Liên hệ","https://www.pngitem.com/pimgs/m/156-1568222_red-png-contact-icon-png-download-phone-red.png"));
                            mangLoaiSP.add(response.length() + 2, new LoaiSP(0,"Thông tin","https://www.pngitem.com/pimgs/m/463-4637103_icon-info-transparent-svg-new-icon-hd-png.png"));
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,error.toString(),Toast.LENGTH_SHORT).show();
                    }
                }
                // home : https://icons.iconarchive.com/icons/roundicons/100-free-solid/256/home-icon.png
                // contact : https://www.pngitem.com/pimgs/m/156-1568222_red-png-contact-icon-png-download-phone-red.png
                // info : https://www.pngitem.com/pimgs/m/463-4637103_icon-info-transparent-svg-new-icon-hd-png.png
        );

        requestQueue.add(jsonArrayRequest);
    }

    private void getDataSPLatest()
    {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.UrlSPLatest,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

//                        for ( int i = 0; i < response.length() ; i++){
//                            try {
//                                JSONObject obj = response.getJSONObject(i);
//                                mangSP.add(new SanPham(
//                                        obj.getInt("id"),
//                                        obj.getString("product_name"),
//                                        obj.getInt("product_price"),
//                                        obj.getString("product_image"),
//                                        obj.getString("product_descript"),
//                                        obj.getInt("category_id")
//                                ) );
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                            sanPhamAdapter.notifyDataSetChanged();
//                        }
                        Toast.makeText(MainActivity.this,response.toString(), Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        );

        requestQueue.add(jsonArrayRequest);
    }
    private void ActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_restaurant_menu);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

    }

    private void ActionViewFlipper() {
        // mang chua cac url cua tam hinh quang cao
        ArrayList<String> mangQuangCao = new ArrayList<>();
        mangQuangCao.add("https://sanhangngoai.com/wp-content/uploads/2018/12/138760-phones-review-apple-iphone-7-plus-review-image2-y5u8sd9ijx-409x350.jpg");
        mangQuangCao.add("https://www.powerplanetonline.com/cdnassets/xiaomi_redmi_8a_2gb_32gb_01_m.jpg");
        mangQuangCao.add("https://photo2.tinhte.vn/data/attachment-files/2018/12/4516624_vsmart-active-1-12.jpg");
        mangQuangCao.add("https://www.lg.com/vn/images/laptops/md06005276/gallery/Global_13Z980_White_non_fingerprint_2017_Gallery_Large_01.jpg");

        for(int i = 0 ; i < mangQuangCao.size() ; i++){
            ImageView imageView = new ImageView(getApplicationContext());
            Picasso.with(getApplicationContext()).load(mangQuangCao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);  // CHO IMAGE VUA Fliper
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(3000); // chay trong ?
        viewFlipper.setAutoStart(true);  // tu dong bat dau chay
        // hieu ung
        Animation animation_slide_in  = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_right);
        Animation animation_slide_out  = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out_right);

        viewFlipper.setInAnimation(animation_slide_in);
        viewFlipper.setOutAnimation(animation_slide_out);

    }

    private void map()
    {
        toolbar = (Toolbar) findViewById(R.id.toolbarHome);
        viewFlipper = (ViewFlipper) findViewById(R.id.viewlipper);
        recyclerViewHome = (RecyclerView) findViewById(R.id.recyclerView);
        navigationView = (NavigationView) findViewById(R.id.naVigationView);
        listViewHome = (ListView) findViewById(R.id.listViewHome);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        mangLoaiSP = new ArrayList<>();
        mangLoaiSP.add(0,new LoaiSP(0,"Trang chính","https://icons.iconarchive.com/icons/roundicons/100-free-solid/256/home-icon.png"));

        loaispAdapter = new LoaispAdapter(mangLoaiSP,this);

        mangSP = new ArrayList<>();
        sanPhamAdapter = new SanPhamAdapter(this,mangSP);


    }
}

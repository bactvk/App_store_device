package com.example.storedeviceonline.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.storedeviceonline.R;
import com.example.storedeviceonline.adapter.PhoneAdapter;
import com.example.storedeviceonline.model.SanPham;
import com.example.storedeviceonline.utils.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PhoneActivity extends AppCompatActivity {

    Toolbar toolbarPhone;
    ListView lvPhone;
    PhoneAdapter phoneAdapter;
    ArrayList<SanPham> sanPhamArrayList;

    int idDT = 0 , page = 1 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);

        map();
        GetIDLoaiSP();
        ActionToolBar(); // back
        GetData(page);
    }

    private void GetData(int Page) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String urlPhone = Server.UrlPhone+String.valueOf(Page);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlPhone,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response != null){
                            try {
                                JSONArray jsonArray = new JSONArray(response);
                                for(int i = 0 ; i < jsonArray.length() ; i++){
                                    JSONObject obj = jsonArray.getJSONObject(i);
                                    sanPhamArrayList.add(new SanPham(
                                            obj.getInt("id"),
                                            obj.getString("product_name") ,
                                            obj.getInt("product_price"),
                                            obj.getString("product_image"),
                                            obj.getString("product_descript"),
                                            obj.getInt("category_id")
                                    ));

                                    phoneAdapter.notifyDataSetChanged();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> param = new HashMap<String,String>() ; // key ,value truyền lên serve
                param.put("id_SP", String.valueOf(idDT));
                return param;
            }
        };

        requestQueue.add(stringRequest);
    }

    private void ActionToolBar() {
        setSupportActionBar(toolbarPhone);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarPhone.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void GetIDLoaiSP() {
        idDT = getIntent().getIntExtra("idLoaiSP",-1);
        Log.d("id: ",idDT+"");
    }

    private void map(){
        toolbarPhone = (Toolbar) findViewById(R.id.toolbarPhone);
        lvPhone = (ListView) findViewById(R.id.listViewPhone);
        sanPhamArrayList = new ArrayList<>();
        phoneAdapter = new PhoneAdapter(sanPhamArrayList,getApplicationContext());

        lvPhone.setAdapter(phoneAdapter);

    }
}

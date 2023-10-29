package com.example.ungdungbanhang.activitiy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.ungdungbanhang.R;
import com.example.ungdungbanhang.adapter.LoaispAdapter;
import com.example.ungdungbanhang.adapter.SanphamAdapter;
import com.example.ungdungbanhang.model.Giohang;
import com.example.ungdungbanhang.model.Loaisp;
import com.example.ungdungbanhang.model.Sanpham;
import com.example.ungdungbanhang.ultil.CheckConnection;
import com.example.ungdungbanhang.ultil.server;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerViewmanhinhchinh;
    NavigationView navigationView;
    ListView listViewmanhinhchinh;
    DrawerLayout drawerLayout;
    ArrayList<Loaisp> mangloaisp;
    LoaispAdapter loaispAdapter;
    int idloaisp=0;
    String tenloaisp="";
    String hinhloaisp="";
    int ID = 0;
    String Tensanpham = "";
    Integer Giasanpham = 0;
    String Hinhsanpham = "";
    String Motasanpham = "";
    int IDsanpham = 0;
    ArrayList<Sanpham> mangsanpham;
    SanphamAdapter sanphamAdapter;
    public static ArrayList<Giohang> manggiohang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Anhxa();
        ActionBar();
        GetDuLieuLoaiSP();
        GetDuLiewSPMoiNhat();
        CatchOnItemListView();
    }
    private void CatchOnItemListView() {
        listViewmanhinhchinh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
                            Intent intent =new Intent(MainActivity.this,MainActivity.class);
                            startActivity(intent);
                        }
                        else {
                            CheckConnection.ShowToast_Short(getApplicationContext(),"khong co mang");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 3:
                        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
                            Intent intent =new Intent(MainActivity.this,DienThoaiActivity.class);
                            intent.putExtra("idloaisanpham",mangloaisp.get(i).getId());
                            startActivity(intent);
                        }
                        else {
                            CheckConnection.ShowToast_Short(getApplicationContext(),"khong co mang");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 4:
                        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
                            Intent intent =new Intent(MainActivity.this,LapTopActivity.class);
                            intent.putExtra("idloaisanpham",mangloaisp.get(i).getId());
                            startActivity(intent);
                        }
                        else {
                            CheckConnection.ShowToast_Short(getApplicationContext(),"khong co mang");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }
            }
        });
    }
    private void GetDuLiewSPMoiNhat() {
        RequestQueue requestQueue=Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(server.getSPMoiNhat, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null) {
                    for (int i=0;i<response.length();i++){
                        try {
                            JSONObject jsonObject=response.getJSONObject(i);
                            ID=jsonObject.getInt("product_id");
                            Tensanpham=jsonObject.getString("productName");
                            Giasanpham=jsonObject.getInt("price");
                            Hinhsanpham=jsonObject.getString("img");
                            Motasanpham=jsonObject.getString("description");
                            IDsanpham=jsonObject.getInt("loaisp_id");
                            mangsanpham.add(new Sanpham(ID,Tensanpham,Giasanpham,Hinhsanpham,Motasanpham,IDsanpham));
                            sanphamAdapter.notifyDataSetChanged();
                        }catch (JSONException e){
                            e.printStackTrace();
                        }

                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void ActionBar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }
    private void Anhxa(){
        toolbar= (Toolbar) findViewById(R.id.toolbarmanhinhchinh);
        recyclerViewmanhinhchinh=findViewById(R.id.recyclerview);
        navigationView=(NavigationView) findViewById(R.id.navigationview);
        listViewmanhinhchinh=findViewById(R.id.listviewmanhinhchinh);
        drawerLayout=findViewById(R.id.drawerlayout);
        mangloaisp=new ArrayList<>();
        mangloaisp.add(0,new Loaisp(0,"Trang Chủ","https://tse4.mm.bing.net/th?id=OIP.KJoiIAjBfnQPYgCfZisUUwHaHN&pid=Api&P=0&h=180"));
        mangloaisp.add(1,new Loaisp(0,"Liên Hệ","https://tse4.mm.bing.net/th?id=OIP.1sVJRrXm3F5y8lUY6mw4ZgHaHa&pid=Api&P=0&h=180"));
        mangloaisp.add(2,new Loaisp(0,"Thông Tin","https://tse2.mm.bing.net/th?id=OIP.z_ZWL8WkxUZZTGleCILCGAHaG-&pid=Api&P=0&h=180"));
        loaispAdapter=new LoaispAdapter(mangloaisp,getApplicationContext());
        listViewmanhinhchinh.setAdapter(loaispAdapter);
        mangsanpham=new ArrayList<>();
        sanphamAdapter=new SanphamAdapter(getApplicationContext(),mangsanpham);
        recyclerViewmanhinhchinh.setHasFixedSize(true);
        recyclerViewmanhinhchinh.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        recyclerViewmanhinhchinh.setAdapter(sanphamAdapter);
        if(manggiohang!=null){

        }else{
            manggiohang=new ArrayList<>();
        }
    }
    private void GetDuLieuLoaiSP(){
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(server.Duongdanloaisp, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response!=null){
                    for(int i=0;i<response.length();i++){
                        try {
                            JSONObject jsonObject=response.getJSONObject(i);
                            idloaisp=jsonObject.getInt("loaisp_id");
                            tenloaisp=jsonObject.getString("ten_loaisp");
                            hinhloaisp=jsonObject.getString("hinhsp");
                            mangloaisp.add(new Loaisp(idloaisp,tenloaisp,hinhloaisp));
                            loaispAdapter.notifyDataSetChanged();
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                CheckConnection.ShowToast_Short(getApplicationContext(),error.toString());
            }
        });
        requestQueue.add(jsonArrayRequest);
    }
}

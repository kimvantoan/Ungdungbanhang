package com.example.ungdungbanhang.activitiy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.ungdungbanhang.R;
import com.example.ungdungbanhang.model.Giohang;
import com.example.ungdungbanhang.model.Sanpham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

public class ChiTietSanPham extends AppCompatActivity {
    Toolbar toolbarChitiet;
    ImageView imgChitiet;
    TextView txtten,txtgia,txtmota;
    Spinner spinner;
    Button btndatmua;
    int id=0;
    String TenChiTiet="";
    int GiaChitiet=0;
    String HinhanhChitiet="";
    String Motachitiet="";
    int Idsanpham=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);
        Anhxa();
        ActionToolbar();
        GetInformation();
        CatchEventSpinner();
        EvenButton();
    }
    private void EvenButton() {
        btndatmua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(MainActivity.manggiohang.size()>0){
                    int sl=Integer.parseInt(spinner.getSelectedItem().toString());
                    boolean exists=false;
                    for(int i=0;i<MainActivity.manggiohang.size();i++){
                        if(MainActivity.manggiohang.get(i).getIdsp()==id){
                            MainActivity.manggiohang.get(i).setSoluongsp(MainActivity.manggiohang.get(i).getSoluongsp()+sl);
                            if(MainActivity.manggiohang.get(i).getSoluongsp()>=10){
                                MainActivity.manggiohang.get(i).setSoluongsp(10);
                            }
                            MainActivity.manggiohang.get(i).setGiasp(GiaChitiet*MainActivity.manggiohang.get(i).getSoluongsp());
                            exists=true;
                        }
                    }
                    if(exists==false){
                        int soluong=Integer.parseInt(spinner.getSelectedItem().toString());
                        long Giamoi=soluong* GiaChitiet;
                        MainActivity.manggiohang.add(new Giohang(id,TenChiTiet,Giamoi,HinhanhChitiet,soluong));
                    }
                }else{
                    int soluong=Integer.parseInt(spinner.getSelectedItem().toString());
                    long Giamoi=soluong* GiaChitiet;
                    MainActivity.manggiohang.add(new Giohang(id,TenChiTiet,Giamoi,HinhanhChitiet,soluong));
                }
                Intent intent =new Intent(getApplicationContext(), com.example.ungdungbanhang.activitiy.Giohang.class);
                startActivity(intent);

            }
        });
    }

    private void CatchEventSpinner() {
        Integer[] soluong =new Integer[]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> arrayadapter=new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_dropdown_item,soluong);
        spinner.setAdapter(arrayadapter);
    }

    private void GetInformation() {

        Sanpham sanpham= (Sanpham) getIntent().getSerializableExtra("thongtinsanpham");
        id=sanpham.getID();
        TenChiTiet=sanpham.getTensanpham();
        GiaChitiet=sanpham.getGiasanpham();
        HinhanhChitiet=sanpham.getHinhanhsanpham();
        Motachitiet=sanpham.getMotasanpham();
        Idsanpham=sanpham.getIDsanpham();
        txtten.setText(TenChiTiet);
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        txtgia.setText("Giá: "+decimalFormat.format(GiaChitiet)+"Đ");
        txtmota.setText(Motachitiet);
        Picasso.get().load(HinhanhChitiet)
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(imgChitiet);
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbarChitiet);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarChitiet.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void Anhxa() {
        toolbarChitiet=(Toolbar) findViewById(R.id.toolbarchitietsanpham);
        imgChitiet=(ImageView) findViewById(R.id.imageviewchitietsanpham);
        txtgia=(TextView) findViewById(R.id.textviewgiachitietsanpham);
        txtten=(TextView) findViewById(R.id.textviewtenchitietsanpham);
        txtmota=(TextView) findViewById(R.id.textviewmotachitietsanpham);
        spinner=(Spinner) findViewById(R.id.spinner);
        btndatmua=(Button) findViewById(R.id.buttondatmua);
    }
}
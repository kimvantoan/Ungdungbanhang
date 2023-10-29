package com.example.ungdungbanhang.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ungdungbanhang.R;
import com.example.ungdungbanhang.activitiy.MainActivity;
import com.example.ungdungbanhang.model.Giohang;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class GiohangAdapter extends BaseAdapter {
    Context context;
    ArrayList<Giohang> arraygiohang;

    public GiohangAdapter(Context context, ArrayList<Giohang> arraygiohang) {
        this.context = context;
        this.arraygiohang = arraygiohang;
    }

    @Override
    public int getCount() {
        return arraygiohang.size();
    }

    @Override
    public Object getItem(int i) {
        return arraygiohang.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    public class ViewHolder{
        public TextView txttengiohang,txtgiagiohang;
        ImageView imggiohang;
        public Button btnminus,btnvalues,btnplus;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder=null;
        if(view==null){
            viewHolder=new ViewHolder();
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(R.layout.dong_giohang,null);
            viewHolder.txttengiohang=(TextView) view.findViewById(R.id.textviewtengiohang);
            viewHolder.txtgiagiohang=(TextView) view.findViewById(R.id.textviewgiagiohang);
            viewHolder.imggiohang=(ImageView) view.findViewById(R.id.imageviewgiohang);
            viewHolder.btnminus=(Button) view.findViewById(R.id.buttonminus);
            viewHolder.btnvalues=(Button) view.findViewById(R.id.buttonvalue);
            viewHolder.btnplus=(Button) view.findViewById(R.id.buttonplus);
        }else{
            viewHolder=(ViewHolder) view.getTag();
        }
        Giohang giohang=(Giohang) getItem(i);
        viewHolder.txttengiohang.setText(giohang.getTensp());
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        viewHolder.txtgiagiohang.setText(decimalFormat.format(giohang.getGiasp())+ " Đ");
        Picasso.get().load(giohang.getHinhsp())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(viewHolder.imggiohang);
        viewHolder.btnvalues.setText(giohang.getSoluongsp()+"");
        int sl= Integer.parseInt(viewHolder.btnvalues.getText().toString());
        if(sl>=10){
            viewHolder.btnplus.setVisibility(View.INVISIBLE);
            viewHolder.btnminus.setVisibility(View.VISIBLE);
        }else if(sl<=1){
            viewHolder.btnminus.setVisibility(View.INVISIBLE);
        }else if(sl>=1){
            viewHolder.btnplus.setVisibility(View.VISIBLE);
            viewHolder.btnminus.setVisibility(View.VISIBLE);
        }
        ViewHolder finalViewHolder = viewHolder;
        ViewHolder finalViewHolder1 = viewHolder;
        viewHolder.btnplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int slmoinhat=Integer.parseInt(finalViewHolder.btnvalues.getText().toString())+1;
                int slht= MainActivity.manggiohang.get(i).getSoluongsp();
                long giaht=MainActivity.manggiohang.get(i).getGiasp();
                MainActivity.manggiohang.get(i).setSoluongsp(slmoinhat);
                long giamoinhat=(giaht*slmoinhat/slht);
                MainActivity.manggiohang.get(i).setGiasp(giamoinhat);
                DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
                finalViewHolder.txtgiagiohang.setText(decimalFormat.format(giohang.getGiasp())+ " Đ");
                com.example.ungdungbanhang.activitiy.Giohang.EvenUltil();
                if(slmoinhat>9){
                    finalViewHolder1.btnplus.setVisibility(View.INVISIBLE);
                    finalViewHolder1.btnminus.setVisibility(View.VISIBLE);
                    finalViewHolder1.btnvalues.setText(String.valueOf(slmoinhat));
                }else {
                    finalViewHolder1.btnplus.setVisibility(View.VISIBLE);
                    finalViewHolder1.btnminus.setVisibility(View.VISIBLE);
                    finalViewHolder1.btnvalues.setText(String.valueOf(slmoinhat));
                }
            }
        });
        viewHolder.btnminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int slmoinhat=Integer.parseInt(finalViewHolder.btnvalues.getText().toString())-1;
                int slht= MainActivity.manggiohang.get(i).getSoluongsp();
                long giaht=MainActivity.manggiohang.get(i).getGiasp();
                MainActivity.manggiohang.get(i).setSoluongsp(slmoinhat);
                long giamoinhat=(giaht*slmoinhat/slht);
                MainActivity.manggiohang.get(i).setGiasp(giamoinhat);
                DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
                finalViewHolder.txtgiagiohang.setText(decimalFormat.format(giohang.getGiasp())+ " Đ");
                com.example.ungdungbanhang.activitiy.Giohang.EvenUltil();
                if(slmoinhat<2){
                    finalViewHolder1.btnplus.setVisibility(View.VISIBLE);
                    finalViewHolder1.btnminus.setVisibility(View.INVISIBLE);
                    finalViewHolder1.btnvalues.setText(String.valueOf(slmoinhat));
                }else {
                    finalViewHolder1.btnplus.setVisibility(View.VISIBLE);
                    finalViewHolder1.btnminus.setVisibility(View.VISIBLE);
                    finalViewHolder1.btnvalues.setText(String.valueOf(slmoinhat));
                }
            }
        });
        return view;
    }
}

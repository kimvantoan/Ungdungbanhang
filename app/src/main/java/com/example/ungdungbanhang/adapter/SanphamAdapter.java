package com.example.ungdungbanhang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.example.ungdungbanhang.R;
import com.example.ungdungbanhang.model.Sanpham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class SanphamAdapter extends RecyclerView.Adapter<SanphamAdapter.ItemHoler> {
    Context context;
    ArrayList<Sanpham> arraysanpham;

    public SanphamAdapter(Context context, ArrayList<Sanpham> arraysanpham) {
        this.context = context;
        this.arraysanpham = arraysanpham;
    }

    @NonNull
    @Override
    public ItemHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_sanphammoinhat,null);
        ItemHoler itemHoler=new ItemHoler(v);
        return itemHoler;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHoler holder, int position) {
        Sanpham sanpham=arraysanpham.get(position);
        holder.txttensanpham.setText(sanpham.getTensanpham());
        DecimalFormat decimalFormat =new DecimalFormat("###,###,###");
        holder.txtgiasanpham.setText("Giá : "+ decimalFormat.format(sanpham.getGiasanpham())+" Đ");
        Picasso.get().load(sanpham.getHinhanhsanpham())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(holder.imghinhsanpham);
    }

    @Override
    public int getItemCount() {
        return arraysanpham.size();
    }

    public class ItemHoler extends ViewHolder{
        public ImageView imghinhsanpham;
        public TextView txttensanpham,txtgiasanpham;

        public ItemHoler(@NonNull View itemView) {
            super(itemView);
            imghinhsanpham=(ImageView)itemView.findViewById(R.id.imageviewsanpham);
            txtgiasanpham=(TextView) itemView.findViewById(R.id.textviewgiasanpham);
            txttensanpham=(TextView) itemView.findViewById(R.id.textviewtensanpham);
        }
    }
}

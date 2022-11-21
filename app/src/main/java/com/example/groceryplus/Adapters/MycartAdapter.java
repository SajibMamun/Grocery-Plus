package com.example.groceryplus.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.groceryplus.Models.MycartModel;
import com.example.groceryplus.R;

import java.util.List;

public class MycartAdapter extends RecyclerView.Adapter<MycartAdapter.ViewHolder> {

  Context context;
  List<MycartModel> mycartModelList;
  int totalprice=0;

    public MycartAdapter(Context context, List<MycartModel> mycartModelList) {
        this.context = context;
        this.mycartModelList = mycartModelList;
    }

    @NonNull
    @Override
    public MycartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MycartAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(mycartModelList.get(position).getProductImage()).into(holder.productImage);
        holder.productName.setText(mycartModelList.get(position).getProductName());
        holder.productPrice.setText(mycartModelList.get(position).getProductPrice());
        holder.currentTime.setText("Time: "+mycartModelList.get(position).getCurrentTime());
        holder.currentDate.setText("Date: "+mycartModelList.get(position).getCurrentDate());
        holder.productQuantity.setText("Quantity: "+mycartModelList.get(position).getTotalQuantity());
        holder.totalprice.setText("Total Price: "+String.valueOf(mycartModelList.get(position).getTotalprice()));


        //total ammount calculate
        totalprice=totalprice+mycartModelList.get(position).getTotalprice();
        Intent intent=new Intent("TotalAllAmmount");
        intent.putExtra("totalAmount",totalprice);

        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

    }

    @Override
    public int getItemCount() {
        return mycartModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView productImage;
        TextView currentDate,currentTime, productName, productPrice, productQuantity, totalprice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage=itemView.findViewById(R.id.productImagetvid);
            currentDate=itemView.findViewById(R.id.currenDatetvid);
            currentTime=itemView.findViewById(R.id.currentTimetvid);
            productName=itemView.findViewById(R.id.productNametvid);
            productPrice=itemView.findViewById(R.id.currentPriceidTv);
            productQuantity=itemView.findViewById(R.id.currentQuantityTvid);
            totalprice=itemView.findViewById(R.id.totalPricetvid);
        }
    }
}

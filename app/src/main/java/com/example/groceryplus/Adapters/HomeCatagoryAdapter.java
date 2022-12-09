package com.example.groceryplus.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.groceryplus.Models.HomeCatagoryModel;
import com.example.groceryplus.R;
import com.example.groceryplus.AllActivities.ViewAllActivity;

import java.util.List;

public class HomeCatagoryAdapter extends RecyclerView.Adapter<HomeCatagoryAdapter.ViewHolder> {


   Context context;
   List<HomeCatagoryModel> homeCatagoryModelList;

    public HomeCatagoryAdapter(Context context, List<HomeCatagoryModel> homeCatagoryModelList) {
        this.context = context;
        this.homeCatagoryModelList = homeCatagoryModelList;
    }

    @NonNull
    @Override
    public HomeCatagoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.catagoryitem,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull HomeCatagoryAdapter.ViewHolder holder, int position) {

        Glide.with(context).load(homeCatagoryModelList.get(position).getImg_url()).into(holder.catImg);
        holder.catName.setText(homeCatagoryModelList.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ViewAllActivity.class);
                intent.putExtra("type",homeCatagoryModelList.get(position).getType());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return homeCatagoryModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView catImg;
        TextView catName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            catImg=itemView.findViewById(R.id.catagoryImgid);
            catName=itemView.findViewById(R.id.catagorynameid);
        }
    }
}

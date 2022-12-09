package com.example.groceryplus.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.groceryplus.Models.NavCatagoryModel;
import com.example.groceryplus.R;
import com.example.groceryplus.AllActivities.ViewAllActivity;

import java.util.List;

public class NavCatagoryAdapter extends RecyclerView.Adapter<NavCatagoryAdapter.ViewHolder> {

  Context context;
  List<NavCatagoryModel> navCatagoryModelList;

    public NavCatagoryAdapter(Context context, List<NavCatagoryModel> navCatagoryModelList) {
        this.context = context;
        this.navCatagoryModelList = navCatagoryModelList;
    }

    @NonNull
    @Override
    public NavCatagoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.nav_catagory_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull NavCatagoryAdapter.ViewHolder holder, int position) {

        Glide.with(context).load(navCatagoryModelList.get(position).getImg_url()).into(holder.nav_cat_img);
        holder.nav_cat_name.setText(navCatagoryModelList.get(position).getName());
        holder.nav_cat_descrip.setText(navCatagoryModelList.get(position).getDescription());
        holder.nav_cat_rating.setText(navCatagoryModelList.get(position).getRating());
        holder.nav_cat_disocunt.setText(navCatagoryModelList.get(position).getDiscount());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ViewAllActivity.class);
                intent.putExtra("type",navCatagoryModelList.get(position).getType());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return navCatagoryModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView nav_cat_img;
        TextView nav_cat_name,nav_cat_descrip,nav_cat_rating,nav_cat_disocunt;
        CardView nav_cat_cardview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nav_cat_img=itemView.findViewById(R.id.nav_cat_img_id);
            nav_cat_name=itemView.findViewById(R.id.nav_cat_name_id);
            nav_cat_descrip=itemView.findViewById(R.id.nav_cat_descrip_id);
            nav_cat_rating=itemView.findViewById(R.id.nav_cat_rating_id);
            nav_cat_disocunt=itemView.findViewById(R.id.nav_cat_discount_id);
            nav_cat_cardview=itemView.findViewById(R.id.cardviewid);
        }
    }
}

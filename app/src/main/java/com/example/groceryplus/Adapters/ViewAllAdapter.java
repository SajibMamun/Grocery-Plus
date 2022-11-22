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
import com.example.groceryplus.AllActivities.DetailActivity;
import com.example.groceryplus.Models.ViewAllModel;
import com.example.groceryplus.R;

import java.util.List;

public class ViewAllAdapter extends RecyclerView.Adapter<ViewAllAdapter.ViewHolder> {

    Context context;
    List<ViewAllModel> viewAllModelList;

    public ViewAllAdapter(Context context, List<ViewAllModel> viewAllModelList) {
        this.context = context;
        this.viewAllModelList = viewAllModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_all_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(viewAllModelList.get(position).getImg_url()).into(holder.viewImg);
        holder.viewName.setText(viewAllModelList.get(position).getName());
        holder.viewDescrip.setText(viewAllModelList.get(position).getDescription());
        holder.viewPrice.setText(viewAllModelList.get(position).getPrice() + "৳");
        holder.viewRating.setText(viewAllModelList.get(position).getRating());

        if (viewAllModelList.get(position).getType().equals("fish")) {
            holder.viewPrice.setText(viewAllModelList.get(position).getPrice() + "৳" + "/KG");
        }

        if (viewAllModelList.get(position).getType().equals("chocolate")) {
            holder.viewPrice.setText(viewAllModelList.get(position).getPrice() + "৳" + "/pcs");
        }
        if (viewAllModelList.get(position).getType().equals("egg")) {
            holder.viewPrice.setText(viewAllModelList.get(position).getPrice() + "৳" + "/12pcs");
        }
        if (viewAllModelList.get(position).getType().equals("vegetable")) {
            holder.viewPrice.setText(viewAllModelList.get(position).getPrice() + "৳" + "/KG");
        }
        if (viewAllModelList.get(position).getType().equals("fruit")) {
            holder.viewPrice.setText(viewAllModelList.get(position).getPrice() + "৳" + "/KG");
        }



holder.itemView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context, DetailActivity.class);
       intent.putExtra("detail", viewAllModelList.get(position));
        context.startActivity(intent);

    }
});


    }

    @Override
    public int getItemCount() {
        return viewAllModelList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView viewName, viewDescrip, viewPrice, viewRating;
        ImageView viewImg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            viewImg = itemView.findViewById(R.id.view_img_id);
            viewName = itemView.findViewById(R.id.view_name_id);
            viewDescrip = itemView.findViewById(R.id.view_descrip_id);
            viewPrice = itemView.findViewById(R.id.view_price_id);
            viewRating = itemView.findViewById(R.id.view_rating_id);
        }
    }
}

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
import com.example.groceryplus.Models.PopularModel;
import com.example.groceryplus.R;
import com.example.groceryplus.AllActivities.ViewAllActivity;

import java.util.List;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ViewHolder> {

  private Context context;
  private List<PopularModel> popularModelList;

    public PopularAdapter(Context context, List<PopularModel> popularModelList) {
        this.context = context;
        this.popularModelList = popularModelList;
    }

    @NonNull
    @Override
    public PopularAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.popularitem_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull PopularAdapter.ViewHolder holder,int position) {

        Glide.with(context).load(popularModelList.get(position).getImg_url()).into(holder.popImg);
        holder.name.setText(popularModelList.get(position).getName());
        holder.description.setText(popularModelList.get(position).getDescription());
        holder.discount.setText(popularModelList.get(position).getDiscount());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ViewAllActivity.class);
                intent.putExtra("type",popularModelList.get(position).getType());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return popularModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView popImg;
        TextView name,description,discount;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            popImg=itemView.findViewById(R.id.Popular_imgID);
            name=itemView.findViewById(R.id.ppNameid);
            description=itemView.findViewById(R.id.ppdescripid);
            discount=itemView.findViewById(R.id.ppdiscountid);

        }
    }
}

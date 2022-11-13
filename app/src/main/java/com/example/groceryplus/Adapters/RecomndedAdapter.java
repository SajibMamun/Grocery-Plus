package com.example.groceryplus.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.groceryplus.Models.RecomndedModel;
import com.example.groceryplus.R;

import java.util.List;

public class RecomndedAdapter extends RecyclerView.Adapter<RecomndedAdapter.ViewHolder> {

    Context context;
    List<RecomndedModel> recomndedModelList;

    public RecomndedAdapter(Context context, List<RecomndedModel> recomndedModelList) {
        this.context = context;
        this.recomndedModelList = recomndedModelList;
    }

    @NonNull
    @Override
    public RecomndedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recomandeditem_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecomndedAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(recomndedModelList.get(position).getImg_url()).into(holder.recomImg);
        holder.recomName.setText(recomndedModelList.get(position).getName());
        holder.RecomDescrip.setText(recomndedModelList.get(position).getDescription());
        holder.RecomRating.setText(recomndedModelList.get(position).getRating());

    }

    @Override
    public int getItemCount() {
        return recomndedModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView recomImg;
        TextView recomName,RecomDescrip,RecomRating;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            recomImg=itemView.findViewById(R.id.recomandedimageid);
            recomName=itemView.findViewById(R.id.recomandedNameid);
            RecomDescrip=itemView.findViewById(R.id.recomandeddiscriptionid);
            RecomRating=itemView.findViewById(R.id.recomandedRatingid);
        }
    }
}

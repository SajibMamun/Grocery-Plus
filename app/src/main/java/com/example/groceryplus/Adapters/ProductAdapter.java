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
import com.example.groceryplus.API.ProductModelClass;
import com.example.groceryplus.R;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private Context context;
    List<ProductModelClass> productDetails;

    public ProductAdapter(Context context, List<ProductModelClass> productDetails) {
        this.context = context;
        this.productDetails = productDetails;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.globalitem,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.pname.setText(productDetails.get(position).getName());
        holder.pbrand.setText(productDetails.get(position).getBrand());
        holder.pdescription.setText(productDetails.get(position).getDescription());
        holder.plink.setText(productDetails.get(position).getProduct_link());
        holder.pprice.setText(productDetails.get(position).getPrice() + " $");
        Glide.with(context)
                .load(productDetails.get(position).getImage_link())
                .into(holder.productImage);

    }

    @Override
    public int getItemCount() {
        return productDetails.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView pname,pbrand,pprice,pdescription,pcode,plink;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage=itemView.findViewById(R.id.ProductImaggid);
            pname=itemView.findViewById(R.id.ProductNameid);
            pbrand=itemView.findViewById(R.id.productbrandid);
            pprice=itemView.findViewById(R.id.ProductPriceID);
            pdescription=itemView.findViewById(R.id.ProductDescription);
            plink=itemView.findViewById(R.id.productlinkid);

        }
    }
}

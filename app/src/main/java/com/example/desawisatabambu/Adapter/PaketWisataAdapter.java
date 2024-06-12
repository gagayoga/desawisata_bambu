package com.example.desawisatabambu.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.desawisatabambu.Interface.ProductItemClickListener;
import com.example.desawisatabambu.Model.Product;
import com.example.desawisatabambu.R;

import java.util.List;

public class PaketWisataAdapter extends RecyclerView.Adapter<PaketWisataAdapter.ViewHolder> {
    private Context context;
    private List<Product> productList;
    private ProductItemClickListener listener;

    public PaketWisataAdapter(Context context, List<Product> productList, ProductItemClickListener listener) {
        this.context = context;
        this.productList = productList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_wisata, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Product currentProduct = productList.get(position);

        // Set data to views
        holder.label.setText(currentProduct.getJudul());

        // Set click listener
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onProductItemClick(currentProduct);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView label;
        CardView cardRekomendasi;

        ViewHolder(View itemView) {
            super(itemView);
            label = itemView.findViewById(R.id.namaPaket);
            cardRekomendasi = itemView.findViewById(R.id.cardWisata);
        }
    }
}

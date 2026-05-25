package com.example.recyclep10;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import com.bumptech.glide.Glide;
import com.example.recyclep10.Presiden;
import com.example.recyclep10.databinding.GridItemBinding;
import com.example.recyclep10.databinding.RowItemBinding;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>  {

    private final Context context;
    private boolean isGrid = false;
    private final List<Presiden> list; // penampung data dari class Presiden

    public RecyclerViewAdapter(Context context, List<Presiden> list) {
        this.context = context;
        this.list = list;
    }

    // status grid dari Activity
    public void setViewType(boolean isGrid) {
        this.isGrid = isGrid;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (isGrid) {
            // Inflate layout untuk Grid
            GridItemBinding gridBinding = GridItemBinding.inflate(
                    LayoutInflater.from(parent.getContext()), parent, false);
            return new RecyclerViewHolder(gridBinding);
        } else {
            // Inflate layout untuk Row/List
            RowItemBinding rowBinding = RowItemBinding.inflate(
                    LayoutInflater.from(parent.getContext()), parent, false);
            return new RecyclerViewHolder(rowBinding);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.RecyclerViewHolder holder, int position) {
        Presiden presiden = list.get(position);

        holder.tvName.setText(presiden.getName());
        holder.tvDesc.setText(presiden.getRemarks());

        Glide.with(context)
                .load(list.get(position).getPhoto())
                .into(holder.imgPhoto);

        holder.itemView.setOnClickListener(v -> {
            Toast.makeText(context, list.get(position).getName(), Toast.LENGTH_SHORT).show();
        });

        holder.itemView.setOnClickListener(v -> {
            Toast.makeText(context, list.get(position).getName(), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvName, tvDesc;

        public RecyclerViewHolder(@NonNull ViewBinding binding) {
            super(binding.getRoot());

            imgPhoto = itemView.findViewById(R.id.img_item_photo);
            tvName = itemView.findViewById(R.id.tv_item_name);
            tvDesc = itemView.findViewById(R.id.tv_item_desc);
        }

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
            tvName = itemView.findViewById(R.id.tv_item_name);
            tvDesc = itemView.findViewById(R.id.tv_item_desc);
        }
    }
}
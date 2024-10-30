package com.appcxs.androidcvs.ui.adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.appcxs.androidcvs.R;
import com.appcxs.androidcvs.api.model.Category;

import java.util.ArrayList;

public class CategoryChildAdapter extends RecyclerView.Adapter<CategoryChildAdapter.MyViewHolder> {
    private ArrayList<Category> category;

    private OnClickCategory onClickCategory;
    private Context context;

    public CategoryChildAdapter(Context context, ArrayList<Category> category, OnClickCategory onClickCategory) {
        this.category = category;
        this.onClickCategory = onClickCategory;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemcategory, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.nameCategory.setText(category.get(position).getCategoryName());

        if (category.get(position).getId() >= 19 && category.get(position).getId() <= 24) {
            ViewCompat.setBackgroundTintList(holder.status, ColorStateList.valueOf(context.getColor(R.color.base)));
        } else {
            ViewCompat.setBackgroundTintList(holder.status, ColorStateList.valueOf(context.getColor(R.color.boderitem)));
        }
        holder.itemView.setOnClickListener(v -> {
            onClickCategory.onClickCategory(category.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return category.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView nameCategory;
        private TextView status;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nameCategory = itemView.findViewById(R.id.tv_name_category);
            status = itemView.findViewById(R.id.status);
        }
    }

    public interface OnClickCategory {
        public void onClickCategory(Category category);
    }
}

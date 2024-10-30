package com.appcxs.androidcvs.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.appcxs.androidcvs.R;
import com.appcxs.androidcvs.api.model.News;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {
    private ArrayList<News> news;
    private Context context;

    private OnClickNews onClickNews;

    public NewsAdapter(ArrayList<News> news, Context context, OnClickNews onClickNews) {
        this.news = news;
        this.context = context;
        this.onClickNews = onClickNews;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        news.get(position).getTitle();
        holder.titleNews.setText(news.get(position).getTitle());
        if (news.get(position).getCreateDate() != null) {
            holder.time.setText(news.get(position).getCreateDate());
        }
        holder.des.setText(news.get(position).getDescription());
        Glide.with(context)
                .load(news.get(position).getThumbnail())
                .centerCrop()
                .into(holder.thumnail);

        holder.itemView.setOnClickListener(v -> {
            onClickNews.onClick(news.get(position));
        });

    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView titleNews;
        private TextView des;
        private ImageView thumnail;
        private TextView time;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            titleNews = itemView.findViewById(R.id.title_news);
            des = itemView.findViewById(R.id.description_news);
            thumnail = itemView.findViewById(R.id.thumbnail);
            time = itemView.findViewById(R.id.time_news);
        }
    }

    public interface OnClickNews {
        public void onClick(News news);
    }
}

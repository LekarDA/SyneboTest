package com.example.synebotest.ui.list;

import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.synebotest.R;
import com.example.synebotest.data.model.ArticleDB;
import com.example.synebotest.util.Constants;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsViewHolder> {

    private ArrayList<ArticleDB> articles = new ArrayList<>();
    private ItemClickListener listener;
    private SharedPreferences sharedPreferences;

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_view,parent,false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        holder.initView(articles.get(position));
        holder.itemView.setOnClickListener(v -> {
            ArticleDB article = articles.get(holder.getAdapterPosition());
            sharedPreferences.edit().putString(Constants.SP_KEY, article.getTitle()).apply();
            listener.onItemClick(article.getId());
        });
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public void setData(ArrayList<ArticleDB> listOfArticles) {
        articles.clear();
        articles.addAll(listOfArticles);
        notifyDataSetChanged();
    }

    public void setItemClickListener(ItemClickListener listener){
        this.listener = listener;
    }

    public void setSharedPreferences(SharedPreferences sp){
        sharedPreferences = sp;
    }
}
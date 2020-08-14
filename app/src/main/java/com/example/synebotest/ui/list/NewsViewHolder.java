package com.example.synebotest.ui.list;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.synebotest.R;
import com.example.synebotest.data.model.ArticleDB;
import com.squareup.picasso.Picasso;

public class NewsViewHolder extends RecyclerView.ViewHolder  {

    public NewsViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public void initView(ArticleDB article){
        ImageView photo = itemView.findViewById(R.id.iv_articlePhoto);
        TextView title = itemView.findViewById(R.id.tv_articleTitle);
        Picasso.with(itemView.getContext()).load(article.getUrlToImage()).placeholder(R.drawable.no_photo).into(photo);
        title.setText(article.getTitle());
    }
}
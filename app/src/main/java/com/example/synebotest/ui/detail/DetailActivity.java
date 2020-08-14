package com.example.synebotest.ui.detail;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.example.synebotest.R;
import com.squareup.picasso.Picasso;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DetailActivity extends AppCompatActivity {
    public final static String ID_KEY = "ID_KEY";

    private DetailViewModel detailViewModel;
    private TextView title;
    private TextView author;
    private TextView date;
    private TextView source;
    private TextView description;
    private TextView content;
    private TextView link;
    private ImageView photo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        detailViewModel = new ViewModelProvider(this).get(DetailViewModel.class);
        int id = getIntent().getIntExtra(ID_KEY,-1);
        title = findViewById(R.id.tv_title);
        author = findViewById(R.id.tv_author);
        date = findViewById(R.id.tv_date);
        source = findViewById(R.id.tv_source);
        description = findViewById(R.id.tv_description);
        content = findViewById(R.id.tv_content);
        link = findViewById(R.id.tv_link);
        photo = findViewById(R.id.iv_photo);
        fetchData(id);
    }

    private void fetchData(int id) {
        detailViewModel.fetchArticleById(id).observe(this, article ->{
            title.setText(article.getTitle());
            author.setText(article.getAuthor());
            date.setText(article.getPublishedAt());
            source.setText(article.getSourceName());
            description.setText(article.getDescription());
            content.setText(article.getContent());
            link.setText(article.getUrl());
            Picasso.with(this).load(article.getUrlToImage()).placeholder(R.drawable.no_photo).into(photo);
        });
    }
}
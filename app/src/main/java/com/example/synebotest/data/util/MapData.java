package com.example.synebotest.data.util;

import com.example.synebotest.data.model.Article;
import com.example.synebotest.data.model.ArticleDB;

import java.util.ArrayList;
import java.util.List;

public class MapData {
    public static ArrayList<ArticleDB> mapNewsList(List<Article> articles) {
        ArrayList<ArticleDB> newsDB = new ArrayList<>();
        for (int i = 0; i < articles.size(); i++) {
            Article article = articles.get(i);
            newsDB.add(new ArticleDB(
                    i,
                    article.getSource().getName(),
                    article.getAuthor(),
                    article.getTitle(),
                    article.getDescription(),
                    article.getUrl(),
                    article.getUrlToImage(),
                    article.getPublishedAt(),
                    article.getContent()
            ));
        }
        return newsDB;
    }
}
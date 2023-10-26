package com.example.app2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

/**
 * @author 86186
 */
public class NewsContentActivity extends AppCompatActivity implements Serializable {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_content);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        //传入数据
        TextView newsTitleText = (TextView) findViewById(R.id.news_title);
        TextView newsContentText = (TextView) findViewById(R.id.news_content);
        ImageView newsImageText = (ImageView) findViewById(R.id.image_view);

        //newsImageText.setImageResource(newsImage);
        Intent intent = getIntent();
        News news = (News) intent.getSerializableExtra("news");

        newsTitleText.setText(news.getTitle());
        newsContentText.setText(news.getContent());
        newsImageText.setImageResource(news.getImageId());
    }
}
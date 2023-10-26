package com.example.app3;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class DaoActivity extends AppCompatActivity {
    private List<ImagedaoAdapter.Imagechange> newsList1 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dao);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        //将标题栏隐藏


        iniimage();
        //加载图片点击效果
        inirecycle();
        //加载recycle布局
    }

    public void iniimage() {
        ImageView imageView = (ImageView) findViewById(R.id.backqiang);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void inirecycle() {
        ImagedaoAdapter.Imagechange imagechange1 = new ImagedaoAdapter.Imagechange(R.drawable.dao1);
        newsList1.add(imagechange1);
        ImagedaoAdapter.Imagechange imagechange2 = new ImagedaoAdapter.Imagechange(R.drawable.dao2);
        newsList1.add(imagechange2);
        ImagedaoAdapter.Imagechange imagechange3 = new ImagedaoAdapter.Imagechange(R.drawable.dao3);
        newsList1.add(imagechange3);
        ImagedaoAdapter.Imagechange imagechange4 = new ImagedaoAdapter.Imagechange(R.drawable.dao4);
        newsList1.add(imagechange4);
        ImagedaoAdapter.Imagechange imagechange5 = new ImagedaoAdapter.Imagechange(R.drawable.dao5);
        newsList1.add(imagechange5);

        RecyclerView recyclerView1 = (RecyclerView) findViewById(R.id.recycler_viewdao);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this);
        layoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView1.setLayoutManager(layoutManager1);
        ImagedaoAdapter adapter1 = new ImagedaoAdapter(newsList1);
        recyclerView1.setAdapter(adapter1);
    }
}
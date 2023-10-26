package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class SearchFragment extends Fragment {
    ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // 使用inflater.inflate方法加载布局文件，并返回一个View对象
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        listView = view.findViewById(R.id.search_frag);
        EditText editText = (EditText) view.findViewById(R.id.search_content);
        ImageView buttonx = (ImageView) view.findViewById(R.id.searchx);
        buttonx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key = editText.getText().toString();
                searchInformation(key);
            }
        });

        return view;

    }

    public void searchInformation(String keyword) {
        // 创建OkHttpClient对象
        OkHttpClient client = new OkHttpClient();
        // 构建请求URL
        HttpUrl.Builder urlBuilder = HttpUrl.parse("").newBuilder();
        urlBuilder.addQueryParameter("keyword", keyword);

        // 构建请求对象
        Request request = new Request.Builder()
                .url(urlBuilder.build().toString())
                .build();

        // 发送请求并处理响应
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                }

                String responseData = response.body().string();
                LoginClass loginClass = new Gson().fromJson(responseData, LoginClass.class);
                List<SearchAdapter> searchlist;
                for () {
                    SearchAdapter imagechange1 = new SearchAdapter();
                    searchlist.add(imagechange1);
                }

                RecyclerView recyclerView1 = (RecyclerView) findViewById();
                LinearLayoutManager layoutManager1 = new LinearLayoutManager(this);
                layoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerView1.setLayoutManager(layoutManager1);

            }
        });
    }

}
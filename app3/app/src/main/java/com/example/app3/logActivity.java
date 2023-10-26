package com.example.app3;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class logActivity extends AppCompatActivity implements View.OnClickListener {
    Button button;
    EditText editText1;
    EditText editText2;
    String name;
    String password;
    javaBean javaBean = new javaBean();
    public static final int UPDATE_TEXT = 1;
    String responseData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        //将标题栏隐藏
        inibutton();

    }

    public void inibutton() {
        button = (Button) findViewById(R.id.register);
        editText1 = (EditText) findViewById(R.id.name);
        editText2 = (EditText) findViewById(R.id.pwd);

        Button button1 = (Button) findViewById(R.id.button_qvxiao1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.register) {
            name = editText1.getText().toString();
            password = editText2.getText().toString();
            if ((name.length() != 0) && (password.length() != 0)) {
                sendRequestWithokhttp();
            } else if (name.length() == 0 && password.length() == 0) {
                Toast.makeText(logActivity.this, "用户名和密码不能为空！", Toast.LENGTH_SHORT).show();
            } else if (name.length() == 0 && password.length() != 0) {
                Toast.makeText(logActivity.this, "密码不能为空！", Toast.LENGTH_SHORT).show();
            } else if (name.length() != 0 && password.length() == 0) {
                Toast.makeText(logActivity.this, "用户名不能为空！", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void sendRequestWithokhttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                javaBean javaBean = new javaBean();
                javaBean.setName(name);
                javaBean.setPassword(password);
                String json = new Gson().toJson(javaBean);
                RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), json);
                Request request = new Request.Builder()
                        .url("https://test.xiandejia.com:8888/douban_server/signup")
                        .addHeader("Content-Type", "application/json;charset=UTF-8")
                        .post(requestBody)
                        .build();
                Response response = null;
                try {
                    response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(), "注册成功！", Toast.LENGTH_SHORT).show();
                            }
                        });
                        responseData = response.body().string();
                        LoginClass loginClass = new Gson().fromJson(responseData, LoginClass.class);
                        SharedPreferences sp = getSharedPreferences("loginToken", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("userName", javaBean.getName());
                        editor.putString("password", javaBean.getPassword());
                        editor.putString("Log_Information", loginClass.getData());
                        editor.putBoolean("Shoucang", false);
                        editor.apply();
                        finish();
                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(), "注册失败！", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
}
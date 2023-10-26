package com.example.app3;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

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

public class EnterActivity extends base implements View.OnClickListener {
    Button button;
    EditText editText1;
    EditText editText2;
    TextView textView;
    String name;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        //将标题栏隐藏
        iniinput();
        //加载页面内的控件

    }

    public void iniinput() {
        button = (Button) findViewById(R.id.register2);
        editText1 = (EditText) findViewById(R.id.name2);
        editText2 = (EditText) findViewById(R.id.pwd2);
        textView = (TextView) findViewById(R.id.zhuce);

        Button button1 = (Button) findViewById(R.id.button_qvxiao1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        button.setOnClickListener(this);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EnterActivity.this, logActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.register2) {
            name = editText1.getText().toString();
            password = editText2.getText().toString();

            if ((name.length() != 0) && (password.length() != 0)) {
                sendRequestWithokhttp();
            } else if (name.length() == 0 && password.length() == 0) {
                Toast.makeText(EnterActivity.this, "用户名和密码不能为空！", Toast.LENGTH_SHORT).show();
            } else if (name.length() == 0 && password.length() != 0) {
                Toast.makeText(EnterActivity.this, "密码不能为空！", Toast.LENGTH_SHORT).show();
            } else if (name.length() != 0 && password.length() == 0) {
                Toast.makeText(EnterActivity.this, "用户名不能为空！", Toast.LENGTH_SHORT).show();
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
                //将要发送的信息打包转换成json

                RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), json);
                Request request = new Request.Builder()
                        .url("https://test.xiandejia.com:8888/douban_server/signup")
                        .addHeader("Content-Type", "application/json;charset=UTF-8")
                        .post(requestBody)
                        .build();
                Response response = null;
                //发送到url

                try {
                    response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    //接受信息

                    if (responseData.contains("用户名重复")) {
                        inistore();
                        //将信息存储在本地

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //这里是要更新的UI
                                Toast.makeText(getApplicationContext(), "登录成功！", Toast.LENGTH_SHORT).show();

                                LayoutInflater factory = LayoutInflater.from(EnterActivity.this);
                                View layout = factory.inflate(R.layout.view_two, null);

                                TextView textView1 = (TextView) layout.findViewById(R.id.yonghuming);
                                textView1.setText("           " + name);
                                textView1.setVisibility(View.VISIBLE);

                                Button button1 = (Button) layout.findViewById(R.id.tuichudenglu);
                                button1.setVisibility(View.VISIBLE);
                                button1.setEnabled(true);
                                button1.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        textView1.setVisibility(View.INVISIBLE);
                                        button1.setVisibility(View.INVISIBLE);
                                        button1.setEnabled(false);
                                        textView1.setEnabled(false);
                                        inistoreempty();

                                    }
                                });
                                //刷新我的UI界面
                            }
                        });

                        finish();
                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //这里是要更新的UI
                                Toast.makeText(getApplicationContext(), "登录失败！", Toast.LENGTH_SHORT).show();
                                editText1.setText("");
                                editText2.setText("");
                                //清空内容
                            }
                        });
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    public void inistore() {
        SharedPreferences sp = getSharedPreferences("loginToken", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("userName", name);
        editor.putString("password", password);
        editor.putString("Log_Information", "Login_Succeeded");
        editor.putBoolean("Shoucang", false);
        editor.apply();
    }

    public void inistoreempty() {
        SharedPreferences sp = getSharedPreferences("loginToken", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("userName", "");
        editor.putString("password", "");
        editor.putString("Log_Information", "");
        editor.putBoolean("Shoucang", false);
        editor.apply();
    }
}
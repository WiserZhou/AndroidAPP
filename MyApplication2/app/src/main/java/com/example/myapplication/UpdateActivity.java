package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class UpdateActivity extends AppCompatActivity {

    String old_password;
    String new_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EditText editText_old = (EditText) findViewById(R.id.old_password);
        EditText editText_new = (EditText) findViewById(R.id.new_password);

        Button button = (Button) findViewById(R.id.finish);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                old_password = editText_old.getText().toString();
                new_password = editText_new.getText().toString();
                if ((old_password.length() != 0) && (new_password.length() != 0)) {
                    sendRequestWithokhttp();
                } else if (old_password.length() == 0 && new_password.length() == 0) {
                    Toast.makeText(UpdateActivity.this, "旧密码和新密码不能为空！", Toast.LENGTH_SHORT).show();
                } else if (old_password.length() == 0 && new_password.length() != 0) {
                    Toast.makeText(UpdateActivity.this, "旧密码密码不能为空！", Toast.LENGTH_SHORT).show();
                } else if (old_password.length() != 0 && new_password.length() == 0) {
                    Toast.makeText(UpdateActivity.this, "新密码不能为空！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void sendRequestWithokhttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                javaBean javaBean = new javaBean();
                javaBean.setPassword(old_password);
                javaBean.setNew_password(new_password);
                String json = new Gson().toJson(javaBean);
                RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), json);
                Request request = new Request.Builder()
                        .url("https://yapi.werun.top:8888/mock/474/user")
                        .addHeader("Content-Type", "application/json;charset=UTF-8")
                        .put(requestBody)
                        .build();
                Response response = null;
                try {
                    response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(), "修改成功！", Toast.LENGTH_SHORT).show();
                            }
                        });
//                        responseData = response.body().string();
//                        LoginClass loginClass = new Gson().fromJson(responseData, LoginClass.class);
//                        SharedPreferences sp = getSharedPreferences("loginToken", MODE_PRIVATE);
//                        SharedPreferences.Editor editor = sp.edit();
//                        editor.putString("userName", javaBean.getName());
//                        editor.putString("password", javaBean.getPassword());
//                        editor.putString("Log_Information", loginClass.getData());
//                        editor.putBoolean("Shoucang", false);
//                        editor.apply();
                        finish();
                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(), "修改失败！", Toast.LENGTH_SHORT).show();
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
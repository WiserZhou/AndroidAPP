package com.example.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
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

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    Button button;
    EditText editText1;
    EditText editText2;
    String telephoneNumber;
    String password;
    User User = new User();
    public static final int UPDATE_TEXT = 1;
    String responseData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        inibutton();
    }
    public void inibutton() {
        button = (Button) findViewById(R.id.register_finish);
        editText1 = (EditText) findViewById(R.id.register_tele);
        editText2 = (EditText) findViewById(R.id.register_pass);

        Button button1 = (Button) findViewById(R.id.button_qvxiao);
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
        if (v.getId() == R.id.register_finish) {
            telephoneNumber = editText1.getText().toString();
            password = editText2.getText().toString();
            if ((telephoneNumber.length() != 0) && (password.length() != 0)) {
                sendRequestWithokhttp();
            } else if (telephoneNumber.length() == 0 && password.length() == 0) {
                Toast.makeText(RegisterActivity.this, "电话号码和密码不能为空！", Toast.LENGTH_SHORT).show();
            } else if (telephoneNumber.length() == 0 && password.length() != 0) {
                Toast.makeText(RegisterActivity.this, "密码不能为空！", Toast.LENGTH_SHORT).show();
            } else if (telephoneNumber.length() != 0 && password.length() == 0) {
                Toast.makeText(RegisterActivity.this, "电话号码不能为空！", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void sendRequestWithokhttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                User User = new User();
                User.setTelephoneNumber(telephoneNumber);
                User.setPassword(password);
                String json = new Gson().toJson(User);
                RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), json);
                Request request = new Request.Builder()
                        .url("https://yapi.werun.top:8888/mock/474/user/register")
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
                        editor.putString("telephoneNumber", User.getTelephoneNumber());
                        editor.putString("password", User.getPassword());
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
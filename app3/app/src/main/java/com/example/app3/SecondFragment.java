package com.example.app3;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButtonToggleGroup;

import org.w3c.dom.Text;


/**
 * @author 86186
 */
public class SecondFragment extends Fragment {
    private View view;
    SharedPreferences pref;
    String userName;
    String password;
    String Log_Information;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.view_two, container, false);
        pref = getActivity().getSharedPreferences("loginToken", Activity.MODE_PRIVATE);
        userName = pref.getString("userName", "");
        password = pref.getString("password", "");
        Log_Information = pref.getString("Log_Information", "");

        if (Log_Information.length() == 0) {
            ImageView imageView = (ImageView) view.findViewById(R.id.weidenglu);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), EnterActivity.class);
                    v.getContext().startActivity(intent);
                }
            });
        } else {
            TextView textView1 = (TextView) view.findViewById(R.id.yonghuming);
            textView1.setText("           " + userName);
            textView1.setVisibility(View.VISIBLE);

            Button button1 = (Button) view.findViewById(R.id.tuichudenglu);
            button1.setVisibility(View.VISIBLE);
            button1.setEnabled(true);
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    textView1.setVisibility(View.INVISIBLE);
                    button1.setVisibility(View.INVISIBLE);
                    textView1.setEnabled(false);
                    button1.setEnabled(false);

                }
            });
        }
        return view;
    }

    public void onResume() {
        super.onResume();
        pref = getActivity().getSharedPreferences("loginToken", Activity.MODE_PRIVATE);
        userName = pref.getString("userName", "");
        password = pref.getString("password", "");
        Log_Information = pref.getString("Log_Information", "");
        if (userName.length() != 0) {
            //Toast.makeText(getContext(), "登入成功!", Toast.LENGTH_SHORT).show();
            TextView textView1 = (TextView) view.findViewById(R.id.yonghuming);
            textView1.setText("           " + userName);
            textView1.setVisibility(View.VISIBLE);
            Button button1 = (Button) view.findViewById(R.id.tuichudenglu);
            button1.setVisibility(View.VISIBLE);
            button1.setEnabled(true);
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    textView1.setVisibility(View.INVISIBLE);
                    button1.setVisibility(View.INVISIBLE);
                    button1.setEnabled(false);
                    SharedPreferences sp = getActivity().getSharedPreferences("loginToken", Activity.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("userName", "");
                    editor.putString("password", "");
                    editor.putString("Log_Information", "");
                    editor.putBoolean("Shoucang", false);
                    editor.apply();
                }
            });
        } else {
            TextView textView1 = (TextView) view.findViewById(R.id.yonghuming);
            //textView1.setText("           " + userName);
            textView1.setVisibility(View.INVISIBLE);
            textView1.setEnabled(false);
            Button button1 = (Button) view.findViewById(R.id.tuichudenglu);
            button1.setVisibility(View.INVISIBLE);
            button1.setEnabled(false);
            ImageView imageView = (ImageView) view.findViewById(R.id.weidenglu);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), EnterActivity.class);
                    v.getContext().startActivity(intent);
                }
            });
        }


    }
}

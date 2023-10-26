package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.Image;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView mNavigationView;
    private FragmentManager mFragmentManager;
    private Fragment[] fragments;
    private int lastFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        //将标题栏隐藏
        mNavigationView = findViewById(R.id.bottom_nav_view);
        initFragment();
        initListener();
    }

    private void initFragment() {
        HomeFragment homeFragment = new HomeFragment();
        MyFragment myFragment = new MyFragment();
        SearchFragment searchFragment = new SearchFragment();
        fragments = new Fragment[]{homeFragment, myFragment, searchFragment};
        mFragmentManager = getSupportFragmentManager();
        //默认显示HomeFragment
        mFragmentManager.beginTransaction()
                .replace(R.id.main_page_controller, homeFragment)
                .show(homeFragment)
                .commit();

    }

    private void initListener() {

        //mNavigationView.performClick();
        mNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                SharedPreferences pref = getSharedPreferences("loginToken", Activity.MODE_PRIVATE);
                String userName = pref.getString("userName", "");
                String password = pref.getString("password", "");
                String Log_Information = pref.getString("Log_Information", "");

                LinearLayout layout = (LinearLayout) findViewById(R.id.my);
                int itemx =item.getItemId();
                switch (itemx) {
                    case R.id.home:
                        if (lastFragment != 0) {
                            MainActivity.this.switchFragment(lastFragment, 0);
                            lastFragment = 0;
                        }
                        return true;
                    case R.id.search:
                        if (lastFragment != 1) {
                            MainActivity.this.switchFragment(lastFragment, 1);
                            lastFragment = 1;
                        }
                        return true;
                    case R.id.my:
                        if (lastFragment != 2) {
                            MainActivity.this.switchFragment(lastFragment, 2);
                            lastFragment = 2;
                        }
                        return false;
                    default:
                        return true;
                }
            }
        });
    }
    private void switchFragment(int lastFragment, int index) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.hide(fragments[lastFragment]);
        if (!fragments[index].isAdded()) {
            transaction.add(R.id.main_page_controller, fragments[index]);
        }
        transaction.show(fragments[index]).commitAllowingStateLoss();
    }
}
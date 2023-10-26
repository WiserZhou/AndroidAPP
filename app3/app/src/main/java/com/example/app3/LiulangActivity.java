package com.example.app3;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class LiulangActivity extends AppCompatActivity implements MyScrollView.OnScrollListener, View.OnClickListener {
    private List<ImageyAdapter.Imagechange> newsList1 = new ArrayList<>();
    private List<ImagezAdapter.Imagechange> newsList2 = new ArrayList<>();
    ImageView ivTranslateLogo;
    private TranslateAnimation translateAniShow, translateAniHide;

    private LinearLayout lLayoutParent;
    private MyScrollView myScrollView;
    private boolean flag = false;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liulang);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        //将标题栏隐藏
        iniimage();
        //初始化控件

        ivTranslateLogo = (ImageView) findViewById(R.id.iv_translate_logo);
        translateAnimation();
        bindViews();
        viewsAddListener();
        //设置一个小动画

    }
    public void iniimage(){
        ImageView imageView = (ImageView) findViewById(R.id.backx);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ImageyAdapter.Imagechange imagechange1 = new ImageyAdapter.Imagechange(R.drawable.imagey1);
        newsList1.add(imagechange1);
        ImageyAdapter.Imagechange imagechange2 = new ImageyAdapter.Imagechange(R.drawable.imagey2);
        newsList1.add(imagechange2);
        ImageyAdapter.Imagechange imagechange3 = new ImageyAdapter.Imagechange(R.drawable.imagey3);
        newsList1.add(imagechange3);
        ImageyAdapter.Imagechange imagechange4 = new ImageyAdapter.Imagechange(R.drawable.imagey4);
        newsList1.add(imagechange4);
        RecyclerView recyclerView1 = (RecyclerView) findViewById(R.id.recycler_viewy);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this);
        layoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView1.setLayoutManager(layoutManager1);
        ImageyAdapter adapter1 = new ImageyAdapter(newsList1);
        recyclerView1.setAdapter(adapter1);

        ImagezAdapter.Imagechange imagechangez1 = new ImagezAdapter.Imagechange(R.drawable.imagez1);
        newsList2.add(imagechangez1);
        ImagezAdapter.Imagechange imagechangez2 = new ImagezAdapter.Imagechange(R.drawable.imagez2);
        newsList2.add(imagechangez2);
        ImagezAdapter.Imagechange imagechangez3 = new ImagezAdapter.Imagechange(R.drawable.imagez3);
        newsList2.add(imagechangez3);
        ImagezAdapter.Imagechange imagechangez4 = new ImagezAdapter.Imagechange(R.drawable.imagez4);
        newsList2.add(imagechangez4);
        ImagezAdapter.Imagechange imagechangez5 = new ImagezAdapter.Imagechange(R.drawable.imagez5);
        newsList2.add(imagechangez5);
        ImagezAdapter.Imagechange imagechangez6 = new ImagezAdapter.Imagechange(R.drawable.imagez6);
        newsList2.add(imagechangez6);
        ImagezAdapter.Imagechange imagechangez7 = new ImagezAdapter.Imagechange(R.drawable.imagez7);
        newsList2.add(imagechangez7);
        ImagezAdapter.Imagechange imagechangez8 = new ImagezAdapter.Imagechange(R.drawable.imagez8);
        newsList2.add(imagechangez8);
        ImagezAdapter.Imagechange imagechangez9 = new ImagezAdapter.Imagechange(R.drawable.imagez9);
        newsList2.add(imagechangez9);
        ImagezAdapter.Imagechange imagechangez10 = new ImagezAdapter.Imagechange(R.drawable.imagez10);
        newsList2.add(imagechangez10);

        RecyclerView recyclerViewz1 = (RecyclerView) findViewById(R.id.recycler_viewz);
        LinearLayoutManager layoutManagerz1 = new LinearLayoutManager(this);
        layoutManagerz1.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerViewz1.setLayoutManager(layoutManagerz1);
        ImagezAdapter adapterz1 = new ImagezAdapter(newsList2);
        recyclerViewz1.setAdapter(adapterz1);

        TextView textView = (TextView) findViewById(R.id.zhankai);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView1 = (TextView) findViewById(R.id.neirong);
                textView1.setText("近未来，科学家们发现太阳急速衰老膨胀，短时间内包括地球在内的整个太阳系都将被太阳所吞没。为了自救，人类提出一个名为“流浪地球\"的大胆计划，即倾全球之力在地球表面建造上万座发动机和转向发动机，推动地球离开太阳系，用2500年的时间奔往另外一个栖息之地。中国航天员刘培强(吴京饰）在儿子刘启四岁那年前往国际空间站，和国际同侪肩负起领航者的重任。转眼刘启（屈楚萧饰）长大，他带着妹妹朵朵（赵今麦饰）偷偷跑到地表，偷开外公韩子昂（吴孟达饰）的运输车，结果不仅遭到逮捕，还遭遇了全球发动机停摆的事件。为了修好发动机，阻止地球坠入木星，全球开始展开饱和式营救，连刘启他们的车也被强征加入。在与时间赛跑的过程中，无数的人前仆后继，奋不顾身，只为延续百代子孙生存的希望.......\n" +
                        "本片根据刘慈欣的同名小说改编。@豆瓣");
                textView.setText("");


            }
        });
        final int[] flag = {0};
        Button button = (Button) findViewById(R.id.shoucang);
        SharedPreferences sp = getSharedPreferences("loginToken", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        SharedPreferences pref = getSharedPreferences("loginToken", Activity.MODE_PRIVATE);
        Boolean shoucang = pref.getBoolean("Shoucang", false);
        if (shoucang) {
            button.setBackgroundResource(R.drawable.yishoucang);
            flag[0] = 1;
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag[0] == 0) {
                    button.setBackgroundResource(R.drawable.yishoucang);
                    editor.putBoolean("Shoucang", true);
                    editor.apply();
                    flag[0] = 1;
                } else {
                    button.setBackgroundResource(R.drawable.shoucang);
                    editor.putBoolean("Shoucang", false);
                    editor.apply();
                    flag[0] = 0;
                }

            }
        });
    }
    @SuppressLint("WrongViewCast")
    private void bindViews() {
        lLayoutParent = findViewById(R.id.Main_lLayoutParent);
        myScrollView = findViewById(R.id.slv);
    }

    private void viewsAddListener() {
        //当布局的状态或者控件的可见性发生改变回调的接口
        lLayoutParent.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            public void onGlobalLayout() {
                //这一步很重要，使得上面的购买布局和下面的购买布局重合
                onScroll(myScrollView.getScrollY());
            }
        });
        myScrollView.setOnScrollListener(this);
    }

    public void onScroll(int scrollY) {
        if (flag == false && scrollY >= 625) {
            ivTranslateLogo.startAnimation(translateAniShow);
            ivTranslateLogo.setVisibility(View.VISIBLE);
            flag = true;
        } else if (flag == true && scrollY < 625) {
            ivTranslateLogo.startAnimation(translateAniHide);
            flag = false;
            translateAniHide.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    ivTranslateLogo.setVisibility(View.INVISIBLE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }
            });
        }
    }

    //位移动画
    private void translateAnimation() {


        //向上位移显示动画  从自身位置的最下端向上滑动了自身的高度
        translateAniShow = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF,//RELATIVE_TO_SELF表示操作自身
                0,//fromXValue表示开始的X轴位置
                Animation.RELATIVE_TO_SELF,
                0,//fromXValue表示结束的X轴位置
                Animation.RELATIVE_TO_SELF,
                1,//fromXValue表示开始的Y轴位置
                Animation.RELATIVE_TO_SELF,
                0);//fromXValue表示结束的Y轴位置
        translateAniShow.setRepeatMode(Animation.REVERSE);
        translateAniShow.setDuration(500);

        //向下位移隐藏动画  从自身位置的最上端向下滑动了自身的高度
        translateAniHide = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF,//RELATIVE_TO_SELF表示操作自身
                0,//fromXValue表示开始的X轴位置
                Animation.RELATIVE_TO_SELF,
                0,//fromXValue表示结束的X轴位置
                Animation.RELATIVE_TO_SELF,
                0,//fromXValue表示开始的Y轴位置
                Animation.RELATIVE_TO_SELF,
                1);//fromXValue表示结束的Y轴位置
        translateAniHide.setRepeatMode(Animation.REVERSE);
        translateAniHide.setDuration(500);
    }

    @Override
    public void onClick(View v) {

    }
}
package com.example.app3;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Bundle;
import android.text.Layout;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 86186
 */
public class FirstFragment extends Fragment implements View.OnClickListener,
        ViewPager.OnPageChangeListener {
    private View view;
    private List<ImageAdapter.Imagechange> newsList1 = new ArrayList<>();
    private List<ImagesAdapter.Imagechange> imagesList = new ArrayList<>();
    private ViewPager vpager_four;
    private ImageView img_cursor;
    private TextView tv_one;
    private TextView tv_two;

    private ArrayList<View> listViews;
    private int offset = 0;//移动条图片的偏移量
    private int currIndex = 0;//当前页面的编号
    private int bmpWidth;// 移动条图片的长度
    private int one = 0; //移动条滑动一页的距离
    private int two = 0; //滑动条移动两页的距离
    LinearLayout layout;
    ImageView imageView5;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.view_one, container, false);
        layout = (LinearLayout) view.findViewById(R.id.blanK);
        layout.setVisibility(View.VISIBLE);
        initbutton();
        initViews();
        return view;


    }

    public void initbutton() {
        ImageView imageView = (ImageView) view.findViewById(R.id.top_search);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(v.getContext(), "进入搜索页面", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        });
        TextView textView1 = (TextView) view.findViewById(R.id.all);
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(v.getContext(), "点击全部", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        });
        ImageView imageView1 = (ImageView) view.findViewById(R.id.imageView1);
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(v.getContext(), "点击了找电影", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        });
        ImageView imageView2 = (ImageView) view.findViewById(R.id.imageView2);
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(v.getContext(), "点击了豆瓣榜单", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        });
        ImageView imageView3 = (ImageView) view.findViewById(R.id.imageView3);
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(v.getContext(), "点击了豆瓣猜", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        });
        ImageView imageView4 = (ImageView) view.findViewById(R.id.imageView4);
        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(v.getContext(), "点击了豆瓣片单", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        });

        Button button = (Button) view.findViewById(R.id.zhuan);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LiulangActivity.class);
                startActivity(intent);
            }
        });
        ImagesAdapter.Imagechange images1 = new ImagesAdapter.Imagechange(R.drawable.images1);
        imagesList.add(images1);
        ImagesAdapter.Imagechange images2 = new ImagesAdapter.Imagechange(R.drawable.images2);
        imagesList.add(images2);
        ImagesAdapter.Imagechange images3 = new ImagesAdapter.Imagechange(R.drawable.images3);
        imagesList.add(images3);
        ImagesAdapter.Imagechange images4 = new ImagesAdapter.Imagechange(R.drawable.images4);
        imagesList.add(images4);
        ImagesAdapter.Imagechange images5 = new ImagesAdapter.Imagechange(R.drawable.images5);
        imagesList.add(images5);
        ImagesAdapter.Imagechange images6 = new ImagesAdapter.Imagechange(R.drawable.images6);
        imagesList.add(images6);
        ImagesAdapter.Imagechange images7 = new ImagesAdapter.Imagechange(R.drawable.images7);
        imagesList.add(images7);
        ImagesAdapter.Imagechange images8 = new ImagesAdapter.Imagechange(R.drawable.images8);
        imagesList.add(images8);
        ImagesAdapter.Imagechange images9 = new ImagesAdapter.Imagechange(R.drawable.images9);
        imagesList.add(images9);
        ImagesAdapter.Imagechange images10 = new ImagesAdapter.Imagechange(R.drawable.images10);
        imagesList.add(images10);
        ImagesAdapter.Imagechange images11 = new ImagesAdapter.Imagechange(R.drawable.images11);
        imagesList.add(images11);
        ImagesAdapter.Imagechange images12 = new ImagesAdapter.Imagechange(R.drawable.images12);
        imagesList.add(images12);
        ImagesAdapter.Imagechange images13 = new ImagesAdapter.Imagechange(R.drawable.images13);
        imagesList.add(images13);
        ImagesAdapter.Imagechange images14 = new ImagesAdapter.Imagechange(R.drawable.images14);
        imagesList.add(images14);
        ImagesAdapter.Imagechange images15 = new ImagesAdapter.Imagechange(R.drawable.images15);
        imagesList.add(images15);
        ImagesAdapter.Imagechange images16 = new ImagesAdapter.Imagechange(R.drawable.images16);
        imagesList.add(images16);
        ImagesAdapter.Imagechange images17 = new ImagesAdapter.Imagechange(R.drawable.images17);
        imagesList.add(images17);
        ImagesAdapter.Imagechange images18 = new ImagesAdapter.Imagechange(R.drawable.images18);
        imagesList.add(images18);
        ImagesAdapter.Imagechange images19 = new ImagesAdapter.Imagechange(R.drawable.images19);
        imagesList.add(images19);
        ImagesAdapter.Imagechange images20 = new ImagesAdapter.Imagechange(R.drawable.images20);
        imagesList.add(images20);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_viewa);
        StaggeredGridLayoutManager layoutManager = new
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        ImagesAdapter adapterx = new ImagesAdapter(imagesList);
        recyclerView.setAdapter(adapterx);

        ImageAdapter.Imagechange imagechange1 = new ImageAdapter.Imagechange(R.drawable.card1);
        newsList1.add(imagechange1);
        ImageAdapter.Imagechange imagechange2 = new ImageAdapter.Imagechange(R.drawable.card2);
        newsList1.add(imagechange2);
        ImageAdapter.Imagechange imagechange3 = new ImageAdapter.Imagechange(R.drawable.card3);
        newsList1.add(imagechange3);
        RecyclerView recyclerView1 = (RecyclerView) view.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(view.getContext());
        layoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView1.setLayoutManager(layoutManager1);
        ImageAdapter adapter1 = new ImageAdapter(newsList1);
        recyclerView1.setAdapter(adapter1);
        imageView5 = (ImageView) view.findViewById(R.id.qudenglu);
        imageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), EnterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initViews() {

        vpager_four = (ViewPager) view.findViewById(R.id.vpager);
        //viewpager的id
        tv_one = (TextView) view.findViewById(R.id.tv_one);
        tv_two = (TextView) view.findViewById(R.id.tv_two);
        img_cursor = (ImageView) view.findViewById(R.id.img_cursor);
        //下划线动画的相关设置：
        bmpWidth = BitmapFactory.decodeResource(getResources(), R.drawable.line).getWidth();// 获取图片宽度
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;// 获取分辨率宽度
        offset = (screenW / 4 - bmpWidth) / 2;// 计算偏移量
        Matrix matrix = new Matrix();
        matrix.postTranslate(offset, 0);
        img_cursor.setImageMatrix(matrix);// 设置动画初始位置
        //移动的距离
        one = offset * 2 + bmpWidth;// 移动一页的偏移量,比如1->2,或者2->3
        two = one * 2;// 移动两页的偏移量,比如1直接跳3

        listViews = new ArrayList<View>();
        LayoutInflater mInflater = getLayoutInflater();
        listViews.add(mInflater.inflate(R.layout.one, null, false));
        listViews.add(mInflater.inflate(R.layout.two, null, false));
        vpager_four.setAdapter(new MyPagerAdapter(listViews));
        vpager_four.setCurrentItem(0);          //设置ViewPager当前页，从0开始算
        tv_one.setOnClickListener(this);
        tv_two.setOnClickListener(this);
        vpager_four.addOnPageChangeListener(this);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int index) {
        Animation animation = null;
        switch (index) {
            case 0:
                if (currIndex == 1) {
                    animation = new TranslateAnimation(one, 0, 0, 0);
                } else if (currIndex == 2) {
                    animation = new TranslateAnimation(two, 0, 0, 0);
                }
                tv_one.setTextSize(23);
                tv_one.setTextColor(Color.parseColor("#000000"));
                tv_two.setTextSize(20);
                tv_two.setTextColor(Color.parseColor("#9D9D9D"));
                break;
            case 1:
                if (currIndex == 0) {
                    animation = new TranslateAnimation(offset, one, 0, 0);
                } else if (currIndex == 2) {
                    animation = new TranslateAnimation(two, one, 0, 0);
                }
                vpager_four.setCurrentItem(1);
                tv_one.setTextSize(20);
                tv_one.setTextColor(Color.parseColor("#9D9D9D"));
                tv_two.setTextSize(23);
                tv_two.setTextColor(Color.parseColor("#000000"));
                break;
        }
        currIndex = index;
        animation.setFillAfter(true);// true表示图片停在动画结束位置
        animation.setDuration(300); //设置动画时间为300毫秒
        img_cursor.startAnimation(animation);//开始动画
    }


    @Override
    public void onPageScrollStateChanged(int state) {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_one:
                vpager_four.setCurrentItem(0);
                tv_one.setTextSize(23);
                tv_one.setTextColor(Color.parseColor("#000000"));
                tv_two.setTextSize(20);
                tv_two.setTextColor(Color.parseColor("#9D9D9D"));
                break;
            case R.id.tv_two:
                vpager_four.setCurrentItem(1);
                tv_one.setTextSize(20);
                tv_one.setTextColor(Color.parseColor("#9D9D9D"));
                tv_two.setTextSize(23);
                tv_two.setTextColor(Color.parseColor("#000000"));
                break;
        }
    }

    private FragmentManager mFragmentManager;
    private Fragment[] fragments;

    public void onResume() {
        super.onResume();
        SharedPreferences pref = getActivity().getSharedPreferences("loginToken", Activity.MODE_PRIVATE);
        String userName = pref.getString("userName", "");
        String password = pref.getString("password", "");
        String Log_Information = pref.getString("Log_Information", "");
        if (userName.length() != 0) {
            layout.setVisibility(View.INVISIBLE);
            imageView5.setEnabled(false);
        } else {
            layout.setVisibility(View.VISIBLE);
            imageView5.setEnabled(true);
        }
    }
}

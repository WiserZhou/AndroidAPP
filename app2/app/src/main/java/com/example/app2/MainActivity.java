package com.example.app2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        ViewPager.OnPageChangeListener {
    private List<News> newsList1 = new ArrayList<>();
    private List<News> newsList2 = new ArrayList<>();
    private List<News> newsList3 = new ArrayList<>();
    private ViewPager vpager_four;
    private ImageView img_cursor;
    private TextView tv_one;
    private TextView tv_two;
    private TextView tv_three;
    private ArrayList<View> listViews;
    private int offset = 0;//移动条图片的偏移量
    private int currIndex = 0;//当前页面的编号
    private int bmpWidth;// 移动条图片的长度
    private int one = 0; //移动条滑动一页的距离
    private int two = 0; //滑动条移动两页的距离

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        //将标题栏隐藏
        initViews();

    }

    private void initViews() {
        vpager_four = (ViewPager) findViewById(R.id.vpager_four);
        //viewpager的id
        tv_one = (TextView) findViewById(R.id.tv_one);
        tv_two = (TextView) findViewById(R.id.tv_two);
        tv_three = (TextView) findViewById(R.id.tv_three);
        //三个标题的定义
        img_cursor = (ImageView) findViewById(R.id.img_cursor);
        //下划线的图像

        //下划线动画的相关设置：
        bmpWidth = BitmapFactory.decodeResource(getResources(), R.drawable.line).getWidth();// 获取图片宽度
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;// 获取分辨率宽度
        offset = (screenW / 3 - bmpWidth) / 2;// 计算偏移量
        Matrix matrix = new Matrix();
        matrix.postTranslate(offset, 0);
        img_cursor.setImageMatrix(matrix);// 设置动画初始位置
        //移动的距离
        one = offset * 2 + bmpWidth;// 移动一页的偏移量,比如1->2,或者2->3
        two = one * 2;// 移动两页的偏移量,比如1直接跳3


        //往ViewPager填充View，同时设置点击事件与页面切换事件
        listViews = new ArrayList<View>();
        LayoutInflater mInflater = getLayoutInflater();
        listViews.add(mInflater.inflate(R.layout.view_one, null, false));
        listViews.add(mInflater.inflate(R.layout.view_two, null, false));
        listViews.add(mInflater.inflate(R.layout.view_three, null, false));
        vpager_four.setAdapter(new MyPagerAdapter(listViews));
        vpager_four.setCurrentItem(0);          //设置ViewPager当前页，从0开始算


        tv_one.setOnClickListener(this);
        tv_two.setOnClickListener(this);
        tv_three.setOnClickListener(this);
        vpager_four.addOnPageChangeListener(this);
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
                break;
            case 1:
                if (currIndex == 0) {
                    animation = new TranslateAnimation(offset, one, 0, 0);
                } else if (currIndex == 2) {
                    animation = new TranslateAnimation(two, one, 0, 0);
                }
                break;
            case 2:
                if (currIndex == 0) {
                    animation = new TranslateAnimation(offset, two, 0, 0);
                } else if (currIndex == 1) {
                    animation = new TranslateAnimation(one, two, 0, 0);
                }
                break;
        }
        currIndex = index;
        animation.setFillAfter(true);// true表示图片停在动画结束位置
        animation.setDuration(300); //设置动画时间为300毫秒
        img_cursor.startAnimation(animation);//开始动画
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.tv_one:
                vpager_four.setCurrentItem(0);
                initNews1();
                RecyclerView recyclerView1 = (RecyclerView) findViewById(R.id.recycler_view1);
                LinearLayoutManager layoutManager1 = new LinearLayoutManager(this);
                recyclerView1.setLayoutManager(layoutManager1);
                NewsAdapter adapter1 = new NewsAdapter(newsList1);
                recyclerView1.setAdapter(adapter1);
                break;
            case R.id.tv_two:
                vpager_four.setCurrentItem(1);
                initNews2();
                RecyclerView recyclerView2 = (RecyclerView) findViewById(R.id.recycler_view2);
                LinearLayoutManager layoutManager2 = new LinearLayoutManager(this);
                recyclerView2.setLayoutManager(layoutManager2);
                NewsAdapter adapter2 = new NewsAdapter(newsList2);
                recyclerView2.setAdapter(adapter2);
                break;
            case R.id.tv_three:
                vpager_four.setCurrentItem(2);
                initNews3();
                RecyclerView recyclerView3 = (RecyclerView) findViewById(R.id.recycler_view3);
                LinearLayoutManager layoutManager3 = new LinearLayoutManager(this);
                recyclerView3.setLayoutManager(layoutManager3);
                NewsAdapter adapter3 = new NewsAdapter(newsList3);
                recyclerView3.setAdapter(adapter3);
                break;
            default:

                break;
        }
    }
    private void initNews1() {
        for (int i = 0; i < 5; i++) {


            News news1_1 = new News("这个春节，和中国探月太空萌兔一起体验“月球足球赛", R.drawable.news1_1, "兔年将至，充满好奇心的太空萌兔在嫦娥三号位于月球的着陆点（北纬44.12°，西经19.51°）意外发现了开启足球世界的大门。在萌兔的召唤下，以埃德森、B·席尔瓦、帕尔默为首的曼城球星即刻响应，号召队友们开启了一场奇幻的太空之旅。经过小伙伴们的不懈努力，足球终于降落在了美丽的天宫。萌兔的心愿实现了，登月成功的曼城球星们包括哈兰德、德布劳内、福登等也加入到了热闹的庆祝活动中。玉兔新春“城”祥瑞，佳节曼舞同相会。中国探月太空萌兔携手曼城俱乐部，为大家送上新春祝福，同时祝愿每一位热爱生活的朋友都能在新的一年弘扬“追逐梦想，勇于探索”的探月精神，在各自的领域发光发热——“城”就在兔年。");
            newsList1.add(news1_1);
            News news1_2 = new News("三星推出ARM版Galaxy Book2 Pro 360：搭载骁龙8cx Gen 3，配备S-Pen", R.drawable.news1_2, "在今年2月举办的MWC2022世界移动通信大会上，三星推出了Galaxy Book2 Pro与Galaxy Book2 Pro 360笔记本电脑，均配备AMOLED显示屏与英特尔处理器，Galaxy Book2 Pro 360名称中的“360”代表这是一台翻转笔记本，可切换为平板模式，三星近日宣布推出Galaxy Book2 Pro 360 ARM版本，搭载高通骁龙8cx Gen 3处理器。");
            newsList1.add(news1_2);
            News news1_3 = new News("谷歌在印度输掉Android上诉案：未能避免1.6亿美元罚款", R.drawable.news1_3, "北京时间1月19日晚间消息，据报道，今日在印度输掉了Android反垄断上诉案，从而未能避免130亿卢比（约合1.6亿美元）的罚款。\n" +
                    "\n" +
                    "去年10月，印度反垄断监管机构“竞争委员会”（CCI）宣布对谷歌处以1.6亿美元的罚款，原因是该公司存在与Android移动设备相关的不公平竞争行为。\n" +
                    "\n" +
                    "本月初，谷歌向印度一上诉法庭对此提起诉讼，称印度CCI做出的罚款决定，在很大程度上抄袭了欧盟委员会之前做出的罚款决定，因此应该予以撤销。但很快，谷歌的这一诉讼就被印度上诉法庭驳回。\n" +
                    "\n" +
                    "随后，谷歌再次上诉到印度最高法院。今日，印度最高法院又驳回了谷歌的上诉，并要求上诉法庭在3月31日之前了解此案。与此同时，最高法院又赋予谷歌一个星期的宽限期，以履行上诉法庭的反垄断裁决。\n" +
                    "\n" +
                    "除了Android反垄断罚款，印度CCI去年10月还对谷歌处以额外93.6亿卢比（约合1.13亿美元）的反垄断罚款，原因是谷歌滥用其在应用商店市场的主导地位，推广自家的支付系统。同样，谷歌也对此提出了上诉。（飞象网）");
            newsList1.add(news1_3);
        }
    }

    private void initNews2() {
        for (int i = 0; i < 5; i++) {
            News news2_1 = new News("新春献词|鹏华基金总裁邓召明：踔厉奋发、笃行不怠，为经济高质量发展贡献公募力量", R.drawable.news2_1, "鹏华基金总裁邓召明表示，虎越雄关，兔临春境；革故辞旧，万象更新。值此辞旧迎新之际，我谨代表鹏华基金祝大家新春快乐，万事如意！在这春风送暖的时节回首过往，我们共同见证公募基金的产品数量突破万只大关，总管理规模突破26万亿，公募基金行业正在迈入高质量发展的新阶段。\n" +
                    "\n" +
                    "　　站在资本市场新发展阶段，鹏华基金将紧紧围绕高质量发展这一主线，在践行普惠金融、服务国家战略、推动创新发展中担当尽责，为中国经济高质量发展贡献公募基金力量。");
            newsList2.add(news2_1);
            News news2_2 = new News("光伏巨头业绩集体报喜 百亿扩产热潮再现", R.drawable.news2_2, "1月19日，隆基绿能（601012.SH）、通威股份（600438.SH）、TCL中环（002129.SZ）、大全能源（688303.SH）等千亿市值光伏企业相继公布2022年度业绩预告，净利润集体大增。\n" +
                    "\n" +
                    "在2022年度全球光伏新增装机量再创新高的背景之下，国内光伏企业迎来利润的高增长。叠加此前已经公布业绩预告的阳光电源（300274.SZ）、天合光能（688599.SZ）、晶澳科技（002459.SZ），可以明确的是，光伏产业链盈利层面的马太效应依然明显。\n" +
                    "\n" +
                    "事实上，在2022年度国内光伏新增装机规模创历史新高的背景下，光伏龙头企业业绩集体飘红的情况属意料之中。值得一提的是，在对未来几年光伏需求保持乐观判断的预期之下，龙头企业们再度掀起百亿级扩产热潮，冲击行业竞争格局。");
            newsList2.add(news2_2);
            News news2_3 = new News("重现“烟火气”的郑州：有饭店满座满订，有堂食排号数百", R.drawable.news2_3, "1月20日上午，一直给郑州多家饭店打订餐电话的王瑞，最终还是失望了。\n" +
                    "\n" +
                    "“今年咋回事儿，（饭店）生意咋都这么火？”王瑞忍不住向第一财经记者抱怨说，往年，大年三十晚上的年夜饭，都是一大家子人在家里吃，今年想着疫情放开了，也好长时间没去饭店聚餐了，跟家人商量后，临时决定去当地几家老饭店定个包厢，结果，打了一圈电话，都没订到包厢，有些饭店的订餐电话打了好几次，都一直提示忙音，连打都没打不进去。\n" +
                    "\n" +
                    "“真不好意思，年三十、初一、初二，店里的包厢都订满了。”谢家河南菜一家门店的客服在电话里说。\n" +
                    "\n" +
                    "须水邓记叫花鸡锦上园店副总经理田军也表示，该店从大年三十到初五，基本上都是预定满席状态。\n" +
                    "\n" +
                    "“前段时间大家都在家待了那么长时间了，很多人都不想在家里自己做了，再加上到了春节，不少人都愿意约上亲朋好友，到饭店里面欢聚一堂。”阿五黄河大鲤鱼英协路店店长刘晓瑞说，其所在门店的包厢，如今也基本上都是满订状态。");
            newsList2.add(news2_3);
        }
    }

    private void initNews3() {
        for (int i = 0; i < 5; i++) {
            News news3_1 = new News("大年初二看C罗！CR7沙特联赛首秀来了，开启征服之旅，预订进球", R.drawable.news3_1, "与巴黎圣日耳曼的友谊赛，C罗先发出战打进2球，状态十分出色，所有人都在期待他的沙特联赛首秀。北京时间1月23日凌晨1时30分，也就是中国的大年初二，沙特联赛第14轮利雅得胜利将在主场迎战伊蒂法克，主帅鲁迪-加西亚已经宣布，C罗会在这场比赛中登场亮相，而且很可能是首发。");
            newsList3.add(news3_1);
            News news3_2 = new News("阿尔特塔：我们仍然可以踢得更好 必须吸取上场跟曼联比赛的教训", R.drawable.news3_2, "直播吧1月21日讯 本周日，阿森纳将在主场迎战同样状态正佳的曼联，主帅阿尔特塔认为他们可以踢得更好，同时必须吸取联赛首回合与曼联比赛的教训。\n" +
                    "\n" +
                    "如果阿森纳本场比赛可以战胜曼联，那么他们将在19场比赛中获得50分——这也是他们半个世纪以来最快的得分，阿尔特塔对此说道：“我仍然认为我们可以踢得更好。”\n" +
                    "\n" +
                    "自2004年以来，阿森纳有望收获19年来的第一个联赛冠军，当被问及现在的连胜对他们的心态有何影响时，阿尔特塔回答说：“我们赢得了很多比赛，希望我们能在周日继续保持这种状态，我们能做到这一点的最好方法就是保持我们现在的状态，甚至更好。\n" +
                    "\n" +
                    "对于本赛季阿森纳有望获得联赛冠军，他则表示：“我认为要做到这一点需要付出巨大的努力，但现在谈论这个还太远了。”");
            newsList3.add(news3_2);
            News news3_3 = new News("若复健过程继续无痛，戴维斯将在下周于主场比赛中复出", R.drawable.news3_3, "今日，湖人122-121主场险胜灰熊。\n" +
                    "\n" +
                    "赛后，据《洛杉矶时报》记者DanWoike报道，据熟知相关计划的消息人士透露，如果安东尼-戴维斯在右脚复健过程中继续保持无痛，他将在下周于主场回归赛场，在湖人开启五连客之旅之前。\n" +
                    "\n" +
                    "湖人下周将在主场背靠背连续迎战快船和马刺。");
            newsList3.add(news3_3);

        }
    }


}
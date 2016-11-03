package com.example.tp.auxiliaryteachingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.example.tp.imageHolder.NetworkImageHolderView;
import com.example.tp.loginRegister.Login;
import com.example.tp.myviews.SlidingViewBySelf;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity  implements AdapterView.OnItemClickListener, ViewPager.OnPageChangeListener, OnItemClickListener {
    //侧边栏菜单
    private SlidingViewBySelf slidingMenu;
    private ConvenientBanner convenientBanner;
    //从网络上加载图片
    private List<String> networkImages;
    //图片URL
    private String[] images = {"http://img2.imgtn.bdimg.com/it/u=3093785514,1341050958&fm=21&gp=0.jpg",
            "http://img2.3lian.com/2014/f2/37/d/40.jpg",
            "http://images.123rf.com.cn/450wm/andreypopov/andreypopov1510/andreypopov151000364.jpg",
            "http://img2.3lian.com/2014/f2/37/d/39.jpg",
            "http://www.8kmm.com/UploadFiles/2012/8/201208140920132659.jpg",
            "http://f.hiphotos.baidu.com/image/h%3D200/sign=1478eb74d5a20cf45990f9df460b4b0c/d058ccbf6c81800a5422e5fdb43533fa838b4779.jpg",
            "http://f.hiphotos.baidu.com/image/pic/item/09fa513d269759ee50f1971ab6fb43166c22dfba.jpg"
    };
    //课程列表
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        slidingMenu = (SlidingViewBySelf)findViewById(R.id.activity_main);
        initViews();
        init();
    }

    public void initViews()
    {
        convenientBanner = (ConvenientBanner) findViewById(R.id.convenientBanner);
        listView = (ListView) findViewById(R.id.listView);

        listView.setOnItemClickListener(this);
    }
    //登录/注册
    public void loginRegister(View v)
    {
        startActivity(new Intent(this, Login.class));
    }
    //我的课程
    public void myClasses(View v)
    {

    }
    //我的下载
    public void myDownloads(View v)
    {

    }
    //消息中心
    public void infoCenter(View v)
    {

    }
    //我的错题
    public void myWrongLibs(View v)
    {

    }

    //设置
    public void setting(View v)
    {

    }

    public void init()
    {
        initImageLoader();
        //网络加载例子
        networkImages= Arrays.asList(images);
        convenientBanner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
            @Override
            public NetworkImageHolderView createHolder() {
                return new NetworkImageHolderView();
            }
        },networkImages)//设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.mipmap.ic_page_indicator, R.mipmap.ic_page_indicator_focused})
                //设置指示器的方向
//                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)
//                .setOnPageChangeListener(this)//监听翻页事件
                .setOnItemClickListener(this);
    }

    //初始化网络图片缓存库
    private void initImageLoader(){
        //网络图片例子,结合常用的图片缓存库UIL,你可以根据自己需求自己换其他网络图片库
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder().
                showImageForEmptyUri(R.drawable.ic_default_adimage)
                .cacheInMemory(true).cacheOnDisk(true).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                getApplicationContext()).defaultDisplayImageOptions(defaultOptions)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO).build();
        ImageLoader.getInstance().init(config);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onPageSelected(int position) {
        Toast.makeText(this,"监听到翻到第"+position+"了",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(int position) {
        Toast.makeText(this,"点击了第"+position+"个",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        Toast.makeText(this,"监听到翻到第"+position+"了",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        Toast.makeText(this,"监听"+state+"了",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //开始自动翻页
        convenientBanner.startTurning(3000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //停止翻页
        convenientBanner.stopTurning();
    }
}

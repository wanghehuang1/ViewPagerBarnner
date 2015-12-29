package com.dsw.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by dsw on 2015/12/29.
 */
public class ViewPagerBarnnerUrl extends RelativeLayout implements ViewPager.OnPageChangeListener {
    /**
     * ViewPager对象
     */
    private ViewPager viewPager;
    /**
     * 指示器
     */
    private LinearLayout indicatorView;
    private Context context;
    /**
     * 存放Url地址
     */
    private LinkedList<String> imageUrls = new LinkedList<String>();
    /**
     * 存放ImageView
     */
    private LinkedList<ImageView> imageViews = new LinkedList<ImageView>();
    /**
     * ViewPager的点击事件
     */
    private ViewPagerClick viewPagerClick;
    /**
     * 指示器的大小
     */
    private float indicatorSize;
    /**
     * 指示器的背景
     */
    private int indicatorDrawable;
    /**
     * 指示器间隔
     */
    private float indicatorInterval;
    /**
     * 容器的高度
     */
    private float containerHeight;
    private float indicatorMarginBottom;
    /**
     * 设置背景图片
     */
    private int indicatorBackgroud;
    /**
     * 选中的位置
     */
    private int currentPostion;
    private Handler mHandler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            viewPager.setCurrentItem(currentPostion + 1);
            mHandler.sendEmptyMessageDelayed(0, 3000);
        }
    };

    public ViewPagerBarnnerUrl(Context context) {
        this(context,null);
    }

    public ViewPagerBarnnerUrl(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ViewPager);
        indicatorSize = typedArray.getDimension(R.styleable.ViewPager_indicatorSize, 10);
        indicatorInterval = typedArray.getDimension(R.styleable.ViewPager_indicatorInterval, 15);
        indicatorDrawable = typedArray.getResourceId(R.styleable.ViewPager_indicatorDrawable, 0);
        indicatorBackgroud = typedArray.getResourceId(R.styleable.ViewPager_indicatorBackgroud, 0);
        containerHeight = typedArray.getDimension(R.styleable.ViewPager_containerHeight, 20);
        indicatorMarginBottom = typedArray.getDimension(R.styleable.ViewPager_indicatorMarginBottom, 10);
        typedArray.recycle();
        initViews();
    }

    /**
     * 初始化View的视图
     */
    private void initViews(){
        viewPager = new ViewPager(context);
        LayoutParams viewPagerParams = new LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
        viewPager.setLayoutParams(viewPagerParams);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setOnPageChangeListener(this);

        indicatorView = new LinearLayout(context);
        indicatorView.setOrientation(LinearLayout.HORIZONTAL);
        LayoutParams layoutParams = new LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, (int)containerHeight);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        layoutParams.bottomMargin =  (int) indicatorMarginBottom;
        indicatorView.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        indicatorView.setLayoutParams(layoutParams);
        if(indicatorBackgroud != 0){
            indicatorView.setBackgroundDrawable(context.getResources().getDrawable(indicatorBackgroud));
        }
        addView(viewPager);
        addView(indicatorView);
        mHandler.sendEmptyMessageDelayed(0, 3000);
    }

    /**
     * ViewPager的适配器
     */
    private PagerAdapter viewPagerAdapter = new PagerAdapter() {

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public int getCount() {
            return imageUrls == null ? 0 : imageUrls.size();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public void finishUpdate(ViewGroup container) {
        }

        @Override
        public int getItemPosition(Object object) {
            return super.getItemPosition(object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = null;
            if(imageViews != null && imageViews.size() > 0){
                imageView = imageViews.get(position);
                imageView.setTag(position > 0 ? position - 1 : 0);		//因为我们在开头新增一项，所以-1
                imageView.setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        if(viewPagerClick != null){
                            viewPagerClick.viewPagerOnClick(view);
                        }
                    }
                });
            }
            container.addView(imageView);
            return imageView;
        }
    };

    @Override
    public void onPageScrollStateChanged(int arg0) {
    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {
    }

    @Override
    public void onPageSelected(int location) {
        if(location == this.imageUrls.size() -1){
            location = 1;
            viewPager.setCurrentItem(location,false);
        }else if(location == 0){
            location = this.imageUrls.size() -2;
            viewPager.setCurrentItem(location,false);
        }
        currentPostion = location;
        setSelectPage(location - 1);
    }


    /**
     * 从url地址创建imageview对象，同时初始化指示器
     */
    private void createImageView(List<String> imageUrlList){
        if(imageUrlList != null && imageUrlList.size() > 0){
            ImageView imageView;
            View pointView;
            //清除头尾
            if(imageViews.size() > 1){
                imageViews.removeFirst();
                imageViews.removeLast();
            }
            for(String url : imageUrlList){
                imageView = new ImageView(context);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                ImageLoader.getInstance().displayImage(url, imageView);
                imageViews.add(imageView);

                pointView = new View(context);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int)(indicatorSize),(int)(indicatorSize));
                if(imageUrlList.indexOf(url) != imageUrlList.size() -1)//由于是居中，所以最后一个不需要margin
                    params.rightMargin = (int)indicatorInterval;
                pointView.setLayoutParams(params);
                if(indicatorDrawable != 0)
                    pointView.setBackgroundDrawable(context.getResources().getDrawable(indicatorDrawable));
                pointView.setEnabled(false);
                indicatorView.addView(pointView);
            }
            //增加头尾
            if(imageViews.size() > 1){
                ImageView ivFirst = new ImageView(context);
                ImageView ivLast = new ImageView(context);
                ImageLoader.getInstance().displayImage(imageUrls.getLast(),ivLast);
                ImageLoader.getInstance().displayImage(imageUrls.getFirst(),ivFirst);
                imageViews.addFirst(ivFirst);
                imageViews.addLast(ivLast);
            }
            viewPagerAdapter.notifyDataSetChanged();
            if(imageViews.size() > 1){
                viewPager.setCurrentItem(1);
            }else{
                viewPager.setCurrentItem(0);
            }
        }
    }

    /**
     * 设置当前选中页面
     * @param position
     */
    private void setSelectPage(int position){
        for(int index=0; index < indicatorView.getChildCount();index++){
            if(position == index){
                indicatorView.getChildAt(index).setEnabled(true);
            }else{
                indicatorView.getChildAt(index).setEnabled(false);
            }
        }
    }

    /**
     * 设置图片的地址，从网络加载图片
     * @param imageUrls
     */
    public void addImageUrls(List<String> imageUrls) {
        if(this.imageUrls.size() > 1){//清除头尾
            this.imageUrls.removeFirst();
            this.imageUrls.removeLast();
        }
        this.imageUrls.addAll(imageUrls);
        if(this.imageUrls.size() >1){//增加头尾
            String first = this.imageUrls.getFirst();
            this.imageUrls.addFirst(this.imageUrls.getLast());
            this.imageUrls.addLast(first);
        }
        createImageView(imageUrls);
    }

    /**
     * 清空所有数据
     */
    public void clearAllData(){
        this.imageUrls.clear();
        this.imageViews.clear();
        this.indicatorView.removeAllViews();
    }

    /**
     * 获取ViewPager对象
     * @return
     */
    public ViewPager getViewPager() {
        return viewPager;
    }

    /**
     * 获取指示器
     * @return
     */
    public LinearLayout getIndicatorView() {
        return indicatorView;
    }

    /**
     * 设置点击ViewPager中的ImageView的监听
     * @param viewPagerClick
     */
    public void setViewPagerClick(ViewPagerClick viewPagerClick) {
        this.viewPagerClick = viewPagerClick;
    }

    public interface ViewPagerClick{
        public void viewPagerOnClick(View view);
    }
}

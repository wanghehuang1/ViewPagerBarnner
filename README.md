# ViewPagerBarnner
该工程主要是封装了ViewPager以及指示器，形成一个统一的滚动条，方便使用。

自定义属性：

	<declare-styleable name="ViewPager">
        <attr name="containerHeight" format="dimension"/>		<!-- 容器的高度 -->
        <attr name="indicatorSize" format="dimension"/>  		<!-- 指示器大小 -->
        <attr name="indicatorDrawable" format="reference"/>	    <!-- 指示器背景，可以说drawable对象 -->
        <attr name="indicatorInterval" format="dimension"/>		<!-- 指示器之间的间隔 -->
        <attr name="indicatorBigSize" format="dimension"/>		<!-- 指示器选中时大小 -->
        <attr name="indicatorBackgroud" format="reference"/>	<!-- 指示器背景色 -->
        <attr name="indicatorMarginBottom" format="dimension"/>	<!-- 指示器距离底部 -->
    </declare-styleable>

###一、ViewPagerBarnnerUrl

	<com.dsw.library.ViewPagerBarnnerUrl
	    android:id="@+id/viewPager"
	    android:layout_height="160dp"
	    android:layout_width="match_parent"
	    viewpager:indicatorSize="8dp"
	    viewpager:indicatorInterval="10dp"
	    viewpager:containerHeight="15dp"
	    viewpager:indicatorMarginBottom="0dp"
	    viewpager:indicatorBackgroud="@mipmap/head_sbox"
	    viewpager:indicatorDrawable="@drawable/indicator_backgroud"/>

Activity：

	public class ViewPagerUrl extends AppCompatActivity {
	    private List<String> urlString;
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_view_pager_banner_url);
	        ViewPagerBarnnerUrl viewPagerBarnnerUrl = (ViewPagerBarnnerUrl) findViewById(R.id.viewPager);
	        urlString = new ArrayList<String>();
	        urlString.add("http://g.hiphotos.baidu.com/image/h%3D360/sign=0c819fc1efc4b7452b94b110fffe1e78/58ee3d6d55fbb2fb45c241fa4b4a20a44723dc68.jpg");
	        urlString.add("http://f.hiphotos.baidu.com/image/h%3D360/sign=f6007fc08301a18befeb1449ae2d0761/8644ebf81a4c510fa42d1bf66459252dd52aa575.jpg");
	        urlString.add("http://b.hiphotos.baidu.com/image/h%3D360/sign=593406224334970a58731629a5cbd1c0/d043ad4bd11373f073e5a192a60f4bfbfaed04e1.jpg");
	        urlString.add("http://c.hiphotos.baidu.com/image/h%3D360/sign=3d6f219c442309f7f86fab14420e0c39/30adcbef76094b36de8a2fe5a1cc7cd98d109d99.jpg");
	        urlString.add("http://f.hiphotos.baidu.com/image/h%3D360/sign=068c6bf8b54543a9ea1bfcca2e168a7b/3c6d55fbb2fb4316d8b7cf7622a4462309f7d316.jpg");
	        urlString.add("http://f.hiphotos.baidu.com/image/h%3D360/sign=442733c30afa513d4eaa6ad80d6c554c/cb8065380cd791234275326baf345982b2b7801c.jpg");
	        viewPagerBarnnerUrl.addImageUrls(urlString);
	        viewPagerBarnnerUrl.setViewPagerClick(new ViewPagerBarnnerUrl.ViewPagerClick() {
	
	            @Override
	            public void viewPagerOnClick(View view) {
	                Toast.makeText(ViewPagerUrl.this, "click:" + view.getTag().toString(), Toast.LENGTH_SHORT).show();
	            }
	        });
	    }
	}


###二、ViewPagerBarnnerBitmap

	  <com.dsw.library.ViewPagerBarnnerBitmap
        android:id="@+id/viewPager"
        android:layout_height="160dp"
        android:layout_width="match_parent"
        viewpager:indicatorSize="8dp"
        viewpager:indicatorInterval="10dp"
        viewpager:containerHeight="15dp"
        viewpager:indicatorMarginBottom="0dp"
        viewpager:indicatorBackgroud="@mipmap/head_sbox"
        viewpager:indicatorDrawable="@drawable/indicator_backgroud"/>

Activity:

	public class ViewPagerBitmap extends AppCompatActivity {
	    private List<Bitmap> bitmaps;
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_view_pager_banner_bitmap);
	        ViewPagerBarnnerBitmap viewPagerBarnnerBitmap = (ViewPagerBarnnerBitmap) findViewById(R.id.viewPager);
	        bitmaps = new ArrayList<Bitmap>();
	        bitmaps.add(BitmapFactory.decodeResource(getResources(), R.mipmap.first));
	        bitmaps.add(BitmapFactory.decodeResource(getResources(), R.mipmap.second));
	        bitmaps.add(BitmapFactory.decodeResource(getResources(), R.mipmap.third));
	        bitmaps.add(BitmapFactory.decodeResource(getResources(), R.mipmap.four));
	        bitmaps.add(BitmapFactory.decodeResource(getResources(), R.mipmap.five));
	        bitmaps.add(BitmapFactory.decodeResource(getResources(), R.mipmap.six));
	
	        viewPagerBarnnerBitmap.addImageUrls(bitmaps);
	        viewPagerBarnnerBitmap.setViewPagerClick(new ViewPagerBarnnerBitmap.ViewPagerClick() {
	
	            @Override
	            public void viewPagerOnClick(View view) {
	                Toast.makeText(ViewPagerBitmap.this, "click:" + view.getTag().toString(), Toast.LENGTH_SHORT).show();
	            }
	        });
	    }
	}


效果图：

![demo](http://yun.baidu.com/share/link?shareid=674977277&uk=2502067285)
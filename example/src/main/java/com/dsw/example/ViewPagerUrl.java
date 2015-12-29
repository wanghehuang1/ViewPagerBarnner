package com.dsw.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.dsw.library.ViewPagerBarnnerUrl;

import java.util.ArrayList;
import java.util.List;

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

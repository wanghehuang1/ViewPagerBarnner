package com.dsw.example;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.dsw.library.ViewPagerBarnnerBitmap;

import java.util.ArrayList;
import java.util.List;

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

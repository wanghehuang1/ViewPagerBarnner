package com.dsw.example;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_viewpagerbitmap).setOnClickListener(this);
        findViewById(R.id.btn_viewpagerurl).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()){
            case R.id.btn_viewpagerbitmap:
                intent = new Intent(this, ViewPagerBitmap.class);
                break;
            case R.id.btn_viewpagerurl:
                intent = new Intent(this, ViewPagerUrl.class);
                break;
        }
        startActivity(intent);
    }
}

package com.example.asus.gouwuche2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import adapter.Fragment_viewpager;
import fragment.Fragment1;
import fragment.Fragment2;
import fragment.Fragment3;
import fragment.Fragment4;

public class ZhuActivity extends AppCompatActivity {
    private TabLayout tab;
    private ViewPager vp;
    private List<String> list;
    private List<Fragment> frags;
    private Fragment f1;
    private Fragment f2;
    private Fragment f3;
    private Fragment f4;
    private Fragment_viewpager fv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhu);
        initview();
        initdata();
    }
    private void initdata() {
        frags=new ArrayList<>();
        f1=new Fragment1();
        f2=new Fragment2();
        f3=new Fragment3();
        f4=new Fragment4();
        frags.add(f1);
        frags.add(f2);
        frags.add(f3);
        frags.add(f4);
        //viewpager的适配
        //tablayout加载viewpager
        tab.setupWithViewPager(vp);
        fv=new Fragment_viewpager(getSupportFragmentManager(),this,frags);
        vp.setAdapter(fv);


    }

    private void initview() {
        tab= (TabLayout) findViewById(R.id.tab);
        vp= (ViewPager) findViewById(R.id.vp);
    }
}

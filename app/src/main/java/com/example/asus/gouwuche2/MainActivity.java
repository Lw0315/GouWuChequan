package com.example.asus.gouwuche2;


import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import common.Circle;

public class MainActivity extends AppCompatActivity {
     private Circle yuan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();
    }

    private void initview() {
        yuan= (Circle) findViewById(R.id.yuan);
        // ObjectAnimator obj=ObjectAnimator.ofFloat(yuan,"tran");
        ObjectAnimator obj=ObjectAnimator.ofFloat(yuan,"translationY", 0f,180f);
        obj.setDuration(4000);
        obj.start();
        obj.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
               Intent intent=new Intent(MainActivity.this,ZhuActivity.class);
                startActivity(intent);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }


}

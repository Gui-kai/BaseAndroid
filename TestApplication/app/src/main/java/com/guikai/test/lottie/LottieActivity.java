package com.guikai.test.lottie;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.guikai.test.R;

public class LottieActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lottie);
        LottieAnimationView animationView = findViewById(R.id.lottie);
        animationView.setAnimation("hello.json");
        animationView.loop(true);
    }
}

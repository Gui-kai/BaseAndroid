package com.guikai.test.mvp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.guikai.test.mvp.presenter.MyPresenterImp;

public class MyActivity extends AppCompatActivity implements ShowImgView {
    //UI显示
    //逻辑操作

    MyPresenterImp myPresenterImp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(null);
        myPresenterImp = new MyPresenterImp();
        myPresenterImp.attachView(this);
    }

    @Override
    public void showImg(ImageView imageView) {
        myPresenterImp.showImage();
    }

    @Override
    public void showToast(String msg) {

    }
}

package com.guikai.test.mvp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.guikai.test.mvp.presenter.MainPresenterImp;

public class MVPActivity extends AppCompatActivity implements MainBaseView {

    MainPresenterImp presenterImp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenterImp = new MainPresenterImp();
        presenterImp.attachView(this);
        //点击登录  presenterImp.login(new User("name","pwd"));
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void loginSuccess(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void loginFailed(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        presenterImp.detachView();
        super.onDestroy();
    }
}

package com.guikai.test.mvp.presenter;

import android.text.TextUtils;

import com.guikai.test.mvp.model.User;
import com.guikai.test.mvp.view.MainBaseView;

public class MainPresenterImp implements MainPresenter {

    private MainBaseView baseView;

    @Override
    public void attachView(MainBaseView view) {
        baseView = view;
    }

    @Override
    public void detachView() {
        baseView = null;
    }

    @Override
    public void login(User user) {
        if (!TextUtils.isEmpty(user.getName()) && !TextUtils.isEmpty(user.getPwd())) {
            if (user.getName().equals("123456") && user.getPwd().equals("guikai")) {
                baseView.loginSuccess("Present执行登录成功");
            } else {
                baseView.loginFailed("登录失败");
            }
        } else {
            baseView.showToast("输入格式不对");
        }
    }
}

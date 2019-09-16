package com.guikai.test.mvp.view;

public interface MainBaseView extends BaseView{
    void loginSuccess(String msg);

    void loginFailed(String msg);
}

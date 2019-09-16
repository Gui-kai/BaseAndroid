package com.guikai.test.mvp.presenter;

import com.guikai.test.mvp.model.User;
import com.guikai.test.mvp.view.MainBaseView;

public interface MainPresenter extends BasePresenter<MainBaseView> {
    void login(User user);
}

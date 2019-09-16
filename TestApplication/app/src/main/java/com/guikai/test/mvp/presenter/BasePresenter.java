package com.guikai.test.mvp.presenter;

public interface BasePresenter<T> {

    void attachView(T v);

    void detachView();

}

package com.guikai.retrofit2;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Banner {

    @GET("/banner/json")
    Call<BannerBean> getBannerInfo();

}

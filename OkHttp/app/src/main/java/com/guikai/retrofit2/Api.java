package com.guikai.retrofit2;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {

    //get请求获取banner
    @GET("/banner/json")
    Call<BannerBean> getBannerInfo();

    //post提交登陆请求
    @FormUrlEncoded
    @POST("/user/login")
    Call<User> editUser(@Field("username") String user, @Field("password") String password);

}

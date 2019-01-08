package com.guikai.retrofit2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.guikai.okhttp2.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitAvtivity extends AppCompatActivity {

    EditText editText1;
    EditText editText2;
    private Api api;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        editText1 = findViewById(R.id.et_user);
        editText2 = findViewById(R.id.et_pass);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.wanandroid.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(Api.class);
    }


    public void begin(View view) {
        Call<BannerBean> call = api.getBannerInfo();
        call.enqueue(new Callback<BannerBean>() {
            @Override
            public void onResponse(Call<BannerBean> call, Response<BannerBean> response) {
                String mes1 = response.body().getData().get(0).getTitle();
                String mes2 = response.body().getData().get(0).getImagePath();
                Toast.makeText(RetrofitAvtivity.this,mes1,Toast.LENGTH_LONG).show();
                Toast.makeText(RetrofitAvtivity.this,mes2,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<BannerBean> call, Throwable t) {

            }
        });
    }

    public void post(View view) {
        String user = editText1.getText().toString();
        String pass = editText2.getText().toString();
        api.editUser(user,pass).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                int errorcode = response.body().getErrorCode();
                if (errorcode == 0) {
                    Toast.makeText(RetrofitAvtivity.this, "登陆成功",Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(RetrofitAvtivity.this,response.body().getErrorMsg(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });

    }
}

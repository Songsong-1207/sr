package com.example.exercise2;

import com.example.exercise2.bean.MyBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {
    String url = "http://news-at.zhihu.com/";

    @GET("api/4/news/hot")
    Observable<MyBean> getData();
}

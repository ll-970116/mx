package com.example.p6qz.api;

import com.example.p6qz.bean.RvBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {
    String baseURL = "https://www.wanandroid.com/";

    @GET("project/list/1/json?cid=294")
    Observable<RvBean> get();
}

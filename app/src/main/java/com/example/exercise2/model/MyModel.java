package com.example.exercise2.model;

import com.example.exercise2.ApiService;
import com.example.exercise2.MyCollback;
import com.example.exercise2.bean.MyBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyModel {
    public void getData(final MyCollback<MyBean> myCollback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        retrofit.create(ApiService.class).getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MyBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MyBean myBean) {
                        myCollback.succeed(myBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        myCollback.failed(e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}

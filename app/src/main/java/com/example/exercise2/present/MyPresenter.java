package com.example.exercise2.present;

import android.util.Log;

import com.example.exercise2.MyCollback;
import com.example.exercise2.bean.MyBean;
import com.example.exercise2.model.MyModel;
import com.example.exercise2.view.MyView;

public class MyPresenter {
    private static final String TAG = "TAG";
    private final MyModel myModel;
    private MyView view;

    public MyPresenter(MyView view) {
        this.view = view;
        myModel = new MyModel();
    }

    public void getData() {
        myModel.getData(new MyCollback<MyBean>() {
            @Override
            public void succeed(MyBean bean) {
                if (bean != null && bean.getRecent() != null){
                    view.setData(bean);
                }
            }

            @Override
            public void failed(String error) {
                Log.i(TAG, "failed: "+error);
            }
        });
    }
}

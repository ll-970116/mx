package com.example.p6qz.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.p6qz.R;
import com.example.p6qz.adapter.RvAdapter;
import com.example.p6qz.api.ApiService;
import com.example.p6qz.bean.RvBean;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class HomeFragment extends Fragment {


    private ArrayList<RvBean.DataBean.DatasBean> list;
    public RvAdapter rvAdapter;
    public RecyclerView rv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initData() {

        //retrofit + rxjave 网络加载
        new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ApiService.baseURL)
                .build()
                .create(ApiService.class)
                .get()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RvBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RvBean rvBean) {
                        list.addAll(rvBean.getData().getDatas());
                        rvAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initView(View view) {
        //找控件
        rv = view.findViewById(R.id.recyclerview_home);
        //Adapter 数据源
        list = new ArrayList<>();
        //布局管理器
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        //创建适配器
        rvAdapter = new RvAdapter(list, getContext());
        //绑定适配器
        rv.setAdapter(rvAdapter);
        //列表间隔线
        rv.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
    }
}
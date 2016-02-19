package com.litc.rxtest.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.Toast;

import com.litc.rxtest.R;
import com.litc.rxtest.swrltest.TestAdapter;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Observer;

/**
 * Created by Traburiss on 2016/2/19.
 * Version 1.0
 * Description
 */
public class SecondActivity extends AppCompatActivity{

    @Bind(R.id.btn_second) Button btn_second;
    @Bind(R.id.srl_second) SwipeRefreshLayout srl_second;
    @Bind(R.id.rlv_second) RecyclerView rlv_second;

    private Handler handler;
    private TestAdapter adapter;
    private int[] colors;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);
        initDate();
        initView();
        initEven();
    }

    private void initDate() {
        handler = new Handler();
        adapter = new TestAdapter(new ArrayList<>(), this);
        colors = new int[]{
                ContextCompat.getColor(this, R.color.colorRefreshBlue),
                ContextCompat.getColor(this, R.color.colorRefreshRed),
                ContextCompat.getColor(this, R.color.colorRefreshGreen),
                ContextCompat.getColor(this, R.color.colorRefreshYellow)};
    }

    private void initView() {

        rlv_second.setLayoutManager(new LinearLayoutManager(this));
        rlv_second.setAdapter(adapter);
        srl_second.setColorSchemeColors(colors);
    }

    private void initEven() {

        srl_second.setOnRefreshListener(()-> {
            adapter.getStrings().clear();
            adapter.notifyDataSetChanged();
            handler.postDelayed(() -> {
                srl_second.setRefreshing(false);
                String str = "";
                getString().subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        adapter.getStrings().add("加载出错");
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onNext(String s) {
                        adapter.getStrings().add(s);
                    }
                });
            }, 4000);
        });

        btn_second.setOnClickListener((view)->Toast.makeText(this, R.string.lambda_test, Toast.LENGTH_SHORT).show());
    }

    private Observable<String> getString(){
        return Observable.create(subscriber -> {
            for (int i = 0; i < 100; i++){
                subscriber.onNext(i+"");
            }
            subscriber.onCompleted();
        });
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
        ButterKnife.unbind(this);
    }
}

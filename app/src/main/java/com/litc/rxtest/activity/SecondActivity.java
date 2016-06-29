package com.litc.rxtest.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.jakewharton.rxbinding.support.v7.widget.RxRecyclerView;
import com.jakewharton.rxbinding.support.v7.widget.RxToolbar;
import com.jakewharton.rxbinding.view.RxView;
import com.litc.rxtest.R;
import com.litc.rxtest.activity.adapter.SecondAdapter;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;

public class SecondActivity extends AppCompatActivity {

    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.rv_second_test) RecyclerView rv_second_test;
    @Bind(R.id.fab) FloatingActionButton fab;

    private ArrayList<String> list;
    private SecondAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);

        initData();
        initView();
        initEven();
    }

    private void initData(){

        list = new ArrayList<>();
        getList("hello world").subscribe(s -> list.add(s));
    }

    private void initView(){

        adapter = new SecondAdapter(this, list);
        rv_second_test.setLayoutManager(new LinearLayoutManager(this));
        rv_second_test.setAdapter(adapter);
    }

    private void initEven(){

        setSupportActionBar(toolbar);

        RxToolbar.navigationClicks(toolbar).subscribe(aVoid -> finish());

        RxRecyclerView.scrollEvents(rv_second_test).subscribe(recyclerViewScrollEvent -> {

            if (recyclerViewScrollEvent.dy() < 0){

                fab.show();
            }else{

                fab.hide();
            }
        });

        RxView.clicks(fab).subscribe(aVoid -> Toast.makeText(this,"hello world", Toast.LENGTH_SHORT).show());
    }

    private Observable<String> getList(String s){

        ArrayList<String> from_list = new ArrayList<>();
        for (int i = 0; i < 100; i++){

            from_list.add(i + ":" + s);
        }
        return Observable.from(from_list);
    }
}

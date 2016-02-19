package com.litc.rxtest.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.litc.rxtest.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import zrc.widget.SimpleFooter;
import zrc.widget.SimpleHeader;
import zrc.widget.ZrcListView;

/**
 * Created by Traburiss on 2016/2/19.
 * Version 1.0
 * Description
 */
public class ThreeActiviity extends AppCompatActivity{

    @Bind(R.id.zr_three_list)
    ZrcListView zr_three_list;
    @Bind(R.id.btn_three)
    Button btn_three;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);
        ButterKnife.bind(this);
        initDate();
        initView();
        initEven();
    }

    private void initDate() {

    }

    private void initView() {
        SimpleHeader simpleHeader = new SimpleHeader(this);
        simpleHeader.setTextColor(ContextCompat.getColor(this, R.color.colorRefreshBlue));
        simpleHeader.setCircleColor(ContextCompat.getColor(this, R.color.colorRefreshGreen));
        zr_three_list.setHeadable(simpleHeader);

        SimpleFooter simpleFooter = new SimpleFooter(this);
        simpleFooter.setCircleColor(ContextCompat.getColor(this, R.color.colorRefreshRed));
        zr_three_list.setFootable(simpleFooter);

    }

    private void initEven() {

    }
}

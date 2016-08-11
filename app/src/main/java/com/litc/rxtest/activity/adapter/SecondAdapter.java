package com.litc.rxtest.activity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.litc.rxtest.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author traburiss
 * @date 2016/6/29
 * @info RxTest
 * @desc
 */

public class SecondAdapter extends RecyclerView.Adapter<SecondAdapter.SecondAdapterHolder>{

    private Context context;
    private ArrayList<String> list;

    public SecondAdapter(Context context, ArrayList<String> list){

        this.context = context;
        this.list = list;
    }
    @Override
    public SecondAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Integer i = 1;
        return new SecondAdapterHolder(LayoutInflater.from(context).inflate(R.layout.item_second, null, false));
    }

    @Override
    public void onBindViewHolder(SecondAdapterHolder holder, int position) {

        RxTextView.text(holder.textView).call(list.get(position));
    }

    @Override
    public int getItemCount() {
        if (null == list) return 0;
        return list.size();
    }

    public class SecondAdapterHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.textView) TextView textView;

        public SecondAdapterHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

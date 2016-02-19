package com.litc.rxtest.swrltest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.litc.rxtest.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by Traburiss on 2016/2/19.
 * Version 1.0
 * Description
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class TestAdapter extends RecyclerView.Adapter<TestAdapter.TestViewHolder>{
    List<String> strings;
    Context mContext;

    @Override
    public TestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TestViewHolder(LayoutInflater.from(mContext).inflate(R.layout.adapter_test, parent, false));
    }

    @Override
    public void onBindViewHolder(TestViewHolder holder, int position) {
        holder.textView.setText(strings.get(position));
    }

    @Override
    public int getItemCount() {
        return strings == null ? 0 : strings.size();
    }

    public class TestViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.tv_adaptertest_label)
        public TextView textView;

        public TestViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

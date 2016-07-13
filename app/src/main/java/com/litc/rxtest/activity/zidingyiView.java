package com.litc.rxtest.activity;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;

import com.litc.rxtest.R;

/**
 * @author traburiss
 * @date 2016/6/30
 * @info RxTest
 * @desc
 */

public class zidingyiView extends TextView{

    public zidingyiView(Context context) {
        super(context);
    }

    public zidingyiView(Context context, AttributeSet attributeSet){

        super(context, attributeSet);
        TypedArray a = context.obtainStyledAttributes(attributeSet, R.styleable.zidingyi);
        String s = a.getString(R.styleable.zidingyi_zzz);
        setText(s);
    }
}

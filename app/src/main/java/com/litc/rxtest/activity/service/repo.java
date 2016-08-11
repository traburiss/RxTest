package com.litc.rxtest.activity.service;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author traburiss
 * @date 2016/7/18
 * @info RxTest
 * @desc
 */

public class repo {

    public String error_info;
    public ArrayList<HashMap<String,Object>> result;

    public String getError_info() {
        return error_info;
    }

    public void setError_info(String error_info) {
        this.error_info = error_info;
    }

    public ArrayList<HashMap<String,Object>> getResult() {
        return result;
    }

    public void setResult(ArrayList<HashMap<String,Object>> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "error_info:" + error_info + "\tresult" + result.toString();
    }
}

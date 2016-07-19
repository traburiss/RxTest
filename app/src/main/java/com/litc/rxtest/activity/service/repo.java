package com.litc.rxtest.activity.service;

import java.util.HashMap;

/**
 * @author traburiss
 * @date 2016/7/18
 * @info RxTest
 * @desc
 */

public class repo {

    public String send_params;
    public HashMap<String, Object> result;

    public String getSend_params() {
        return send_params;
    }

    public void setSend_params(String send_params) {
        this.send_params = send_params;
    }

    public HashMap<String, Object> getResult() {
        return result;
    }

    public void setResult(HashMap<String, Object> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "result:" + result.toString() + "\tsendparams" + send_params;
    }
}

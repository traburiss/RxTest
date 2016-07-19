package com.litc.rxtest.activity.service;

import java.util.HashMap;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author traburiss
 * @date 2016/7/13
 * @info RxTest
 * @desc
 */

public interface AppServiceTest {

    @GET("/app_service")
    Observable<HashMap<String, Object>> get_info(@Query("function_code") String function_code, @Query("send_params") String send_params);
}

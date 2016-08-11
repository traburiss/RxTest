package com.litc.rxtest.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;
import com.litc.rxtest.R;
import com.litc.rxtest.activity.service.AppServiceTest;
import com.litc.rxtest.activity.service.repo;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.textView) TextView textView1;
    @BindView(R.id.textView2) TextView textView2;

    @BindView(R.id.button1) Button button1;
    @BindView(R.id.button2) Button button2;
    @BindView(R.id.button) Button button;
    @BindView(R.id.button5) Button button5;
    @BindView(R.id.button6) Button button6;
    @BindView(R.id.button3) Button button3;

    @BindView(R.id.editText) EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        initDate();
        initView();
        initEven();
    }

    private void initDate() {

//        HashMap<String, Object> hashMap = new HashMap<>();
//        hashMap.put("a", "a");
//        ArrayList<HashMap<String,String>> list = (ArrayList<HashMap<String, String>>) hashMap.get("a");
//        HashMap<String,String> item = list.get(0);
    }

    private void initView() {

        textView1.setText(R.string.butter_knife_test);

    }

    private void initEven() {

        RxView.clicks(button1).subscribe(this::Button2Click);

        button2.setOnClickListener(view -> {

            query(" litc")
                    .flatMap(Observable::from)
                    .flatMap(this::getTitle)
                    .filter(s -> !s.contains("hello"))
                    .take(100)
                    .doOnNext(s -> textView2.setText(s))
                    .subscribe(s -> Toast.makeText(this, s, Toast.LENGTH_SHORT).show());
        });

        button.setOnClickListener(view -> {

            queryNums()
                    .flatMap(Observable::from)
                    .flatMap(this::Turn2Num)
                    .flatMap(this::add2String)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new mySubscriber<String>());
        });

        RxView.clicks(button5)
                .subscribe(this::button5Click);

        RxView.clicks(button6).subscribe(new Observer<Void>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Void aVoid) {

                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });

        RxView.clicks(button3).subscribe(aVoid -> {

//            String send_params = "\"a\"";
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("PAGE_SIZE", "1");
                jsonObject.put("PAGE_NUM", "1");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            Retrofit retrofit= new Retrofit.Builder()
                    .baseUrl("http://172.26.131.200:8000")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            AppServiceTest service = retrofit.create(AppServiceTest.class);

//            get_info("1", send_params);
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(s -> Toast.makeText(this, s, Toast.LENGTH_SHORT).show());
            service
                    .get_info("1", jsonObject.toString())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<repo>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                            e.printStackTrace();
                            Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onNext(repo repo) {

                            if(repo.getError_info().equalsIgnoreCase("success")){

                                Toast.makeText(MainActivity.this, repo.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
//                    .subscribe(new Subscriber<ArrayList>() {
//                        @Override
//                        public void onCompleted() {
//
//                        }
//
//                        @Override
//                        public void onError(Throwable e) {
//
//                            Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
//                        }
//
//                        @Override
//                        public void onNext(ArrayList list) {
//
//                            Toast.makeText(MainActivity.this, list.toString(), Toast.LENGTH_SHORT).show();
//                        }
//                    });


        });

    }

    private void Button2Click(Void v){

        Observable.just(1234567890)
                .map(s -> s + " litc")
                .subscribe(s -> Toast.makeText(this, s, Toast.LENGTH_SHORT).show());

    }

    private void get_info(String function, String send_params){

//        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://172.26.131.196:8000").build();
//
//        AppServiceTest appServiceTest = retrofit.create(AppServiceTest.class);
//        Call<repo> result = appServiceTest.get_info(function, send_params);

//        Retrofit retrofit= new Retrofit.Builder()
//                .baseUrl("http://172.26.131.169:8000")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        AppServiceTest service = retrofit.create(AppServiceTest.class);
//        Call<HashMap<String,Object>> model = service.get_info("1", "1");

//        model.enqueue(new Callback<repo>() {
//            @Override
//            public void onResponse(Call<repo> call, Response<repo> response) {
//
////                return Observable.just(response.body().getLogin());
//                Toast.makeText(MainActivity.this, response.body().toString(), Toast.LENGTH_SHORT).show();
//                Log.i("Retrofit", "success: " + response.body().toString());
//            }
//
//            @Override
//            public void onFailure(Call<repo> call, Throwable t) {
//
//                Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
//                Log.i("Retrofit", "error" + t.toString());
////                return Observable.just("error");
//            }
//        });
    }

    private Observable<ArrayList<String>> query(String s){

        ArrayList<String> list = new ArrayList<String>();
        Observable.from(new String[]{s + " hello world", s + " is good", s + " 你好世界", s + " 爽歪歪", s + "世界末日", s + "hello e", s + "abc"}).subscribe(list::add);
        return Observable.just(list);
    }

    private Observable<String> getTitle(String s){

        s += " title";
        return Observable.just(s);
    }

    private Observable<ArrayList<String>> queryNums(){

        ArrayList<String> list = new ArrayList<String>();
        Observable.from(new String[]{"1", "3", "5", "8", "10"}).subscribe(list::add);
        return Observable.just(list);
    }

    private Observable<ArrayList<String>> queryNums(Void v){

        ArrayList<String> list = new ArrayList<String>();
        Observable.from(new String[]{"1", "3", "5", "8", "10"}).subscribe(list::add);
        return Observable.just(list);
    }

    private Observable<Integer> Turn2Num(String s){

        return Observable.just(Integer.valueOf(s));
    }

    private Observable<String> add2String(int num){

        int m = 0;
        for(long i = 0; i < 100000000; i++){

            m += i%2;
        }
        return Observable.just(String.valueOf(num*m));
    }

    private String dealWithString(String s){


        int m = 0;
        for(long i = 0; i < 100000000; i++){

            m += i%2;
        }
        return s + m;
    }

    private Observable<String> newDealWithString(String s){

        return Observable
                .defer(()->Observable.just(dealWithString(s)));
    }

    private void button5Click(Void v){


        newDealWithString(editText.getText().toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new mySubscriber<>());
    }

    private class mySubscriber<T> extends Subscriber<T>{

        @Override
        public void onCompleted() {

            Toast.makeText(MainActivity.this, "finish", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(Throwable e) {

            Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNext(T t) {

            dealWithT(t);
        }

        private void dealWithT(T t){

            if (t.getClass().isAssignableFrom(String.class)){

                Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }else{

                Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}

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

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.textView) TextView textView1;
    @Bind(R.id.textView2) TextView textView2;

    @Bind(R.id.button1) Button button1;
    @Bind(R.id.button2) Button button2;
    @Bind(R.id.button) Button button;
    @Bind(R.id.button5) Button button5;
    @Bind(R.id.button6) Button button6;

    @Bind(R.id.editText) EditText editText;

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

    }

    private void Button2Click(Void v){

        Observable.just(1234567890)
                .map(s -> s + " litc")
                .subscribe(s -> Toast.makeText(this, s, Toast.LENGTH_SHORT).show());

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

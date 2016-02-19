package com.litc.rxtest.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.litc.rxtest.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import com.litc.rxtest.lombok.LombokTest;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.button) Button button;
    @Bind(R.id.textView) TextView textView1;
    @Bind(R.id.textView2) TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        textView1.setText(R.string.butter_knife_test);
        button.setOnClickListener((view)-> Toast.makeText(this, R.string.lambda_test, Toast.LENGTH_SHORT).show());

        LombokTest lombokTest = new LombokTest(10,0.1);
        textView2.setText(lombokTest.toString());
    }
}

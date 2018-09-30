package com.pals.isitrainingthere;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

public class Result extends AppCompatActivity {

    TextView tvResult;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);

        tvResult = findViewById(R.id.tv_result);
        Intent recIntent = getIntent();
        String result = recIntent.getStringExtra("answer");
        tvResult.setText(result);
    }
}

package com.stu.birthday_card;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class M_Tran2 extends AppCompatActivity {

    EditText dollarText,euroText,wonText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mtran2);
        Intent intent = getIntent();
        float dollar2 = intent.getFloatExtra("dollar_rate_key",0.0f);
        float euro2 = intent.getFloatExtra("euro_rate_key",0.0f);
        float won2 = intent.getFloatExtra("won_rate_key",0.0f);

        Log.i("111","dollar2:"+dollar2);
        Log.i("111","euro2:"+euro2);
        Log.i("111","won2:"+won2);

        dollarText = findViewById(R.id.dollar2);
        euroText = findViewById(R.id.euro2);
        wonText = findViewById(R.id.won2);

        dollarText.setText(String.valueOf(dollar2));
        euroText.setText(String.valueOf(euro2));
        wonText.setText(String.valueOf(won2));

    }

    public void save(View btn){

        Log.i("222","save:");

        float dollarRate = Float.parseFloat(dollarText.getText().toString());
        float euroRate = Float.parseFloat(euroText.getText().toString());
        float wonRate = Float.parseFloat(wonText.getText().toString());

        Intent first = getIntent();
        first.putExtra("dollar_key",dollarRate);
        first.putExtra("euro_key",euroRate);
        first.putExtra("won_key",wonRate);

        SharedPreferences sp = getSharedPreferences("myrate", Activity.MODE_PRIVATE);

        SharedPreferences.Editor editor = sp.edit();
        editor.putFloat("dollar_rate",dollarRate);
        editor.putFloat("euro_rate",euroRate);
        editor.putFloat("won_rate",wonRate);
        editor.apply();

        setResult(3,first);
        finish();

    }
}
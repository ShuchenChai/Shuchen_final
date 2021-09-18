package com.stu.birthday_card;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Point extends AppCompatActivity{

    int score_A=0;
    int score_B=0;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point);

    }
    public void clickb(View btn){
        Log.i("111","11111");


        if(btn.getId() == R.id.btn3){
            Log.i("111","11111");
            score_A = score_A + 3;
        }
        else if(btn.getId() == R.id.btn2){
            score_A = score_A + 2;
        }
        else if(btn.getId() == R.id.btn1){
            score_A = score_A + 1;
        }
        else if(btn.getId() == R.id.btn13){
            score_B = score_B + 3;
        }
        else if(btn.getId() == R.id.btn12){
            score_B = score_B + 2;
        }
        else if(btn.getId() == R.id.btn11){
            score_B = score_B + 1;
        }
        else{
            score_A = 0;
            score_B = 0;
        }
        TextView out1 = findViewById(R.id.text1);
        TextView out2 = findViewById(R.id.text2);
        out1.setText(String.valueOf(score_A));
        out2.setText(String.valueOf(score_B));

    }
}
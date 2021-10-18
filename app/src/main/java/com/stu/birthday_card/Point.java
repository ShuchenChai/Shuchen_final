package com.stu.birthday_card;

import androidx.annotation.NonNull;
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

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt("score_A",score_A);
        outState.putInt("score_B",score_B);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        score_A = savedInstanceState.getInt("score_A",0);
        score_B = savedInstanceState.getInt("score_B",0);
        showScore();
    }

    public void clickb(View btn){
        if(btn.getId() == R.id.btn3){
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
        showScore();
    }
    public void showScore(){
        TextView out1 = findViewById(R.id.text1);
        TextView out2 = findViewById(R.id.text2);
        out1.setText(String.valueOf(score_A));
        out2.setText(String.valueOf(score_B));
    }
}
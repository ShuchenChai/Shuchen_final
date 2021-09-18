package com.stu.birthday_card;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Tran extends AppCompatActivity implements View.OnClickListener{

    TextView out,adv;
    EditText edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tran);

        Button btn =  findViewById(R.id.btn1);
        btn.setOnClickListener(this);
    }

    public void onClick(View v){
        String con;
        Double bmi;
        EditText in_he = findViewById(R.id.in_he);
        EditText in_we = findViewById(R.id.in_we);
        if(in_he==null|| in_we ==null){
            con = "请输入正确数值！";
        }
        else {
            Double he = Double.parseDouble(in_he.getText().toString());
            Double we = Double.parseDouble(in_we.getText().toString());
            if (he == 0 || we == 0) {
                con = "请输入正确数值！";
            } else {
                bmi = we / (he * he);
                Double m = Double.parseDouble(String.format("%.2f", bmi));
                con = m.toString();
                adv = findViewById(R.id.advise);
                if (bmi >= 28) {
                    adv.setText("健康建议：肥胖，建议多多运动");
                } else if (bmi > 22.9) {
                    adv.setText("健康建议：过重，建议多运动");
                } else if (bmi >= 18.5) {
                    adv.setText("健康建议：正常，建议坚持运动");
                } else
                    adv.setText("健康建议：偏瘦，建议吃点");
            }
        }
        out = findViewById(R.id.output);
        out.setText("BMI："+ con);
    }
}
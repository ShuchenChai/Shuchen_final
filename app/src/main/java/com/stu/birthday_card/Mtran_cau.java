package com.stu.birthday_card;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Mtran_cau extends AppCompatActivity {
    EditText cinput;
    TextView crate;
    TextView cout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mtran_cau);

        cout = findViewById(R.id.cout);
        cinput = findViewById(R.id.cinput);
        TextView cname = findViewById(R.id.cname);
        crate = findViewById(R.id.crate);
        Intent intent = getIntent();
        String type = intent.getStringExtra("title");
        String rate = intent.getStringExtra("detail");
        float rate2 = 100/Float.parseFloat(rate);
        cname.setText(type);
        crate.setText(String.valueOf(rate2));

    }

    public void csub(View btn){
        float cot = 0.0f;
        float rat = Float.parseFloat(crate.getText().toString());
        if(TextUtils.isEmpty(cinput.getText().toString())){
            Toast.makeText(getApplicationContext(),"输入不能为空",Toast.LENGTH_SHORT).show();
        }
        else {
            float cin = Float.parseFloat(cinput.getText().toString());
            cot = cin*rat;
            }
        cout.setText(String.valueOf(cot));
        }
    }
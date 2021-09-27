package com.stu.birthday_card;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class M_Tran extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mtran);
    }


    public void click_m(View btn){
        double out = 0;
        TextView out_m = findViewById(R.id.out_m);
        EditText in_Rmb = findViewById(R.id.in_Rmb);
        if(TextUtils.isEmpty(in_Rmb.getText().toString())){
            Toast.makeText(getApplicationContext(),"输入不能为空",Toast.LENGTH_LONG).show();
        }
        else {
            Double Rmb = Double.parseDouble(in_Rmb.getText().toString());
            if (btn.getId() == R.id.Dollar) {
                out = Rmb*0.1548;
            }
            else if (btn.getId() == R.id.Euro) {
                out = Rmb*0.1324;
            }
            else{
                out = Rmb*182;
            }

        }
        out_m.setText(String.valueOf(out));

    }

}
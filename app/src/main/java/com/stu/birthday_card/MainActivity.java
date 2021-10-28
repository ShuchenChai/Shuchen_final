package com.stu.birthday_card;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openAll(View btn){
        Intent getall = new Intent(this,GetAll.class);
        startActivity(getall);
    }

    public void openMy(View btn){
        Intent ShowMyall = new Intent(this,ShowMyall.class);
        startActivity(ShowMyall);
    }
}


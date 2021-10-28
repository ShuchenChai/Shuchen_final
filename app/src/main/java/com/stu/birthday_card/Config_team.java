package com.stu.birthday_card;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Config_team extends AppCompatActivity {


    EditText Cname1,Cname2,Cname3,Cname4,Cname5;

    EditText Clevel1,Clevel2,Clevel3,Clevel4,Clevel5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_team);
        Intent intent = getIntent();
        String cname1 = intent.getStringExtra("Cname1");
        String cname2 = intent.getStringExtra("Cname2");
        String cname3 = intent.getStringExtra("Cname3");
        String cname4 = intent.getStringExtra("Cname4");
        String cname5 = intent.getStringExtra("Cname5");

        String clevel1 = intent.getStringExtra("Clevel1");
        String clevel2 = intent.getStringExtra("Clevel2");
        String clevel3 = intent.getStringExtra("Clevel3");
        String clevel4 = intent.getStringExtra("Clevel4");
        String clevel5 = intent.getStringExtra("Clevel5");

        Cname1 = findViewById(R.id.Cname1);
        Cname2 = findViewById(R.id.Cname2);
        Cname3 = findViewById(R.id.Cname3);
        Cname4 = findViewById(R.id.Cname4);
        Cname5 = findViewById(R.id.Cname5);

        Clevel1 = findViewById(R.id.Clevel1);
        Clevel2 = findViewById(R.id.Clevel2);
        Clevel3 = findViewById(R.id.Clevel3);
        Clevel4 = findViewById(R.id.Clevel4);
        Clevel5 = findViewById(R.id.Clevel5);

        Cname1.setText(cname1);
        Cname2.setText(cname2);
        Cname3.setText(cname3);
        Cname4.setText(cname4);
        Cname5.setText(cname5);

        Clevel1.setText(clevel1);
        Clevel2.setText(clevel2);
        Clevel3.setText(clevel3);
        Clevel4.setText(clevel4);
        Clevel5.setText(clevel5);

    }

    public void save(View btn){
        String cname1 = Cname1.getText().toString();
        String cname2 = Cname2.getText().toString();
        String cname3 = Cname3.getText().toString();
        String cname4 = Cname4.getText().toString();
        String cname5 = Cname5.getText().toString();

        String clevel1 = Clevel1.getText().toString();
        String clevel2 = Clevel2.getText().toString();
        String clevel3 = Clevel3.getText().toString();
        String clevel4 = Clevel4.getText().toString();
        String clevel5 = Clevel5.getText().toString();

        Intent first = getIntent();
        first.putExtra("Cname1",cname1);
        first.putExtra("Cname2",cname2);
        first.putExtra("Cname3",cname3);
        first.putExtra("Cname4",cname4);
        first.putExtra("Cname5",cname5);

        first.putExtra("Clevel1",clevel1);
        first.putExtra("Clevel2",clevel2);
        first.putExtra("Clevel3",clevel3);
        first.putExtra("Clevel4",clevel4);
        first.putExtra("Clevel5",clevel5);

        SharedPreferences sp = getSharedPreferences("Config_team", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        editor.putString("Cname1",cname1);
        editor.putString("Cname2",cname2);
        editor.putString("Cname3",cname3);
        editor.putString("Cname4",cname4);
        editor.putString("Cname5",cname5);

        editor.putString("Clevel1",clevel1);
        editor.putString("Clevel2",clevel2);
        editor.putString("Clevel3",clevel3);
        editor.putString("Clevel4",clevel4);
        editor.putString("Clevel5",clevel5);

        editor.apply();

        setResult(3,first);
        finish();

    }
}
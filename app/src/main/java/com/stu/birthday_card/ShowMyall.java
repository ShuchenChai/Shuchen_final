package com.stu.birthday_card;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

public class ShowMyall extends AppCompatActivity {

    TextView Cname1,Cname2,Cname3,Cname4,Cname5;

    TextView Clevel1,Clevel2,Clevel3,Clevel4,Clevel5;

    String cname1,cname2,cname3,cname4,cname5;

    String clevel1;
    String clevel2;
    String clevel3;
    String clevel4;
    String clevel5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_myall);
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

        SharedPreferences sharedPreferences = getSharedPreferences("Config_team", Activity.MODE_PRIVATE);
        PreferenceManager.getDefaultSharedPreferences(this);
        cname1 = sharedPreferences.getString("Cname1","");
        cname2 = sharedPreferences.getString("Cname2","");
        cname3 = sharedPreferences.getString("Cname3","");
        cname4 = sharedPreferences.getString("Cname4","");
        cname5 = sharedPreferences.getString("Cname5","");

        clevel1 = sharedPreferences.getString("Clevel1","");
        clevel2 = sharedPreferences.getString("Clevel2","");
        clevel3 = sharedPreferences.getString("Clevel3","");
        clevel4 = sharedPreferences.getString("Clevel4","");
        clevel5 = sharedPreferences.getString("Clevel5","");

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10 && resultCode == 3) {
            cname1 = data.getStringExtra("Cname1");
            cname2 = data.getStringExtra("Cname2");
            cname3 = data.getStringExtra("Cname3");
            cname4 = data.getStringExtra("Cname4");
            cname5 = data.getStringExtra("Cname5");

            clevel1 = data.getStringExtra("Clevel1");
            clevel2 = data.getStringExtra("Clevel2");
            clevel3 = data.getStringExtra("Clevel3");
            clevel4 = data.getStringExtra("Clevel4");
            clevel5 = data.getStringExtra("Clevel5");

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

    }

    public void Config_team(View btn){
        Intent config = new Intent(this,Config_team.class);

        config.putExtra("Cname1",cname1);
        config.putExtra("Cname2",cname2);
        config.putExtra("Cname3",cname3);
        config.putExtra("Cname4",cname4);
        config.putExtra("Cname5",cname5);

        config.putExtra("Clevel1",clevel1);
        config.putExtra("Clevel2",clevel2);
        config.putExtra("Clevel3",clevel3);
        config.putExtra("Clevel4",clevel4);
        config.putExtra("Clevel5",clevel5);

        startActivityForResult(config,10);

    }
}
package com.stu.birthday_card;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.prefs.PreferenceChangeEvent;

public class M_Tran extends AppCompatActivity implements Runnable{

    float dollarRate;
    float euroRate;
    float wonRate;

    TextView out_m;
    EditText in_Rmb;

    Handler handler;


    //0.1548f,0.1324f,182.551tf
    /*@Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.mymenu,menu);
        return true;
    }*/


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10 && resultCode == 3) {
            dollarRate = data.getFloatExtra("dollar_key", 0.1f);
            euroRate = data.getFloatExtra("euro_key", 0.1f);
            wonRate = data.getFloatExtra("won_key", 0.1f);

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mtran);

        out_m = findViewById(R.id.out_m);
        in_Rmb = findViewById(R.id.in_Rmb);

        SharedPreferences sharedPreferences = getSharedPreferences("myrate", Activity.MODE_PRIVATE);

        PreferenceManager.getDefaultSharedPreferences(this);
        dollarRate = sharedPreferences.getFloat("dollar_rate",0.0f);
        euroRate = sharedPreferences.getFloat("euro_rate",0.0f);
        wonRate = sharedPreferences.getFloat("won_rate",0.0f);

        handler = new Handler(Looper.myLooper()){
            @Override
            public void handleMessage(@NonNull Message msg){
                if(msg.what == 6){
                    String str = (String) msg.obj;
                    Log.i("000","handleMessage: get"+str);
                    out_m.setText(str);
                }
                super.handleMessage(msg);
            }
        };



        Thread t = new Thread(this);
        t.start();
    }

    public void click_m(View btn){
        float out_i;
        out_i =0.0f;

        if(TextUtils.isEmpty(in_Rmb.getText().toString())){
            Toast.makeText(getApplicationContext(),"输入不能为空",Toast.LENGTH_SHORT).show();
        }
        else {
            float Rmb = Float.parseFloat(in_Rmb.getText().toString());
            if (btn.getId() == R.id.Dollar) {
                out_i = Rmb*dollarRate;
            }
            else if (btn.getId() == R.id.Euro) {
                out_i = Rmb*euroRate;
            }
            else if (btn.getId() == R.id.Won) {
                out_i = Rmb*wonRate;
            }
        }
        out_m.setText(String.valueOf(out_i));

    }

    public void click_config(View btn){
        Intent config = new Intent(this,M_Tran2.class);
        config.putExtra("dollar_rate_key",dollarRate);
        config.putExtra("euro_rate_key",euroRate);
        config.putExtra("won_rate_key",wonRate);

        Log.i("000","dollar2:"+dollarRate);
        Log.i("000","euro2:"+euroRate);
        Log.i("000","won2:"+wonRate);

        startActivityForResult(config,10);

    }

    @Override
    public void run() {
        Log.i("run",".......");
        Message msg = handler.obtainMessage(6);
        msg.what = 6;
        msg.obj = "Hello from run";
        handler.sendMessage(msg);
        Log.i("iii","已发送");

        //只能在线程中运行网络访问
        URL url = null;
        try{
            url = new URL("https://www.boc.cn/sourcedb/whpj/index.html");
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            InputStream in = http.getInputStream();
            String html = inputStream2String(in);
            Log.i("333","run:html"+html);
        } catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private String inputStream2String(InputStream inputStream) throws IOException{
        final int bufferSize = 1024;
        final char[] buffer = new char[bufferSize];
        final StringBuilder out = new StringBuilder();
        Reader in = new InputStreamReader(inputStream,"utf-8");
        while(true){
            int rsz = in.read(buffer,0,buffer.length);
            if(rsz < 0)
                break;
            out.append(buffer,0,rsz);
        }
        return out.toString();
    }
}
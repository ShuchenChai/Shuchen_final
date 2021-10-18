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

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.prefs.PreferenceChangeEvent;

public class M_Tran extends AppCompatActivity implements Runnable{

    private static final String TAG = "M_Tran";

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
        String updateDate = sharedPreferences.getString("update_date","");
        dollarRate = sharedPreferences.getFloat("dollar_rate",0.0f);
        euroRate = sharedPreferences.getFloat("euro_rate",0.0f);
        wonRate = sharedPreferences.getFloat("won_rate",0.0f);

        Date today = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        final String todayStr = sdf.format(today);

        Log.i(TAG, "onCreate: updateDate = " + updateDate);
        Log.i(TAG, "onCreate: todayStr = "+ todayStr );

        handler = new Handler(Looper.myLooper()){
            @Override
            public void handleMessage(@NonNull Message msg){
                if(msg.what == 6){
                    String str = (String) msg.obj;
                    out_m.setText(str);
                }
                if(msg.what == 7){
                    String str = (String) msg.obj;
                    dollarRate = 100/Float.parseFloat(str);
                }
                if(msg.what == 8){
                    String str = (String) msg.obj;
                    euroRate = 100/Float.parseFloat(str);
                }
                if(msg.what == 9){
                    String str = (String) msg.obj;
                    wonRate = 100/Float.parseFloat(str);
                }
                super.handleMessage(msg);
                SharedPreferences sp = getSharedPreferences("myrate", Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putFloat("dollar_rate",dollarRate);
                editor.putFloat("euro_rate",euroRate);
                editor.putFloat("won_rate",wonRate);
                editor.putString("update_date",todayStr);
                editor.apply();
            }
        };



        if(!updateDate.equals(todayStr)){
            Log.i(TAG, "onCreate: 需要更新");
            Thread t = new Thread(this);
            t.start();
        }
        else{
            Log.i(TAG, "onCreate: 不需要更新");
        }
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

        Log.i(TAG,"dollar2:"+dollarRate);
        Log.i(TAG,"euro2:"+euroRate);
        Log.i(TAG,"won2:"+wonRate);

        startActivityForResult(config,10);

    }

    public void openlist(View btn){
        Intent list = new Intent(this,RateListActivity.class);
        startActivity(list);
    }

    @Override
    public void run() {
        Log.i("run",".......");


        //只能在线程中运行网络访问
//        URL url = null;
//        try{
//            url = new URL("https://www.boc.cn/sourcedb/whpj/index.html");
//            HttpURLConnection http = (HttpURLConnection) url.openConnection();
//            InputStream in = http.getInputStream();
//            String html = inputStream2String(in);
//            Log.i("333","run:html"+html);
//        } catch (MalformedURLException e){
//            e.printStackTrace();
//        } catch (IOException e){
//            e.printStackTrace();
//        }

        try{
            Document doc = Jsoup.connect("https://usd-cny.com/").get();
            Log.i(TAG,"run:title = " + doc.title());
            Element firstTable = doc.getElementsByTag("table").first();

//          Log.i(TAG,"run : table = "+ firstTable);

            Elements trs = firstTable.getElementsByTag("tr");
            trs.remove(0);

            for(Element tr:trs){
                Elements tds = tr.getElementsByTag("td");
                Element td1 = tds.get(0);
                Element td2 = tds.get(4);
                if("美元".equals(td1.text())){
                    sendto(td2,7);
                }
                else if("欧元".equals(td1.text())){
                    sendto(td2,8);
                }
                else if("韩币".equals(td1.text())){
                    sendto(td2,9);
                }
            }


            for(Element item:firstTable.getElementsByClass("bz")){
                Log.i(TAG,"run : item = "+item.text());
            }

            Elements tds = firstTable.getElementsByTag("td");

            Element td1 = tds.get(1);
            Element td2 = tds.get(6);
            Element td3 = tds.get(26);
            Log.i(TAG,"run:td1 = "+td1.text()+"\t td2 = "+td2.text()+"\t td3 = "+td3.text());

//            for(int i = 0;i<tds.size();i+=5){
//                Element td1 = tds.get(i);
//                Element td2 = tds.get(i+1);
//                Log.i(TAG,"run:td1 = "+td1.text()+"\t td2 = "+td2.text());
//            }

//            for( Element td : tds ){
//                Log.i(TAG,"run : td = " + td.text());
//            }

//            Elements ths = firstTable.getElementsByTag("th");
//            for(Element th : ths){
//                Log.i(TAG,"run : th="+th);
//                Log.i(TAG,"run : th.html="+th.html());
//                Log.i(TAG,"run : th.text="+th.text());
//            }
//            Element th2 = ths.get(1);
//            Log.i("666","run:th2 = "+th2);

        }catch(IOException e){
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

    void sendto(Element td2,int n){
        Message msg = handler.obtainMessage(n);
        msg.obj = td2.text();
        handler.sendMessage(msg);
        Log.i(TAG,"已发送");
    }


}


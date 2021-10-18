package com.stu.birthday_card;

import android.app.ListActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import androidx.annotation.NonNull;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RateListActivity extends ListActivity implements Runnable{
    String data[] = {"wait....."};
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_rate_list);
        ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,data);
        setListAdapter(adapter);

        Thread t = new Thread(this);
        t .start();

        handler = new Handler(Looper.myLooper()){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what==7){
                    List<String> list2 = (List<String>) msg.obj;
                    ListAdapter adapter = new ArrayAdapter<String>(RateListActivity.this, android.R.layout.simple_list_item_1,list2);
                    setListAdapter(adapter);

                }

            }
        };

    }

    @Override
    public void run(){
        //获取网络数据放入list
        List<String> retlist = new ArrayList<String>();
        try{
            Thread.sleep(3000);
            Document doc = Jsoup.connect("https://usd-cny.com/").get();
            Element firstTable = doc.getElementsByTag("table").first();

//          Log.i(TAG,"run : table = "+ firstTable);

            Elements trs = firstTable.getElementsByTag("tr");
            trs.remove(0);

            for(Element tr:trs){
                Elements tds = tr.getElementsByTag("td");
                Element td1 = tds.get(0);
                Element td2 = tds.get(4);
                String str1 = td1.text();
                String val = td2.text();

                retlist.add(str1 + "---->" + val);
            }

        }catch(IOException | InterruptedException e){
            e.printStackTrace();
        }
        Message msg = handler.obtainMessage(7);
        msg.obj = retlist;
        handler.sendMessage(msg);



    }
}
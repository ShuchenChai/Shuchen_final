package com.stu.birthday_card;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RateListActivity2 extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private static final String TAG = "R2";

    String data[] = {"wait....."};
    Handler handler;
    ListView mylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_list2);
        mylist= findViewById(R.id.mylist1);
        mylist.setOnItemClickListener(RateListActivity2.this);


        handler = new Handler(Looper.myLooper()){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what==33){
                    ArrayList<HashMap<String,String>> list2 = (ArrayList<HashMap<String,String>>) msg.obj;
                    myAdapter adapter = new myAdapter(RateListActivity2.this,R.layout.list_item,list2);
                    mylist.setAdapter(adapter);

                }

            }
        };


        myRun myrun = new myRun();
        myrun.setHandler(handler);
        Thread t = new Thread(myrun);
        t.start();
//        SimpleAdapter listItemAdapter = new SimpleAdapter(this,
//                listItems,
//                R.layout.list_item,
//                new String[]{"ItemTitle","ItemDetail"},
//                new int[]{R.id.itemTitle,R.id.itemDetail});
//        mylist.setAdapter(listItemAdapter);
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

        Object itemAtPosition = mylist.getItemAtPosition(position);
        HashMap<String,String> map = (HashMap<String,String>) itemAtPosition;
        String titlestr = map.get("ItemTitle");
        String detailstr = map.get("ItemDetail");
        Log.i(TAG, "title = "+titlestr);
        Log.i(TAG, "detail = "+detailstr);
        Intent intent = new Intent(this,Mtran_cau.class);
        intent.putExtra("title",titlestr);
        intent.putExtra("detail",detailstr);
        startActivityForResult(intent,50);
    }
}

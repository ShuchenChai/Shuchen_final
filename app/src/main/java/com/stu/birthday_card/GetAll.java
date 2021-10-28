package com.stu.birthday_card;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetAll extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private static final String TAG = "GetAll";
    Handler handler;
    ListView Onmyoji_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_all);


        Onmyoji_list = findViewById(R.id.Show_list);
        Onmyoji_list.setOnItemClickListener(GetAll.this);

        handler = new Handler(Looper.myLooper()){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what==55){
                    ArrayList<Item> list2 = (ArrayList<Item>) msg.obj;
                    myAdapter adapter = new myAdapter(GetAll.this,R.layout.list_item,list2);
                    Onmyoji_list.setAdapter(adapter);
                    Onmyoji_list.setEmptyView(findViewById(R.id.nodata));

                }

            }
        };

        myRun myrun = new myRun();
        myrun.setHandler(handler);
        Thread t = new Thread(myrun);
        t.start();

    }

    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

        Object itemAtPosition = Onmyoji_list.getItemAtPosition(position);
        Item item = (Item) itemAtPosition;
        String Sphurl = item.getCval();

        Log.i(TAG, "hurl = "+Sphurl);
        Intent intent = new Intent(this,ShowDetails.class);
        intent.putExtra("hurl_Onmyoji",Sphurl);
        startActivityForResult(intent,50);
    }
}
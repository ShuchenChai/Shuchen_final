package com.stu.birthday_card;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.bumptech.glide.Glide;

import javax.security.auth.login.LoginException;

public class ShowDetails extends AppCompatActivity implements Runnable{

    private static final String TAG = "ShowDetails";
    Handler handler;

    ImageView Img_Onmyoji;
    TextView name_Onmyoji;
    TextView CV_Onmyoji;
    TextView Rare_Onmyoji;
    TextView Type_Onmyoji;
    TextView DJ_Onmyoji;
    TextView REMED_Onmyoji;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);

        Img_Onmyoji = findViewById(R.id.imageView);
        name_Onmyoji = findViewById(R.id.Oname);
        CV_Onmyoji = findViewById(R.id.OCV);
        Rare_Onmyoji = findViewById(R.id.Orare);
        Type_Onmyoji = findViewById(R.id.Otype);
        DJ_Onmyoji = findViewById(R.id.Odj);
        REMED_Onmyoji = findViewById(R.id.Oremed);

        handler = new Handler(Looper.myLooper()){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what==66){
                    ArrayList<String> List2 = (ArrayList<String>) msg.obj;
                    String name = List2.get(0);
                    String CV = List2.get(1);
                    String Rare = List2.get(2);
                    String Type = List2.get(3);
                    String DJ = List2.get(4);
                    String REMED = List2.get(5);
                    String purl = List2.get(6);

                    name_Onmyoji.setText(name);
                    CV_Onmyoji.setText(CV);
                    Rare_Onmyoji.setText(Rare);
                    Type_Onmyoji.setText(Type);
                    DJ_Onmyoji.setText(DJ);
                    REMED_Onmyoji.setText(REMED);
                    Glide.with(ShowDetails.this).load("https:" + purl).into(Img_Onmyoji);
                }

            }
        };

        Thread t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {

        ArrayList<String> List;
        ArrayList<String> Listpic;
        List = new ArrayList<String>();
        Listpic = new ArrayList<String>();

        try {
            Intent intent = getIntent();
            String hurl_onmyoji = intent.getStringExtra("hurl_Onmyoji");
            Pattern mpsrc = Pattern.compile("src=\"(.*?)\"");


            Document doc = Jsoup.connect("https:"+hurl_onmyoji).get();
            Element table = doc.getElementsByTag("table").first();
            Elements Pcatch = doc.getElementsByTag("p");

            String picc = null;

            for(Element src : Pcatch ){
                Matcher msrc = mpsrc.matcher(src.toString());
                if(msrc.find()){
                    if (msrc.group(1).contains("//pic.87g.com/")) {
                        picc = msrc.group(1);
                        Listpic.add(picc);

                    }
                }
            }

            Elements trs = table.getElementsByTag("tr");
//            Log.i(TAG, "run: Pcatch = " + Pcatch);

//            Log.i(TAG, "run: name_Onmyoji = " + trs.get(0).getElementsByTag("td").get(1).text());
//            Log.i(TAG, "run: name_Onmyoji = " + trs.get(1).getElementsByTag("td").get(1).text());
//            Log.i(TAG, "run: name_Onmyoji = " + trs.get(2).getElementsByTag("td").get(1).text());
//            Log.i(TAG, "run: name_Onmyoji = " + trs.get(3).getElementsByTag("td").get(1).text());
//            Log.i(TAG, "run: name_Onmyoji = " + trs.get(4).getElementsByTag("td").get(1).text());
//            Log.i(TAG, "run: name_Onmyoji = " + trs.get(5).getElementsByTag("td").get(1).text());

            String name = trs.get(0).getElementsByTag("td").get(1).text();
            String CV = trs.get(1).getElementsByTag("td").get(1).text();
            String Rare = trs.get(2).getElementsByTag("td").get(1).text();
            String Type = trs.get(3).getElementsByTag("td").get(1).text();
            String DJ = trs.get(4).getElementsByTag("td").get(1).text();
            String REMED = trs.get(5).getElementsByTag("td").get(1).text();

            List.add(name);
            List.add(CV);
            List.add(Rare);
            List.add(Type);
            List.add(DJ);
            List.add(REMED);
            List.add(Listpic.get(0));

        } catch (IOException e) {
            e.printStackTrace();
        }
        Message msg = handler.obtainMessage(66);
        msg.obj = List;
        handler.sendMessage(msg);

    }
}
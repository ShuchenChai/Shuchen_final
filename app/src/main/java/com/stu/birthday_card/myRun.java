package com.stu.birthday_card;

import android.os.Handler;
import android.os.Message;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class myRun implements Runnable{


    Handler handler;

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void run(){
        //获取网络数据放入list
        ArrayList<HashMap<String,String>> listItems;
        listItems = new ArrayList<HashMap<String,String>>();
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
                HashMap<String,String> map = new HashMap<String,String>();

                map.put("ItemTitle",str1);
                map.put("ItemDetail",val);
                listItems.add(map);
            }

        }catch(IOException | InterruptedException e){
            e.printStackTrace();
        }
        Message msg = handler.obtainMessage(33);
        msg.obj = listItems;
        handler.sendMessage(msg);
    }
}

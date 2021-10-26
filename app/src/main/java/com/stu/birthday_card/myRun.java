package com.stu.birthday_card;

import android.nfc.Tag;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class myRun implements Runnable{
    private static final String TAG = "myrun";
    Handler handler;
    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void run(){
        //获取网络数据放入list
        ArrayList<Item> listItems;
        listItems = new ArrayList<Item>();
        try{
            Thread.sleep(3000);
            Document doc = Jsoup.connect("https://m.87g.com/yys/38101.html").get();
            Element firstTable = doc.getElementsByTag("table").first();
            String pic = null;
            String name = null;
            String hurl = null;
//          Log.i(TAG,"run : table = "+ firstTable);

            Elements trs = firstTable.getElementsByTag("tr");

            for(Element tr:trs){
                Elements tds = tr.getElementsByTag("td");
//                Log.i(TAG, "run: tds = "+tds);

                for(Element td:tds){
                    String td1 = td.text();
                    if(td1.isEmpty()){
//                        Element td2 = tds.get(0).getElementsByAttribute("href").first();
                        Pattern mpsrc = Pattern.compile("src=\"(.*?)\"");
                        Matcher msrc = mpsrc.matcher(td.toString());

                        Pattern npsrc = Pattern.compile("alt=\"(.*?)\"");
                        Matcher nsrc = npsrc.matcher(td.toString());

                        Pattern hpsrc = Pattern.compile("href=\"(.*?)\"");
                        Matcher hsrc = hpsrc.matcher(td.toString());
                        while (msrc.find()) {
                            if (msrc.group(1).contains("//pic.87g.com/")) {
                                pic = msrc.group(1);
                            }
                        }
                        while(nsrc.find()){
                            name = nsrc.group(1);
                        }
                        while (hsrc.find()){
                            if (hsrc.group(1).contains("//m.87g.com/")) {
                                hurl = hsrc.group(1);
                            }
                        }
                        Log.i(TAG, "run: name = " + name);
                        Log.i(TAG, "run: pic = " + pic);
                        Log.i(TAG, "run: url = " + hurl);
                    }

                }
//                Element td2 = tds.get(4);
//                Element td3 = tds.get(5);
//                String str1 = td1.text();
//                String val = td2.text();
//                String img = td3.text();
//                Item item = new Item(str1,val,img);
//                listItems.add(item);

            }

        }catch(IOException | InterruptedException e){
            e.printStackTrace();
        }
        Message msg = handler.obtainMessage(55);
        msg.obj = listItems;
        handler.sendMessage(msg);
    }
}

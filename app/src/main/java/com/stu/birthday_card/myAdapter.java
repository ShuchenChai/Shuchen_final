package com.stu.birthday_card;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
//import com.stu.birthday_card.glide.Glide;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class myAdapter extends ArrayAdapter {
    private static final String TAG = "MyAdapter";
    public myAdapter(@NonNull Context context,
                     int resource,
                     @NonNull ArrayList<Item> list) {
        super(context, resource, list);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View itemView = convertView;
        if(itemView ==null){
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,
                    parent,
                    false);
        }

        Item item = (Item) getItem(position);
        ImageView pic = itemView.findViewById(R.id.pics);
        TextView name = itemView.findViewById(R.id.names);

        name.setText(item.getCname());
        String picurl = item.getImg();
        Glide.with(name).load("https:" + picurl).into(pic);

        return itemView;
    }
}

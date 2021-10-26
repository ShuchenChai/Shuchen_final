package com.stu.birthday_card;

import android.sax.Element;

public class Item {

    private String img;
    private String cname;
    private String cval;

    public Item(String cname, String cval, String img) {
        this.cname = cname;
        this.cval = cval;
        this.img = img;
    }

    public String getCname() {
        return cname;
    }

    public String getCval() {
        return cval;
    }

    public String getImg() { return img;}
}


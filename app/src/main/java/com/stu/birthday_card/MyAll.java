package com.stu.birthday_card;

import android.content.SharedPreferences;

public class MyAll{
    private String id;
    private String Cname;
    private String star;
    private int level;
    private String YH_name;


    public String getId() {
        return id;
    }

    public String getC_Name() {
        return Cname;
    }

    public String getStar() {
        return star;
    }

    public int getLevel() {
        return level;
    }

    public String getYH_name() {
        return YH_name;
    }



    public MyAll(String id, String Cname, String star, int level, String YH_name) {
        this.id = id;
        this.Cname = Cname;
        this.star = star;
        this.level = level;
        this.YH_name = YH_name;
    }
}

package com.example.ifoodmate;

public class recyclercat {
    String cat_name,cat_rest,imageid;


    public recyclercat(String cat_name, String cat_rest, String imageid) {
        this.cat_name = cat_name;
        this.cat_rest = cat_rest;
        this.imageid = imageid;
    }

    public String getCat_name() {
        return cat_name;
    }

    public String getCat_rest() {
        return cat_rest;
    }

    public String getImageid() {
        return imageid;
    }
}

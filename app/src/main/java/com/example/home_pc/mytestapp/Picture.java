package com.example.home_pc.mytestapp;

import java.io.Serializable;

public class Picture implements Serializable {
    String url;

    public Picture()  {
    }

    public Picture(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}

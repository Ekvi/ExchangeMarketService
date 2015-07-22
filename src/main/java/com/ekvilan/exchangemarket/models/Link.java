package com.ekvilan.exchangemarket.models;


public class Link {
    private String cityName;
    private String link;

    public Link(String cityName, String link) {
        this.cityName = cityName;
        this.link = link;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}

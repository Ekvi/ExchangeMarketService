package com.ekvilan.exchangemarket.models;


import javax.persistence.*;

@Entity
@Table(name = "links")
public class Link {
    @Id
    @GeneratedValue
    private long id;
    @Column(name = "site_name")
    private String siteName;
    @Column(name = "city")
    private String cityName;
    @Column(name = "link")
    private String link;

    public Link(){}

    public Link(String siteName, String cityName, String link) {
        this.siteName = siteName;
        this.cityName = cityName;
        this.link = link;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
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

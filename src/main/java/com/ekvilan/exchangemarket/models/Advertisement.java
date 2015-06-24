package com.ekvilan.exchangemarket.models;

import javax.persistence.*;


@Entity
@Table(name = "advertisements")
public class Advertisement {
    @Id
    @GeneratedValue
    private long id;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "user_city")
    private String city;
    @Column(name = "action_user_choice")
    private String action;
    @Column(name = "currency_user_choice")
    private String currency;
    @Column(name = "summa")
    private int sum;
    @Column
    private double rate;
    @Column(name = "user_phone")
    private String phone;
    @Column(name = "user_area")
    private String area;
    @Column(name = "user_comment")
    private String comment;
    @Column(name = "publish_date")
    private String date;

    public Advertisement(){}

    public Advertisement(long id, String userId, String action, String currency, int sum, double rate, String phone,
                         String city, String area, String comment, String date) {
        this.id = id;
        this.userId = userId;
        this.action = action;
        this.currency = currency;
        this.sum = sum;
        this.rate = rate;
        this.phone = phone;
        this.city = city;
        this.area = area;
        this.comment = comment;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

package com.ekvilan.exchangemarket.controllers.models;


import java.util.List;

public class RequestInfo {
    private String city;
    private List<String> actions;
    private List<String> currencies;

    public RequestInfo() {}

    public RequestInfo(String city, List<String> actions, List<String> currencies) {
        this.city = city;
        this.actions = actions;
        this.currencies = currencies;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<String> getActions() {
        return actions;
    }

    public void setActions(List<String> actions) {
        this.actions = actions;
    }

    public List<String> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<String> currencies) {
        this.currencies = currencies;
    }
}

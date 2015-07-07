package com.ekvilan.exchangemarket.controllers.cache;


import com.ekvilan.exchangemarket.models.Rates;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Cache {
    private List<Rates> ratesList;

    public Cache() {
        ratesList = new ArrayList<Rates>();
    }

    public void save(List<Rates> newRates) {
        ratesList = newRates;
    }

    public List<Rates> getRates() {
        return ratesList;
    }
}

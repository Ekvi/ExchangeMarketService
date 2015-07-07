package com.ekvilan.exchangemarket.utils;


import com.ekvilan.exchangemarket.controllers.cache.Cache;
import com.ekvilan.exchangemarket.models.Rates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class RatesUpdater {
    @Autowired
    private RatesExtractor ratesExtractor;
    @Autowired
    private Cache cache;

    @Scheduled(cron="0 5 * * * *")
    public void update() {
        List<Rates> ratesList = ratesExtractor.getRates();
        cache.save(ratesList);

        for(Rates r : ratesList) {
            System.out.println("usd " + r.getUsdBuy() + " " + r.getUsdSale());
            System.out.println("eur " + r.getEurBuy() + " " + r.getEurSale());
            System.out.println("rub " + r.getRubBuy() + " " + r.getRubSale() + "\n");
        }
    }
}

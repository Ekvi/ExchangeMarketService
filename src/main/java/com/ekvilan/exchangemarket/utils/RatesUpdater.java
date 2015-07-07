package com.ekvilan.exchangemarket.utils;


import com.ekvilan.exchangemarket.controllers.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class RatesUpdater {
    @Autowired
    private RatesExtractor ratesExtractor;
    @Autowired
    private Cache cache;

    @Scheduled(cron="0 5 * * * *")
    public void update() {
        cache.save(ratesExtractor.getRates());
    }
}

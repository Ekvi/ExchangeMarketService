package com.ekvilan.exchangemarket.utils;


import com.ekvilan.exchangemarket.models.Advertisement;
import com.ekvilan.exchangemarket.models.Link;
import com.ekvilan.exchangemarket.services.AdvertisementService;
import com.ekvilan.exchangemarket.services.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class AdsUpdater {
    @Autowired
    private FinanceIUaParser parser;
    @Autowired
    private AdvertisementService advertisementService;
    @Autowired
    private LinkService linkService;

    private final String BUY = "?type=1";
    private final String SALE = "?type=2";


    @Scheduled(cron="0 0/15 * * * *")
    public void update() {
        List<Advertisement> fromDb = advertisementService.getAdvertisements("finance.i.ua");
        List<Advertisement> newAds = new ArrayList<Advertisement>();
        List<Advertisement> removeAds = new ArrayList<Advertisement>();
        List<Link> links = linkService.getLinks();

        for(Link link : links) {
            newAds.addAll(parser.parseAdvertisements(link, BUY));
            removeAds.addAll(parser.getRemoveAds());
            newAds.addAll(parser.parseAdvertisements(link, SALE));
            removeAds.addAll(parser.getRemoveAds());
        }

        List<Advertisement> alsoRemove = new ArrayList<Advertisement>();
        for(Advertisement a : fromDb) {
            a.setId(0);
            if (!newAds.contains(a) && !removeAds.contains(a)) {
                alsoRemove.add(a);
            }
        }
        removeAds.addAll(alsoRemove);

        advertisementService.update(removeAds, newAds);
    }
}

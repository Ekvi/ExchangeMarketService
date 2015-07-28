package com.ekvilan.exchangemarket.utils;



import com.ekvilan.exchangemarket.SpringTestConfiguration;
import com.ekvilan.exchangemarket.models.Advertisement;
import com.ekvilan.exchangemarket.models.Link;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringTestConfiguration.class)
public class IntegrationTestFinanceIUaParser {
    @Autowired
    private FinanceIUaParser parser;

    @Test
    public void testParseAdvertisements() {
        Link link = new Link("http://finance.i.ua", "Одесса", "http://finance.i.ua/market/odessa/");
        String action = "?type=1";
        List<Advertisement> newAds = parser.parseAdvertisements(link, action);
        List<Advertisement> removeAds = parser.getRemoveAds();

        for(Advertisement a : newAds) {
            System.out.println(a.getUserId() + " " + a.getCity() + " " + a.getAction() + " " + a.getCurrency() +
                    " " + a.getSum() + " " + a.getRate() + " " + a.getArea() + " " + a.getComment() + " " + a.getDate());
        }
        System.out.println("###########################################################");
        for(Advertisement a : removeAds) {
            System.out.println(a.getUserId() + " " + a.getCity() + " " + a.getAction() + " " + a.getCurrency() +
                    " " + a.getSum() + " " + a.getRate() + " " + a.getArea() + " " + a.getComment() + " " + a.getDate());
        }
    }

}

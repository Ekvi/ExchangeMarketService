package com.ekvilan.exchangemarket.utils;

import com.ekvilan.exchangemarket.SpringTestConfiguration;
import com.ekvilan.exchangemarket.models.Rates;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringTestConfiguration.class)
public class TestRatesExtractor {
    @Autowired
    private RatesExtractor ratesExtractor;

    @Test
    public void testGetRates() {
        List<Rates> ratesList = ratesExtractor.getRates();

        for(Rates r : ratesList) {
            System.out.println("usd " + r.getUsdBuy() + " " + r.getUsdSale());
            System.out.println("eur " + r.getEurBuy() + " " + r.getEurSale());
            System.out.println("rub " + r.getRubBuy() + " " + r.getRubSale() + "\n");
        }
    }
}

package com.ekvilan.exchangemarket.controllers;

import com.ekvilan.exchangemarket.SpringTestConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertTrue;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringTestConfiguration.class)
public class IntegrationTestRatesController {
    @Autowired
    private RatesController ratesController;

    @Test
    public void testGetRates() {
        assertTrue(!ratesController.getRates().isEmpty());
    }
}

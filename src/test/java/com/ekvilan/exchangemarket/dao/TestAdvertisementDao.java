package com.ekvilan.exchangemarket.dao;


import com.ekvilan.exchangemarket.SpringTestConfiguration;
import com.ekvilan.exchangemarket.models.Advertisement;
import com.ekvilan.exchangemarket.services.AdvertisementService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringTestConfiguration.class)
public class TestAdvertisementDao {
    @Autowired
    private AdvertisementService service;


    @Test
    @Transactional
    public void testSave() {
        service.save(new Advertisement(45,"test@gmail.com", "Odessa", "продажа", "usd", 200, 20.2,
                 "80971234543", null, null, new Date().toString()));

        Advertisement actual = service.get("userId","test@gmail.com");

        assertNotNull(actual);
        assertEquals("test@gmail.com", actual.getUserId());
    }

    @Test
    public void testGetListWithOneActionAndCurrency() {
        List<String> actions = new ArrayList<String>();
        actions.add("купить");
        List<String> currencies = new ArrayList<String>();
        currencies.add("евро");

        List<Advertisement> ads = service.getAdvertisements("киев", actions, currencies);

        assertTrue(!ads.isEmpty());
    }

    @Test
    public void testGetListWithManyActionsAndCurrencies() {
        List<String> actions = new ArrayList<String>();
        actions.add("купить"); actions.add("продать");

        List<String> currencies = new ArrayList<String>();
        currencies.add("евро"); currencies.add("доллар");

        List<Advertisement> ads = service.getAdvertisements("киев", actions, currencies);

        assertTrue(ads.size() > 0);
    }
}

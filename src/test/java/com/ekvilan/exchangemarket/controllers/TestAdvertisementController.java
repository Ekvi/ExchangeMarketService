package com.ekvilan.exchangemarket.controllers;

import com.ekvilan.exchangemarket.SpringTestConfiguration;
import com.ekvilan.exchangemarket.controllers.models.RequestInfo;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringTestConfiguration.class)
public class TestAdvertisementController {
    @Autowired
    private AdvertisementController controller;
    @Autowired
    private AdvertisementService service;

    @Test
    @Transactional
    public void testAddAdvertisement() {
        controller.addAdvertisement(new Advertisement("test@gmail.com", "продажа", "usd", 200, 20.2,
                "Odessa", "80971234543", null, null, new Date().toString()));

        Advertisement actual = service.get("userId","test@gmail.com");

        assertNotNull(actual);
        assertEquals("test@gmail.com", actual.getUserId());
    }

    @Test
    public void testGetAdvertisements() {
        List<String> actions = new ArrayList<String>();
        actions.add("купить"); actions.add("продать");

        List<String> currencies = new ArrayList<String>();
        currencies.add("евро"); currencies.add("доллар");

        List<Advertisement> ads = controller.getAdvertisements(new RequestInfo("одесса", actions, currencies));

        assertNotNull(ads);
        assertTrue(ads.size() > 0);
    }

    @Test
    public void testGetOwnAdvertisements() {
        List<Advertisement> ads = controller.getOwnAdvertisements("\"userId\":\"dmitrii.shribak@gmail.com\"");

        assertNotNull(ads);
        assertTrue(ads.size() > 0);
    }
}

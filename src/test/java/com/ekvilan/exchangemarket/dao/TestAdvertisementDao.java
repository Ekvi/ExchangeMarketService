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

import java.util.Date;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringTestConfiguration.class)
public class TestAdvertisementDao {
    @Autowired
    private AdvertisementService service;


    @Test
    @Transactional
    public void testSave() {
        service.save(new Advertisement("test@gmail.com", "продажа", "usd", 200, 20.2,
                "Odessa", "80971234543", null, null, new Date().toString()));

        Advertisement actual = service.get("userId","test@gmail.com");

        assertNotNull(actual);
        assertEquals("test@gmail.com", actual.getUserId());
    }
}

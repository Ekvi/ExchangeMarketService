package com.ekvilan.exchangemarket.controllers;


import com.ekvilan.exchangemarket.controllers.cache.Cache;
import com.ekvilan.exchangemarket.models.Rates;
import com.ekvilan.exchangemarket.utils.RatesExtractor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class UnitTestRatesController {
    @Mock
    private Cache cache;
    @Mock
    private RatesExtractor ratesExtractor;
    @InjectMocks
    @Spy
    private RatesController ratesController;

    @Test
    public void testGetRatesWhenCacheIsEmpty() {
        List<Rates> emptyList = Collections.emptyList();

        when(cache.getRates()).thenReturn(emptyList);

        List<Rates> ratesList = ratesController.getRates();

        assertTrue(ratesList.isEmpty());
        verify(ratesExtractor, times(1)).getRates();
        verify(cache, times(1)).save(anyList());

    }

    @Test
    public void testGetRatesFromCache() {
        List<Rates> fakeRates = Arrays.asList(new Rates(), new Rates(), new Rates());

        when(cache.getRates()).thenReturn(fakeRates);

        List<Rates> ratesList = ratesController.getRates();

        assertTrue(!ratesList.isEmpty());
        verify(cache, times(1)).getRates();
        verify(ratesExtractor, never()).getRates();
        verify(cache, never()).save(anyList());
    }
}

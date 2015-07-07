package com.ekvilan.exchangemarket.controllers;

import com.ekvilan.exchangemarket.controllers.cache.Cache;
import com.ekvilan.exchangemarket.models.Rates;
import com.ekvilan.exchangemarket.utils.RatesExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/rates")
public class RatesController {
    @Autowired
    private RatesExtractor ratesExtractor;
    @Autowired
    private Cache cache;

    @RequestMapping("/get")
    @ResponseBody
    public List<Rates> getRates(){
        List<Rates> ratesList = cache.getRates();
        if(ratesList != null && !ratesList.isEmpty()) {
            return ratesList;
        } else {
            ratesList = ratesExtractor.getRates();
            cache.save(ratesList);
            return ratesList;
        }
    }
}

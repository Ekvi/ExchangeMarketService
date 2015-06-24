package com.ekvilan.exchangemarket.controllers;

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

    @RequestMapping("/get")
    @ResponseBody
    public List<Rates> getRates(){
        return ratesExtractor.getRates();
    }
}

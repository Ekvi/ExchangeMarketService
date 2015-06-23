package com.ekvilan.exchangemarket.controllers;

import com.ekvilan.exchangemarket.models.Advertisement;
import com.ekvilan.exchangemarket.controllers.models.RequestInfo;
import com.ekvilan.exchangemarket.services.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/advertisement")
public class AdvertisementController {
    @Autowired
    private AdvertisementService service;


    @RequestMapping("/get")
    @ResponseBody
    public List<Advertisement> getAdvertisements(@RequestBody RequestInfo params){
        return service.getAdvertisements(params.getCity(), params.getActions(), params.getCurrencies());
    }

    @RequestMapping("/getOwn")
    @ResponseBody
    public List<Advertisement> getOwnAdvertisements(@RequestBody String userId){
        return service.getAdvertisements(extractValue(userId));
    }

    @RequestMapping("/add")
    @ResponseBody
    public void addAdvertisement(@RequestBody Advertisement advertisement){
        service.save(advertisement);
    }

    @RequestMapping("/remove")
    @ResponseBody
    public void removeAdvertisement(@RequestBody String id){
        service.remove(Long.parseLong(extractValue(id)));
    }

    private String extractValue(String content) {
        String[] str = content.split(":");
        content = str[1];
        return content.replaceAll("\"", "").replaceAll("}", "");
    }
}

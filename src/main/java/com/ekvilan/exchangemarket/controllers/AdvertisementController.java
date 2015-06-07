package com.ekvilan.exchangemarket.controllers;

import com.ekvilan.exchangemarket.models.Advertisement;
import com.ekvilan.exchangemarket.services.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/advertisement")
public class AdvertisementController {
    @Autowired
    private AdvertisementService service;


    @RequestMapping("/{userId}")
    @ResponseBody
    public List<Advertisement> getOwnAdvertisements(@PathVariable("userId") String userId){
        System.out.println(userId);
        return null;
    }

    @RequestMapping("/all")
    @ResponseBody
    public List<Advertisement> getAllAdvertisements(){

        return null;
    }

    @RequestMapping("/get")
    @ResponseBody
    public List<Advertisement> getAdvertisements(@RequestBody String params){

        return null;
    }

    @RequestMapping("/add")
    @ResponseBody
    public void addAdvertisement(@RequestBody Advertisement advertisement/*Person person*/){
        service.save(advertisement);
    }
}

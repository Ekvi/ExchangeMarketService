package com.ekvilan.exchangemarket.controllers;

import com.ekvilan.exchangemarket.models.Advertisement;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/advertisement")
public class JsonController {

    /*@RequestMapping("/{name}")
    @ResponseBody
    public User generateJsonResponse(@PathVariable("name") String name){
        User user = new User(1001, name);
        return user;
    }*/

    @RequestMapping("/userId")
    @ResponseBody
    public List<Advertisement> getOwnAdvertisements(@RequestBody String userId){

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
    public List<Advertisement> addAdvertisements(@RequestBody String params){

        return null;
    }
}

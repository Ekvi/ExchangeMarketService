package com.ekvilan.exchangemarket.controllers;

import com.ekvilan.exchangemarket.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/user")
public class JsonOutputContoller {

    @RequestMapping("/{name}")
    @ResponseBody
    public User generateJsonResponse(@PathVariable("name") String name){
        User user = new User(1001, name);
        return user;
    }
}

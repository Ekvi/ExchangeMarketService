package com.ekvilan.exchangemarket.services;


import com.ekvilan.exchangemarket.models.Advertisement;

public interface AdvertisementService {
    public void save(Advertisement advertisement);
    public Advertisement get(String name, Object value);
}

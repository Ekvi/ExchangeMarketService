package com.ekvilan.exchangemarket.services;


import com.ekvilan.exchangemarket.models.Advertisement;

import java.util.List;

public interface AdvertisementService {
    public void save(Advertisement advertisement);
    public Advertisement get(String name, Object value);
    public List<Advertisement> getAdvertisements(String city, List<String> actions, List<String> currencies);
    public List<Advertisement> getAdvertisements(String userId);
    public void remove(long id);
    public void update(List<Advertisement> removeAds, List<Advertisement> newAds);
}

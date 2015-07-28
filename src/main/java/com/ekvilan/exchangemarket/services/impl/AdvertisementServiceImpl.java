package com.ekvilan.exchangemarket.services.impl;

import com.ekvilan.exchangemarket.dao.AdvertisementDao;
import com.ekvilan.exchangemarket.models.Advertisement;
import com.ekvilan.exchangemarket.services.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class AdvertisementServiceImpl implements AdvertisementService {
    @Autowired
    private AdvertisementDao advertisementDao;

    @Override
    public void save(Advertisement advertisement) {
        advertisementDao.save(advertisement);
    }

    @Override
    public Advertisement get(String name, Object value) {
        return advertisementDao.get(name, value);
    }

    @Override
    public List<Advertisement> getAdvertisements(String city, List<String> actions, List<String> currencies) {
        return advertisementDao.getList(city, actions, currencies);
    }

    @Override
    public List<Advertisement> getAdvertisements(String userId) {
        return advertisementDao.getList("userId", userId);
    }

    @Override
    public void remove(long id) {
        advertisementDao.remove("id", id);
    }

    @Override
    public void update(List<Advertisement> removeAds, List<Advertisement> newAds) {
        advertisementDao.remove(removeAds);
        advertisementDao.save(newAds);
    }
}

package com.ekvilan.exchangemarket.services;

import com.ekvilan.exchangemarket.dao.AdvertisementDao;
import com.ekvilan.exchangemarket.models.Advertisement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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
}

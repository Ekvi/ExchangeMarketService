package com.ekvilan.exchangemarket.dao;

import com.ekvilan.exchangemarket.models.Advertisement;
import org.springframework.stereotype.Repository;


@Repository
public class AdvertisementDao extends AbstractHibernateDAO<Advertisement> {
    public AdvertisementDao() {
        super(Advertisement.class);
    }
}

package com.ekvilan.exchangemarket.services.impl;


import com.ekvilan.exchangemarket.dao.LinkDao;
import com.ekvilan.exchangemarket.models.Link;
import com.ekvilan.exchangemarket.services.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LinkServiceImpl implements LinkService {
    @Autowired
    private LinkDao linkDao;

    @Override
    public List<Link> getLinks() {
        return linkDao.getAll();
    }
}

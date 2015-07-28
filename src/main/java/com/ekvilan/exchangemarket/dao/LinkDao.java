package com.ekvilan.exchangemarket.dao;

import com.ekvilan.exchangemarket.models.Link;
import org.springframework.stereotype.Repository;


@Repository
public class LinkDao extends AbstractHibernateDAO<Link> {
    public LinkDao() {
        super(Link.class);
    }
}

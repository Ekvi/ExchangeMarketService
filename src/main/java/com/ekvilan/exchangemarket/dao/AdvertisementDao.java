package com.ekvilan.exchangemarket.dao;

import com.ekvilan.exchangemarket.models.Advertisement;
import org.hibernate.Criteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;



@Repository
public class AdvertisementDao extends AbstractHibernateDAO<Advertisement> {
    public AdvertisementDao() {
        super(Advertisement.class);
    }

    public List<Advertisement> getList(String city, List<String> actions, List<String> currencies) {
        Criteria criteria = criteria();

        criteria.add(Restrictions.eq("city", city));

        Disjunction actionDisjunction = Restrictions.disjunction();
        for(String action : actions) {
            actionDisjunction.add(Restrictions.eq("action", action));
        }
        criteria.add(actionDisjunction);

        Disjunction currencyDisjunction = Restrictions.disjunction();
        for(String currency : currencies) {
            currencyDisjunction.add(Restrictions.eq("currency", currency));
        }
        criteria.add(currencyDisjunction);

        return criteria.list();
    }
}

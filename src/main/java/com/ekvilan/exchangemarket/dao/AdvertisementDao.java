package com.ekvilan.exchangemarket.dao;

import com.ekvilan.exchangemarket.models.Advertisement;
import org.hibernate.Criteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.hibernate.criterion.Restrictions.eq;


@Repository
public class AdvertisementDao extends AbstractHibernateDAO<Advertisement> {
    public AdvertisementDao() {
        super(Advertisement.class);
    }

    public List<Advertisement> getList(String city, List<String> actions, List<String> currencies) {
        Criteria criteria = criteria();

        criteria.add(eq("city", city));

        Disjunction actionDisjunction = Restrictions.disjunction();
        for(String action : actions) {
            actionDisjunction.add(eq("action", action));
        }
        criteria.add(actionDisjunction);

        Disjunction currencyDisjunction = Restrictions.disjunction();
        for(String currency : currencies) {
            currencyDisjunction.add(eq("currency", currency));
        }
        criteria.add(currencyDisjunction);
        criteria.addOrder(Order.desc("id"));

        return criteria.list();
    }

    @Override
    public List<Advertisement> getList(String property, Object value) {
        return criteria()
                .add(eq(property, value))
                .addOrder(Order.desc("id"))
                .list();
    }

    public void remove(List<Advertisement> advertisements) {
        for(Advertisement a : advertisements) {
            Advertisement advertisement = getAd(a);
            if(advertisement != null) {
                currentSession().delete(advertisement);
            }
        }
    }

    public void save(List<Advertisement> advertisements) {
        for(Advertisement a : advertisements) {
            Advertisement advertisement = getAd(a);
            if(advertisement == null) {
                currentSession().save(a);
            }
        }
    }

    private Advertisement getAd(Advertisement a) {

        return (Advertisement)criteria()
                .add(eq("userId", a.getUserId())).add(eq("city", a.getCity()))
                .add(eq("action", a.getAction())).add(eq("currency", a.getCurrency()))
                .add(eq("sum", a.getSum())).add(eq("rate", a.getRate()))
                .add(eq("phone", a.getPhone())).add(eq("area", a.getArea()))
                .add(eq("comment", a.getComment())).add(eq("date", a.getDate()))
                .uniqueResult();
    }
}

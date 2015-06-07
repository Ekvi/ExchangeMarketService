package com.ekvilan.exchangemarket.dao;

import com.ekvilan.exchangemarket.models.Advertisement;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;


public abstract class AbstractHibernateDAO<E> implements BasicCrudDao<E> {
    @Autowired
    private SessionFactory sessionFactory;

    protected final Class<E> entityClass;

    public AbstractHibernateDAO(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    protected Criteria criteria() {
        return currentSession().createCriteria(entityClass);
    }

    protected Session currentSession() {
        return sessionFactory.getCurrentSession();
    }


    @Override
    public void save(E entity) {
        currentSession().save(entity);
    }

    @Override
    public List<E> getAll() {
        return criteria()
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .list();
    }

    @Override
    public E get(String property, Object value) {
        return (E) criteria().add(Restrictions.eq(property, value))
                .uniqueResult();
    }

    /*@Override
    public E get(String property, Object value, String property2, Object value2) {
        Criteria criteria = criteria();
        criteria.add(Restrictions.eq(property, value));
        criteria.add(Restrictions.eq(property2, value2));

        return (E)criteria.uniqueResult();
    }*/

    @Override
    public List<E> getList(String property, Object value) {
        return criteria()
                .add(Restrictions.eq(property, value))
                .list();
    }

}

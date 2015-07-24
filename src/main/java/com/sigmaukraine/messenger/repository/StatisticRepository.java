package com.sigmaukraine.messenger.repository;

import com.sigmaukraine.messenger.domain.User;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class StatisticRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public Integer getSubjectsCount() {
        return Integer.valueOf(sessionFactory.getCurrentSession().createQuery("select count(*) from Subject ").uniqueResult().toString());
    }

    public Integer getChatsCount() {
        return Integer.valueOf(sessionFactory.getCurrentSession().createQuery("select count(*) from Chat").uniqueResult().toString());
    }

    public Integer getMessagesCount() {
        return Integer.valueOf(sessionFactory.getCurrentSession().createQuery("select count(*) from Message ").uniqueResult().toString());
    }

    public Integer getUsersCount() {
        return Integer.valueOf(sessionFactory.getCurrentSession().createQuery("select count(*) from User ").uniqueResult().toString());
    }
}

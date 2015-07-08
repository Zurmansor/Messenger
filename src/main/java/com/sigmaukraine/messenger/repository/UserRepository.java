package com.sigmaukraine.messenger.repository;

import com.sigmaukraine.messenger.domain.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class UserRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public void addUser(User user) {
        this.sessionFactory.getCurrentSession().save(user);
    }

    public User getUserByLogin(String login) {
        List <User> users = this.sessionFactory.getCurrentSession()
                .createQuery("FROM User WHERE login=?")
                .setParameter(0, login)
                .list();

        return users.size() > 0 ? users.get(0) : null;

    }

    public User getUserByLoginAndPassword(String login, String password) {
        List <User> users = this.sessionFactory.getCurrentSession()
                .createQuery("FROM User WHERE login=? AND password=?")
                .setParameter(0, login)
                .setParameter(1, password)
                .list();
        return users.size() > 0 ? users.get(0) : null;
    }
}

package com.sigmaukraine.messenger.repository;

import com.sigmaukraine.messenger.domain.Subject;
import com.sigmaukraine.messenger.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Repository
@Transactional
public class UserRepository {
    private static Logger LOG = Logger.getLogger(UserRepository.class.getName());

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
        if (LOG.isLoggable(Level.INFO)) {
            LOG.log(Level.INFO, "getting user by login");
        }
        return users.size() > 0 ? users.get(0) : null;
    }

/*    public User getUserByLoginAndPassword(String login, String password) {
        List <User> users = this.sessionFactory.getCurrentSession()
                .createQuery("FROM User WHERE login=? AND password=?")
                .setParameter(0, login)
                .setParameter(1, password)
                .list();
        return users.size() > 0 ? users.get(0) : null;
    }*/

    public List<User> listAll() {
        if (LOG.isLoggable(Level.INFO)) {
            LOG.log(Level.INFO, "getting list of all users");
        }
        return this.sessionFactory.getCurrentSession().createQuery("from User")
                .list();
    }

    public void removeUser(int id) {
        User contact = (User) this.sessionFactory.getCurrentSession().load(User.class, id);
        if (null != contact) {
            this.sessionFactory.getCurrentSession().delete(contact);
            if (LOG.isLoggable(Level.INFO)) {
                LOG.log(Level.INFO, "removing user");
            }
        }
    }


    public User getUserById(int id) {
        return (User) this.sessionFactory.getCurrentSession().get(User.class, id);
    }

    public void editUser(int userId ,User updatedUser) {
        Session session = sessionFactory.openSession();
//        Transaction tr = (Transaction) session.beginTransaction();
        User user = getUserById(userId);
        user.setLogin(updatedUser.getLogin());
        user.setFirstName(updatedUser.getFirstName());
        user.setLastName(updatedUser.getLastName());
        user.setEmail(updatedUser.getEmail());
        user.setPhone(updatedUser.getPhone());
        user.setComment(updatedUser.getComment());
        session.update(user);
    }
}

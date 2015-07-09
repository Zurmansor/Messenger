package com.sigmaukraine.messenger.repository;

import com.sigmaukraine.messenger.domain.Message;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class MessageRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public List<Message> listAll() {
        return this.sessionFactory.getCurrentSession().createQuery("from Message")
                .list();
    }

    public void removeMessage(int id) {
        Message contact = (Message) this.sessionFactory.getCurrentSession().load(Message.class, id);

        if (null != contact) {
            this.sessionFactory.getCurrentSession().delete(contact);
        }
    }

    public void addMessage(Message message) {
        this.sessionFactory.getCurrentSession().save(message);
    }

/*    public Subject getSubjectByName(String name) {
        List<Subject> subjects = this.sessionFactory.getCurrentSession()
                .createQuery("FROM Subject WHERE name=?")
                .setParameter(0, name)//.uniqueResult()
                .list();

        return subjects.size() > 0 ? subjects.get(0) : null;
    }*/
}

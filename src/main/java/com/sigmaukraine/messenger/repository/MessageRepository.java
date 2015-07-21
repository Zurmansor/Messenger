package com.sigmaukraine.messenger.repository;

import com.sigmaukraine.messenger.domain.Message;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Repository
@Transactional
public class MessageRepository {

    private static Logger LOG = Logger.getLogger(MessageRepository.class.getName());

    @Autowired
    private SessionFactory sessionFactory;

    public List<Message> listAll() {
        if (LOG.isLoggable(Level.INFO)) {
            LOG.log(Level.INFO, "getting list of all messages");
        }
        return this.sessionFactory.getCurrentSession().createQuery("from Message")
                .list();
    }

    public void removeMessage(int id) {
        Message contact = (Message) this.sessionFactory.getCurrentSession().load(Message.class, id);

        if (null != contact) {
            this.sessionFactory.getCurrentSession().delete(contact);
            if (LOG.isLoggable(Level.INFO)) {
                LOG.log(Level.INFO, "removing the message");
            }
        }
    }

    public void addMessage(Message message) {
        if (LOG.isLoggable(Level.INFO)) {
            LOG.log(Level.INFO, "adding a message");
        }
        this.sessionFactory.getCurrentSession().save(message);
    }


    public List<Message> getListMessagesByChatId (int id) {
        if (LOG.isLoggable(Level.INFO)) {
            LOG.log(Level.INFO, "getting list of messages by id");
        }
        return this.sessionFactory.getCurrentSession()
                .createQuery("FROM Message WHERE chatId=? ORDER BY created DESC")
                .setParameter(0, id)
                .setMaxResults(20)
                .list();
    }

    public List<Message> getNewMessages (int chatId, Timestamp lastMessageTimeStamp) {
        if (LOG.isLoggable(Level.INFO)) {
            LOG.log(Level.INFO, "getting new messages");
        }
        return this.sessionFactory.getCurrentSession()
                .createQuery("FROM Message WHERE created>? AND chatId=?")
                .setParameter(0, lastMessageTimeStamp)
                .setParameter(1, chatId)
                .list();
    }



/*    public Subject getSubjectByName(String name) {
        List<Subject> subjects = this.sessionFactory.getCurrentSession()
                .createQuery("FROM Subject WHERE name=?")
                .setParameter(0, name)//.uniqueResult()
                .list();

        return subjects.size() > 0 ? subjects.get(0) : null;
    }*/
}

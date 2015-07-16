package com.sigmaukraine.messenger.repository;

import com.sigmaukraine.messenger.domain.Chat;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Repository
@Transactional
public class ChatRepository {
    private static Logger LOG = Logger.getLogger(ChatRepository.class.getName());

    @Autowired
    private SessionFactory sessionFactory;

    public List<Chat> listAll() {
        if (LOG.isLoggable(Level.INFO)) {
            LOG.log(Level.INFO, "getting list of all chats");
        }
        return this.sessionFactory.getCurrentSession().createQuery("from Chat")
                .list();
    }

    public void removeChat(int id) {
        Chat contact = (Chat) this.sessionFactory.getCurrentSession().load(Chat.class, id);

        if (null != contact) {
            this.sessionFactory.getCurrentSession().delete(contact);
            if (LOG.isLoggable(Level.INFO)) {
                LOG.log(Level.INFO, "removing the chat");
            }
        }
    }

    public void addChat(Chat chat) {
        this.sessionFactory.getCurrentSession().save(chat);
        if (LOG.isLoggable(Level.INFO)) {
            LOG.log(Level.INFO, "adding a chat");
        }
    }

    public List<Chat> getListChatsBySubjectId (int id) {
        if (LOG.isLoggable(Level.INFO)) {
            LOG.log(Level.INFO, "getting list of chats by subject's id");
        }
        return this.sessionFactory.getCurrentSession()
                .createQuery("FROM Chat WHERE themId=?")
                .setParameter(0, id)
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

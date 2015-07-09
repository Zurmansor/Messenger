package com.sigmaukraine.messenger.repository;

import com.sigmaukraine.messenger.domain.Chat;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class ChatRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public List<Chat> listAll() {
        return this.sessionFactory.getCurrentSession().createQuery("from Chat")
                .list();
    }

    public void removeChat(int id) {
        Chat contact = (Chat) this.sessionFactory.getCurrentSession().load(Chat.class, id);

        if (null != contact) {
            this.sessionFactory.getCurrentSession().delete(contact);
        }
    }

    public void addChat(Chat chat) {
        this.sessionFactory.getCurrentSession().save(chat);
    }

/*    public Subject getSubjectByName(String name) {
        List<Subject> subjects = this.sessionFactory.getCurrentSession()
                .createQuery("FROM Subject WHERE name=?")
                .setParameter(0, name)//.uniqueResult()
                .list();

        return subjects.size() > 0 ? subjects.get(0) : null;
    }*/
}

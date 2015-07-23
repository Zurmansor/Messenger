package com.sigmaukraine.messenger.repository;

import com.sigmaukraine.messenger.domain.Chat;
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
public class ChatRepository {
    private static Logger LOG = Logger.getLogger(ChatRepository.class.getName());

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Returns list of all chats
     * @return List<Chat>
     */
    public List<Chat> listAll() {
        if (LOG.isLoggable(Level.INFO)) {
            LOG.log(Level.INFO, "getting list of all chats");
        }
        return this.sessionFactory.getCurrentSession().createQuery("from Chat")
                .list();
    }

    /**
     * Removes chat by Id if it exist
     * @param id
     */
    public void removeChat(int id) {
        Chat contact = (Chat) this.sessionFactory.getCurrentSession().load(Chat.class, id);

        if (null != contact) {
            this.sessionFactory.getCurrentSession().delete(contact);
            if (LOG.isLoggable(Level.INFO)) {
                LOG.log(Level.INFO, "removing the chat");
            }
        }
    }

    /**
     * Adds chat
     * @param chat
     */
    public void addChat(Chat chat) {
        this.sessionFactory.getCurrentSession().save(chat);
        if (LOG.isLoggable(Level.INFO)) {
            LOG.log(Level.INFO, "adding a chat");
        }
    }

    /**
     * Gets list of chats by subject's Id
     * @param id
     * @return List<Chat>
     */
    public List<Chat> getListChatsBySubjectId (int id) {
        if (LOG.isLoggable(Level.INFO)) {
            LOG.log(Level.INFO, "getting list of chats by subject's id");
        }
        return this.sessionFactory.getCurrentSession()
                .createQuery("FROM Chat WHERE subjectId=?")
                .setParameter(0, id)
                .list();
    }


    /**
     * Checks chat for unique
     * @param name
     * @param checkSubjectId
     * @return boolean
     */
    public boolean isUnique(String name, int checkSubjectId) {
        List<Chat> chats = this.sessionFactory.getCurrentSession()
                .createQuery("FROM Chat WHERE name=? AND subjectId=?")
                .setParameter(0, name)
                .setParameter(1, checkSubjectId)
                .list();
        if (LOG.isLoggable(Level.INFO)) {
            LOG.log(Level.INFO, "checks is chat unique");
        }
        return chats.size() == 0;
    }

    /**
     * Gets chat by Id
     * @param id
     * @return Chat
     */
    public Chat getChatById(int id) {
        if (LOG.isLoggable(Level.INFO)) {
            LOG.log(Level.INFO, "getting chat by id");
        }
        return (Chat) this.sessionFactory.getCurrentSession().get(Chat.class, id);
    }

    /**
     * Edits chat by Id.
     * @param chatId
     * @param updatedChat
     */
    public void editChat(int chatId, Chat updatedChat) {
        Session session = sessionFactory.openSession();
        Chat chat = getChatById(chatId);
            chat.setName(updatedChat.getName());
            chat.setDescription(updatedChat.getDescription());
            session.update(chat);
            if (LOG.isLoggable(Level.INFO)) {
                LOG.log(Level.INFO, "editing chat");
            }
    }


}

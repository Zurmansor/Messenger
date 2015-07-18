package com.sigmaukraine.messenger.repository;

import com.sigmaukraine.messenger.domain.Subject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.transaction.Transaction;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Repository
@Transactional
public class SubjectRepository {
private static Logger LOG = Logger.getLogger(SubjectRepository.class.getName());

    @Autowired
    private SessionFactory sessionFactory;

    public List<Subject> listAll() {
        if (LOG.isLoggable(Level.INFO)) {
            LOG.log(Level.INFO, "getting list of all subjects");
        }
        return this.sessionFactory.getCurrentSession().createQuery("from Subject")
                .list();
    }

    public void removeSubject(int id) {
        Subject contact = (Subject) this.sessionFactory.getCurrentSession().get(Subject.class, id);

        if (null != contact) {
            this.sessionFactory.getCurrentSession().delete(contact);
            if (LOG.isLoggable(Level.INFO)) {
                LOG.log(Level.INFO, "removing the subject");
            }
        }
    }

    public void addSubject(Subject subject) {
        this.sessionFactory.getCurrentSession().save(subject);
        if (LOG.isLoggable(Level.INFO)) {
            LOG.log(Level.INFO, "adding a subject");
        }
    }

    public Subject getSubjectByName(String name) {
        List<Subject> subjects = this.sessionFactory.getCurrentSession()
                .createQuery("FROM Subject WHERE name=?")
                .setParameter(0, name)//.uniqueResult()
                .list();
        if (LOG.isLoggable(Level.INFO)) {
            LOG.log(Level.INFO, "select subject " + name);
        }
        return subjects.size() > 0 ? subjects.get(0) : null;
    }

    public Subject getSubjectById(int id) {
        return (Subject) this.sessionFactory.getCurrentSession().get(Subject.class, id);
    }

    public void editSubject(int subjectId ,Subject updatedSubject) {
        Session session = sessionFactory.openSession();
//        Transaction tr = (Transaction) session.beginTransaction();
        Subject subject = getSubjectById(subjectId);
        subject.setName(updatedSubject.getName());
        subject.setDescription(updatedSubject.getDescription());
        session.update(subject);
    }
}

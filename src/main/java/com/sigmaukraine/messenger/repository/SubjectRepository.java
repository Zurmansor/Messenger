package com.sigmaukraine.messenger.repository;

import com.sigmaukraine.messenger.domain.Subject;
import com.sigmaukraine.messenger.domain.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class SubjectRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public List<Subject> listAll() {
        return this.sessionFactory.getCurrentSession().createQuery("from Subject")
                .list();
    }

    public void removeSubject(int id) {
        Subject contact = (Subject) this.sessionFactory.getCurrentSession().load(Subject.class, id);

        if (null != contact) {
            this.sessionFactory.getCurrentSession().delete(contact);
        }
    }

    public void addSubject(Subject subject) {
        this.sessionFactory.getCurrentSession().save(subject);
    }

    public Subject getSubjectByName(String name) {
        List<Subject> subjects = this.sessionFactory.getCurrentSession()
                .createQuery("FROM Subject WHERE name=?")
                .setParameter(0, name)
                .list();

        return subjects.size() > 0 ? subjects.get(0) : null;
    }
}

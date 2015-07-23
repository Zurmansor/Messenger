package com.sigmaukraine.messenger.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "messages")
public class Message {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private Integer id;

    @Column(name = "text")
    private String text;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "created")
    private Timestamp created;

    @Column(name = "chat_id")
    private Integer chatId;

//    @ManyToOne
//    private User user;

    @ManyToOne()
    @JoinColumn(name = "user_id", nullable = false, insertable = false, updatable = false)
    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Integer getChatId() {
        return chatId;
    }

    public void setChatId(Integer chatId) {
        this.chatId = chatId;
    }

    public User getUser() {
        return user;
    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
}

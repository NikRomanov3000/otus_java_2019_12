package ru.otus.hw10.core.model;

import javax.persistence.*;

@Entity
@Table(name = "phone")
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private long id;

    @Column(name = "phone")
    private String phone;

    @ManyToOne
    private User user;

    public Phone() {
    }

    public Phone(String phone_number) {
        this.phone = phone_number;
    }

    public long getId() {
        return id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone_number) {
        this.phone = phone_number;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

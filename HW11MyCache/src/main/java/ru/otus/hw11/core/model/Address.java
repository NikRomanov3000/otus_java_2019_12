package ru.otus.hw11.core.model;

import javax.persistence.*;

@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "full_address")
    private String full_address;

    @OneToOne(mappedBy = "address")
    private User user;

    public Address() {
    }

    public Address(String full_address) {
        this.full_address = full_address;
    }


    public long getId() {
        return id;
    }

    public String getFull_address() {
        return full_address;
    }

    public void setFull_address(String full_address) {
        this.full_address = full_address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

package ru.otus.hw10.core.model;

import javax.persistence.*;

@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private long id;

    @Column(name = "full_address")
    private String full_address;

//    @OneToOne(mappedBy = "full_address")
//    private User user;

    public long getId() {
        return id;
    }

    public String getFull_address() {
        return full_address;
    }

    public void setFull_address(String full_address) {
        this.full_address = full_address;
    }

    public Address() {
    }

    public Address(String full_address) {
        this.full_address = full_address;
    }


}

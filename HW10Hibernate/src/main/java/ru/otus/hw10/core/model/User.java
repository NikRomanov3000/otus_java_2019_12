package ru.otus.hw10.core.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "app_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @OneToOne(targetEntity = Address.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(targetEntity = Phone.class, mappedBy = "phone", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Phone> phones;

    public User() {
    }

    public User(long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = null;
        this.phones = new HashSet<>();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Address getUserAddress() {
        return address;
    }

    public void setUserAddress(Address userAddress) {
        this.address = userAddress;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<Phone> getUserPhones() {
        return phones;
    }

    public void addUserPhones(Phone phone) {
        phones.add(phone);
    }
}

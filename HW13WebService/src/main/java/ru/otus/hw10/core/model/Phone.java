package ru.otus.hw10.core.model;

import javax.persistence.*;

@Entity
@Table(name = "phone")
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "phone")
    private String phone;

    @ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL)
    @JoinColumn(name="r_user_id")
    private User app_user;

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
        return app_user;
    }

    public void setUser(User user) {
        this.app_user = user;
    }
}

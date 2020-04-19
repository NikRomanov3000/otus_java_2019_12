package ru.otus.webserviceHW.services;

public interface UserAuthService {
    boolean authenticate(String login, String password);
}

package ru.otus.hw15MS.messagesystem;


import java.util.Optional;

public interface RequestHandler {
    Optional<Message> handle(Message msg);
}

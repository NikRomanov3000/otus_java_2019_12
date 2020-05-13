package ru.otus.hw15MS.dbhandler;

import ru.otus.core.model.User;
import ru.otus.core.service.DbServiceUser;
import ru.otus.hw15MS.Serializers;
import ru.otus.hw15MS.messagesystem.Message;
import ru.otus.hw15MS.messagesystem.MessageType;
import ru.otus.hw15MS.messagesystem.RequestHandler;

import java.util.List;
import java.util.Optional;

public class GetAllUsersDataRequestHandler implements RequestHandler {
    private final DbServiceUser dbService;

    public GetAllUsersDataRequestHandler(DbServiceUser dbService) {
        this.dbService = dbService;
    }

    @Override
    public Optional<Message> handle(Message msg) {
        List<User> data = dbService.getAllUsers();
        return Optional.of(new Message(msg.getTo(), msg.getFrom(), msg.getId(), MessageType.ALL_USERS_DATA.getValue(), Serializers.serialize(data)));
    }
}

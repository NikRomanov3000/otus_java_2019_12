package ru.otus.hw15MS.dbhandler;

import ru.otus.core.service.DbServiceUser;
import ru.otus.hw15MS.Serializers;
import ru.otus.hw15MS.messagesystem.Message;
import ru.otus.hw15MS.messagesystem.MessageType;
import ru.otus.hw15MS.messagesystem.RequestHandler;

import java.util.Optional;

public class GetUserDataRequestHandler implements RequestHandler {
    private final DbServiceUser dbService;

    public GetUserDataRequestHandler(DbServiceUser dbService) {
        this.dbService = dbService;
    }

    @Override
    public Optional<Message> handle(Message msg) {
        long id = Serializers.deserialize(msg.getPayload(), Long.class);
        Optional data = dbService.getUserById(id);
        return Optional.of(new Message(msg.getTo(), msg.getFrom(), msg.getId(), MessageType.USER_DATA.getValue(), Serializers.serialize(data)));
    }
}

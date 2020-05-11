package ru.otus.hw15MS.front.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.core.model.User;
import ru.otus.hw15MS.Serializers;
import ru.otus.hw15MS.front.FrontendService;
import ru.otus.hw15MS.messagesystem.Message;
import ru.otus.hw15MS.messagesystem.MessageType;
import ru.otus.hw15MS.messagesystem.RequestHandler;

import java.util.Optional;
import java.util.UUID;

public class PostUsersDataRequestHandler implements RequestHandler {
    private static final Logger logger = LoggerFactory.getLogger(GetUserDataResponseHandler.class);

    private final FrontendService frontendService;

    public PostUsersDataRequestHandler(FrontendService frontendService) {
        this.frontendService = frontendService;
    }

    @Override
    public Optional<Message> handle(Message msg) {
        User userData = Serializers.deserialize(msg.getPayload(), User.class);
        UUID sourceMessageId = msg.getSourceMessageId().orElseThrow(() -> new RuntimeException("Not found sourceMsg for message:" + msg.getId()));
        frontendService.takeConsumer(sourceMessageId, User.class).ifPresent(consumer -> consumer.accept(userData));
        return Optional.of(new Message(msg.getTo(), msg.getFrom(), msg.getId(), MessageType.ADD_USER.getValue(), Serializers.serialize(userData)));
    }
}

package ru.otus.hw15MS.dbhandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.core.model.User;
import ru.otus.core.service.DbServiceUser;
import ru.otus.hw15MS.Serializers;
import ru.otus.hw15MS.front.handlers.GetUserDataResponseHandler;
import ru.otus.hw15MS.messagesystem.Message;
import ru.otus.hw15MS.messagesystem.MessageType;
import ru.otus.hw15MS.messagesystem.RequestHandler;

import java.util.Optional;
import java.util.UUID;

public class PostUsersDataResponseHandler implements RequestHandler {
    private static final Logger logger = LoggerFactory.getLogger(GetUserDataResponseHandler.class);
    private final DbServiceUser dbService;

    public PostUsersDataResponseHandler(DbServiceUser dbService) {
        this.dbService = dbService;
    }

    @Override
    public Optional<Message> handle(Message msg) {
        logger.info("new message:{}", msg);
        try{
            User userData =  Serializers.deserialize(msg.getPayload(), User.class);
            UUID sourceMessageId = msg.getSourceMessageId().orElseThrow(() -> new RuntimeException("Not found sourceMsg for message:" + msg.getId()));
            dbService.saveUser(userData);
            return Optional.of(new Message(msg.getTo(), msg.getFrom(), msg.getId(), MessageType.ADD_USER.getValue(), Serializers.serialize(userData.getId())));
        }catch (Exception ex){
            logger.error("msg:" + msg, ex);
            return Optional.of(new Message(msg.getTo(), msg.getFrom(), msg.getId(), MessageType.ADD_USER.getValue(), Serializers.serialize(ex)));
        }
    }
}

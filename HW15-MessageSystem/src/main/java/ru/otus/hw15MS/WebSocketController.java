package ru.otus.hw15MS;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import ru.otus.hw15MS.messagesystem.Message;

public class WebSocketController {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketController.class);

    @MessageMapping("/message.{roomId}")
    @SendTo("/topic/response.{roomId}")
    public Message getUsers(){
        logger.info("got users exec");
        return new Message();
    }

    //  @MessageMapping("/message.{roomId}")
    //  @SendTo("/topic/response.{roomId}")
    //  public Message getMessage(@DestinationVariable String roomId, Message message) {
    //    logger.info("got message:{}, roomId:{}", message, roomId);
    //    return new Message(HtmlUtils.htmlEscape(message.getMessageStr()));
    //  }
}

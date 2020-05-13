package ru.otus.hw15MS;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.otus.core.model.User;
import ru.otus.hw15MS.front.FrontendService;
import ru.otus.hw15MS.messagesystem.Message;

@Controller
public class WebSocketController {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketController.class);

   private final FrontendService frontendService;

    public WebSocketController(FrontendService frontendService) {
        this.frontendService = frontendService;
    }

    @MessageMapping("/api")
    @SendTo("/user")
    @RequestMapping(method = RequestMethod.GET)
    public Message getUsersData(){
        logger.info("GET /api/user");
         return frontendService.getAllUserData();
    }

    @MessageMapping("/api")
    @SendTo("/user")
    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public  Message getUserData(@PathVariable long userId){
        logger.info("GET /api/user/"+userId);
        return frontendService.getUserData(userId, );
    }

    @MessageMapping("/api")
    @SendTo("/user")
    @RequestMapping(method = RequestMethod.POST)
    public  Message saveUserData(@RequestBody User user){
        logger.info("POST /api/user");
        return frontendService.saveUser(user,);
    }


}

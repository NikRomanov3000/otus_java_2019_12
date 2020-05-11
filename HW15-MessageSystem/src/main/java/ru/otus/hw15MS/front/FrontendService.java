package ru.otus.hw15MS.front;


import ru.otus.core.model.User;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;

public interface FrontendService {
    void getUserData(long userId, Consumer<String> dataConsumer);
    void getAllUserData (Consumer<String> dataConsumer);
    void saveUser(User user, Consumer<String> dataConsumer);

    <T> Optional<Consumer<T>> takeConsumer(UUID sourceMessageId, Class<T> tClass);
}


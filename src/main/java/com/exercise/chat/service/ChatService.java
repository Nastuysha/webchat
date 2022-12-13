package com.exercise.chat.service;

import com.exercise.chat.entities.Message;
import com.exercise.chat.entities.User;

public interface ChatService {
    void sendMessage(Message message);

    User addUser(User user);

    Iterable<Message> findAllMessages();

    Iterable<User> findAllUsers();
}

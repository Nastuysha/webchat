package com.exercise.chat.service.impl;

import com.exercise.chat.entities.Message;
import com.exercise.chat.entities.User;
import com.exercise.chat.repositories.MessageRepository;
import com.exercise.chat.repositories.UserRepository;
import com.exercise.chat.service.ChatService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService {

    private static final Logger log = LoggerFactory.getLogger(ChatServiceImpl.class);
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd:MM:yyyy HH:mm:ss");

    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    @Autowired
    public ChatServiceImpl(UserRepository userRepository, MessageRepository messageRepository) {
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
    }

    @Override
    public void sendMessage(Message message) {
        message.setMessageTime(LocalDateTime.now().format(DATE_TIME_FORMATTER));
        messageRepository.save(message);
    }

    @Override
    public User addUser(User user) {
        log.info(String.format("Adding user %s, to the repository", user.getName()));
        return userRepository.save(user);
    }

    @Override
    public Iterable<Message> findAllMessages() {
        return messageRepository.findAll();
    }

    @Override
    public Iterable<User> findAllUsers() {
        return userRepository.findAll();
    }
}
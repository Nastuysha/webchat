package com.exercise.chat.controllers;

import com.exercise.chat.entities.Color;
import com.exercise.chat.entities.Message;
import com.exercise.chat.entities.User;
import com.exercise.chat.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ChatController {

    private final ChatService chatService;

    @Autowired
    public ChatController(
        ChatService chatService
    ) {
        this.chatService = chatService;
    }

    @GetMapping("/hello")
    public String showHelloForm() {
        return "hello";
    }

    @GetMapping("/signup")
    public String showSignUpForm(User user) {
        return "add-user";
    }

    @GetMapping("/signin")
    public String showSignInForm(User user) {
        return "auth-user";
    }

    @PostMapping("/adduser")
    public String addUser(User user, Model model, HttpServletRequest request) {
        try {
            User savedUser = chatService.addUser(user);
            request.getSession().setAttribute("userName", savedUser.getName());
            request.getSession().setAttribute("color", savedUser.getColor());
            request.getSession().setAttribute("password", savedUser.<String>getPassword());
            model.addAttribute("users", chatService.findAllUsers());
        } catch (Exception e) {
            model.addAttribute("error", "That user name already exist, please authorized!");
            return "auth-user";
        }

        return "redirect:/";
    }

    @PostMapping("/authuser")
    public String authUser(User user, Model model, HttpServletRequest request) {
        try {
            User savedUser = chatService.addUser(user);
            request.getSession().setAttribute("userName", savedUser.getName());
            request.getSession().setAttribute("color", savedUser.getColor());
            request.getSession().setAttribute("password", savedUser.<String>getPassword());
            model.addAttribute("users", chatService.findAllUsers());
        } catch (Exception e) {
            return "redirect:/";
        }
        model.addAttribute("error", "This user is not registered yet, please register!");
        return "add-user";
    }

    @PostMapping("/send")
    public String sendMessage(@RequestParam String textMessage, HttpServletRequest request) {
        Message message = new Message();
        message.setTextString(textMessage);

        message.setUserName(String.valueOf(request.getSession().getAttribute("userName")));
        message.setColor((Color) request.getSession().getAttribute("color"));
        chatService.sendMessage(message);
        return "redirect:/";
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("users", chatService.findAllUsers());
        model.addAttribute("messages", chatService.findAllMessages());
        return "index";
    }
}

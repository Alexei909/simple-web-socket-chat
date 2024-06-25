package com.example.ws_chat.user;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @MessageMapping("/user.addUser")
    @SendTo("/user/topic")
    public User saveUser(
        @Payload User user
    ) {
        this.userService.saveUser(user);
        return user;
    }

    @MessageMapping("/user.disconectUser")
    @SendTo("/user/topic")
    public User disconect(
        @Payload User user
    ) {
        this.userService.disconect(user);
        return user;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> findConnectedUsers() {
        List<User> users = this.userService.findConnectedUsers();
        return ResponseEntity.ok(users);
    }
}

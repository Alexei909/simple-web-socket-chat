package com.example.ws_chat.user;

import java.util.List;

public interface UserService {

    void saveUser(User user);
    void disconect(User user);
    List<User> findConnectedUsers();

}

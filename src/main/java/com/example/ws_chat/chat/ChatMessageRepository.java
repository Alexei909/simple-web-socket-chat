package com.example.ws_chat.chat;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ChatMessageRepository extends CrudRepository<ChatMessage, String> {

    List<ChatMessage> findByChatId(String chatId);

}   

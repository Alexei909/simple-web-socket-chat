package com.example.ws_chat.chat;

import java.util.List;

public interface ChatMessageService {

    ChatMessage save(ChatMessage chatMessage);
    List<ChatMessage> findChatMessages(String senderId, String recipientId);
    
}

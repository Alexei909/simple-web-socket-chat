package com.example.ws_chat.chat;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ws_chat.chatroom.ChatRoomService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ChatMessageServiceImpl implements ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomService chatRoomService;

    @Override
    public ChatMessage save(ChatMessage chatMessage) {
        String chatId = this.chatRoomService.getChatRoomId(
                chatMessage.getSenderId(), 
                chatMessage.getRecipientId(), 
                true
        ).orElseThrow(() -> new RuntimeException("ChatRoom not found"));
        
        chatMessage.setChatId(chatId);
        return this.chatMessageRepository.save(chatMessage);
    }

    @Override
    public List<ChatMessage> findChatMessages(String senderId, String recipientId) {
        String chatId = this.chatRoomService.getChatRoomId(
                senderId, 
                recipientId, 
                false
        ).orElseThrow(() -> new RuntimeException("ChatRoom not found"));

        return this.chatMessageRepository.findByChatId(chatId);
    }

}

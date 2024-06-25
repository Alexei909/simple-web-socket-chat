package com.example.ws_chat.chatroom;

import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ChatRoomServiceImpl implements ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    
    /**
     * Метод возвращает CharRoom. В случае если createNewRoomIfNotExists true
     * создается новйы объект ChatRoom.
     * 
     * @param senderId                 Индентификатор отправителя.
     * @param recipientId              Индентификатор получателя.
     * @param createNewRoomIfNotExists Флаг 
     */
    @Override
    public Optional<String> getChatRoomId(
            String senderId,
            String recipientId,
            boolean createNewRoomIfNotExists
    ) { 
        return this.chatRoomRepository
                .findBySenderIdAndRecipientId(senderId, recipientId)
                .map(ChatRoom::getChatId)
                .or(() -> {
                    if (createNewRoomIfNotExists) {
                        String chatId = this.createChatId(senderId, recipientId);
                        return Optional.of(chatId);
                    }

                    return Optional.empty();
                });
    }

    private String createChatId(String senderId, String recipientId) {
        String chatId = String.format("%s_%s", senderId, recipientId);

        ChatRoom senderRecipient = ChatRoom.builder()
                .chatId(chatId)
                .senderId(senderId)
                .recipientId(recipientId)
                .build();
        
        ChatRoom recipientSecnder = ChatRoom.builder()
                .chatId(chatId)
                .senderId(recipientId)
                .recipientId(senderId)
                .build();
        
        this.chatRoomRepository.save(senderRecipient);
        this.chatRoomRepository.save(recipientSecnder);
        return chatId;
    }

}

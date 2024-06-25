package com.example.ws_chat.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.messaging.converter.DefaultContentTypeResolver;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(@NonNull MessageBrokerRegistry registry) {
        // .enableSimpleBroker конфигурирует простой брокер сообщений в памяти
        registry.enableSimpleBroker("/user");

        // .setApplicationDestinationPrefixes особого смысла не несет.
        // Добавляет префикс для MessageMapping
        registry.setApplicationDestinationPrefixes("/app");

        // .setUserDestinationPrefix используется для идентификации пользовательских
        // направлений в брокере сообщений, позволяя пользователю подписываться
        // на уникальные в рамках его сессии очереди
        registry.setUserDestinationPrefix("/user");
    }

    @Override
    public void registerStompEndpoints(@NonNull StompEndpointRegistry registry) {
        // .addEndpoint определяет точку через которую клиенты будут полключаться к STOMP-серверу
        // .withSockJs - это резервный SockJS, который будет использоваться в случае если
        // websocket будет не доступен
        registry.addEndpoint("/ws")
                .withSockJS();
    }

    /**
     * Метод используется для настройки конвертеров сообщений.
     * Он позволяет заменить или добавить конвертеры сообщений по умолчанию.
     * 
     * @param messageConverters Список определенных конвертеров сообщений по умолчанию.
     */
    @Override
    public boolean configureMessageConverters(@NonNull List<MessageConverter> messageConverters) {
        // Это конвертер по умолчанию, который проверяет наличие заголовка MessageHeaders.CONTENT_TYPE
        DefaultContentTypeResolver resolver = new DefaultContentTypeResolver();

        // .setDefaultMimeType устонавливает значание по умолчанию, при отсутствии заголовка Content-Type
        resolver.setDefaultMimeType(MimeTypeUtils.APPLICATION_JSON);

        // конвертер сообщений
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setObjectMapper(new ObjectMapper());
        converter.setContentTypeResolver(resolver);
        messageConverters.add(converter);
        return false;
    }

}

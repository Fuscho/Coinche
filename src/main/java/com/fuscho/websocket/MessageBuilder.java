package com.fuscho.websocket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fuscho.model.notification.Event;
import com.fuscho.model.notification.EventHeaders;

import java.util.HashMap;
import java.util.Map;

public class MessageBuilder {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static Message message(Event event){
        String content;
        try {
            content = objectMapper.writeValueAsString(event);
        } catch (JsonProcessingException e) {
            content = null;
        }
        Map<String,Object> headers = new HashMap<>();
        headers.put(EventHeaders.EVENT_TYPE, event.getClass().getCanonicalName().toString());
        return Message.builder()
                .content(content)
                .headers(headers)
                .build();
    }

}

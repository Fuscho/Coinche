package com.fuscho.websocket;

import lombok.Builder;
import lombok.Value;

import java.util.Map;

/**
 * Créer par mchoraine le 04/03/2016.
 */
@Value
@Builder
public class Message {
    String content;
    Map<String, Object> headers;

    public Object getHeader(String header){
        return headers.get(header);
    }

    public <T> T getHeader(String header, Class<T> resultClass){
        return (resultClass.cast( headers.get(header)));
    }
}

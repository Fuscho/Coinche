package com.fuscho.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Créer par mchoraine le 04/03/2016.
 */
@Service
@Slf4j
public class StompMessagingService implements ApplicationListener<ContextStartedEvent> {

    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public StompMessagingService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Async
    public Void send(Message msg) {
        this.messagingTemplate.convertAndSend("/topic/notifications", msg);
        return null;
    }

    @Override
    public void onApplicationEvent(ContextStartedEvent event) {
        log.info("Configuration du system de messagerie. Init des abonnements");
    }
}

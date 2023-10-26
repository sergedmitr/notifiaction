package ru.sergdm.ws.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import ru.sergdm.ws.model.Notification;
import ru.sergdm.ws.service.NotificationService;

@Service
public class NotificationListener {
    @Autowired
    NotificationService notificationService;

    private static final Logger LOG = LoggerFactory.getLogger(NotificationListener.class);

    @KafkaListener(topics = "${app.topic.example}")
    public void receive(@Payload Notification data,
                        @Headers MessageHeaders headers) {
        LOG.info("received data='{}'", data);

        notificationService.addNotification(data);
        headers.keySet().forEach(key -> {
            LOG.info("{}: {}", key, headers.get(key));
        });
    }

}

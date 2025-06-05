package com.example.notifications.consumer;

import com.example.notifications.model.CrudEventMessage;
import com.example.notifications.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Consumer for CRUD event messages from RabbitMQ.
 * Listens for messages on the configured queue and processes them.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class CrudEventConsumer {

    private final NotificationService notificationService;

    /**
     * Processes CRUD event messages from the queue.
     * Creates a notification for each message received.
     *
     * @param message The CRUD event message
     */
    @RabbitListener(queues = "${rabbitmq.queue.name:crud-events-queue}")
    public void handleCrudEvent(CrudEventMessage message) {
        log.info("Received CRUD event message: {} operation on {} with ID {}, status: {}", 
                message.getOperation(), message.getEntityType(), message.getEntityId(), 
                message.getStatus() != null ? message.getStatus() : "PUBLISHED");

        try {
            // Simulate processing delay
            log.info("Processing message, please wait...");
            try {
                TimeUnit.SECONDS.sleep(3); // 3-second delay to simulate processing time
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.error("Processing delay interrupted", e);
            }

            // Update message status to CONSUMED
            message.setStatus("CONSUMED");
            log.info("Message status updated to: {}", message.getStatus());

            // Create notification
            notificationService.createNotification(message);
            log.info("Successfully processed CRUD event message with ID: {}, status: {}", 
                    message.getEntityId(), message.getStatus());
        } catch (Exception e) {
            log.error("Error processing CRUD event message", e);
        }
    }
}

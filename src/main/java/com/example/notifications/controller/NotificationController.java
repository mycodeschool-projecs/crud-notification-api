package com.example.notifications.controller;

import com.example.notifications.model.Notification;
import com.example.notifications.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for notifications.
 * Provides endpoints for retrieving and managing notifications.
 */
@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
@Slf4j
public class NotificationController {

    private final NotificationService notificationService;

    /**
     * Get all notifications
     */
    @GetMapping
    public ResponseEntity<List<Notification>> getAllNotifications() {
        log.info("Getting all notifications");
        return ResponseEntity.ok(notificationService.getAllNotifications());
    }

    /**
     * Get notifications by read status
     */
    @GetMapping("/status/{read}")
    public ResponseEntity<List<Notification>> getNotificationsByReadStatus(@PathVariable boolean read) {
        log.info("Getting notifications with read status: {}", read);
        return ResponseEntity.ok(notificationService.getNotificationsByReadStatus(read));
    }

    /**
     * Get notifications by source service
     */
    @GetMapping("/service/{sourceService}")
    public ResponseEntity<List<Notification>> getNotificationsBySourceService(@PathVariable String sourceService) {
        log.info("Getting notifications from service: {}", sourceService);
        return ResponseEntity.ok(notificationService.getNotificationsBySourceService(sourceService));
    }

    /**
     * Get notifications by entity type
     */
    @GetMapping("/entity-type/{entityType}")
    public ResponseEntity<List<Notification>> getNotificationsByEntityType(@PathVariable String entityType) {
        log.info("Getting notifications for entity type: {}", entityType);
        return ResponseEntity.ok(notificationService.getNotificationsByEntityType(entityType));
    }

    /**
     * Get notifications by operation
     */
    @GetMapping("/operation/{operation}")
    public ResponseEntity<List<Notification>> getNotificationsByOperation(@PathVariable String operation) {
        log.info("Getting notifications for operation: {}", operation);
        return ResponseEntity.ok(notificationService.getNotificationsByOperation(operation));
    }

    /**
     * Get notifications by entity ID
     */
    @GetMapping("/entity-id/{entityId}")
    public ResponseEntity<List<Notification>> getNotificationsByEntityId(@PathVariable String entityId) {
        log.info("Getting notifications for entity ID: {}", entityId);
        return ResponseEntity.ok(notificationService.getNotificationsByEntityId(entityId));
    }

    /**
     * Mark a notification as read
     */
    @PutMapping("/{id}/mark-read")
    public ResponseEntity<Notification> markAsRead(@PathVariable Long id) {
        log.info("Marking notification with ID {} as read", id);
        Notification notification = notificationService.markAsRead(id);
        if (notification != null) {
            log.info("User read notification: {} operation on {} with ID {}", 
                    notification.getOperation(), notification.getEntityType(), notification.getEntityId());
            return ResponseEntity.ok(notification);
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Mark all notifications as read
     */
    @PutMapping("/mark-all-read")
    public ResponseEntity<Void> markAllAsRead() {
        log.info("Marking all notifications as read");
        List<Notification> markedNotifications = notificationService.markAllAsRead();

        if (!markedNotifications.isEmpty()) {
            log.info("User read {} notifications", markedNotifications.size());
            markedNotifications.forEach(notification -> 
                log.info("User read notification: {} operation on {} with ID {}", 
                    notification.getOperation(), notification.getEntityType(), notification.getEntityId())
            );
        } else {
            log.info("No unread notifications to mark as read");
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}

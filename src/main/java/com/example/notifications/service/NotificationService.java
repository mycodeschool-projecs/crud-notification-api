package com.example.notifications.service;

import com.example.notifications.model.CrudEventMessage;
import com.example.notifications.model.Notification;
import com.example.notifications.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

    private final NotificationRepository notificationRepository;

    /**
     * Creates a notification from a CRUD event message
     */
    @Transactional
    public Notification createNotification(CrudEventMessage message) {
        log.info("Creating notification for {} operation on {} with ID {}", 
                message.getOperation(), message.getEntityType(), message.getEntityId());

        Notification notification = Notification.builder()
                .operation(message.getOperation())
                .entityType(message.getEntityType())
                .entityId(message.getEntityId())
                .details(message.getDetails())
                .sourceService(message.getSourceService())
                .timestamp(message.getTimestamp() != null ? message.getTimestamp() : new Date())
                .read(false)
                .build();

        return notificationRepository.save(notification);
    }

    /**
     * Gets all notifications ordered by timestamp (newest first)
     */
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    /**
     * Gets notifications by read status
     */
    public List<Notification> getNotificationsByReadStatus(boolean read) {
        return notificationRepository.findByReadOrderByTimestampDesc(read);
    }

    /**
     * Gets notifications by source service
     */
    public List<Notification> getNotificationsBySourceService(String sourceService) {
        return notificationRepository.findBySourceServiceOrderByTimestampDesc(sourceService);
    }

    /**
     * Gets notifications by entity type
     */
    public List<Notification> getNotificationsByEntityType(String entityType) {
        return notificationRepository.findByEntityTypeOrderByTimestampDesc(entityType);
    }

    /**
     * Gets notifications by operation type
     */
    public List<Notification> getNotificationsByOperation(String operation) {
        return notificationRepository.findByOperationOrderByTimestampDesc(operation);
    }

    /**
     * Gets notifications by entity ID
     */
    public List<Notification> getNotificationsByEntityId(String entityId) {
        return notificationRepository.findByEntityIdOrderByTimestampDesc(entityId);
    }

    /**
     * Marks a notification as read
     */
    @Transactional
    public Notification markAsRead(Long id) {
        Optional<Notification> notificationOpt = notificationRepository.findById(id);
        if (notificationOpt.isPresent()) {
            Notification notification = notificationOpt.get();
            notification.setRead(true);
            return notificationRepository.save(notification);
        }
        return null;
    }

    /**
     * Marks all notifications as read
     * @return the list of notifications that were marked as read
     */
    @Transactional
    public List<Notification> markAllAsRead() {
        List<Notification> unreadNotifications = notificationRepository.findByReadOrderByTimestampDesc(false);
        unreadNotifications.forEach(notification -> notification.setRead(true));
        return notificationRepository.saveAll(unreadNotifications);
    }
}

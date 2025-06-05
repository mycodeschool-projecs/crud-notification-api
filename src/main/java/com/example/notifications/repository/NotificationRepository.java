package com.example.notifications.repository;

import com.example.notifications.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    
    // Find notifications by read status
    List<Notification> findByReadOrderByTimestampDesc(boolean read);
    
    // Find notifications by source service
    List<Notification> findBySourceServiceOrderByTimestampDesc(String sourceService);
    
    // Find notifications by entity type
    List<Notification> findByEntityTypeOrderByTimestampDesc(String entityType);
    
    // Find notifications by operation type
    List<Notification> findByOperationOrderByTimestampDesc(String operation);
    
    // Find notifications by entity ID
    List<Notification> findByEntityIdOrderByTimestampDesc(String entityId);
}
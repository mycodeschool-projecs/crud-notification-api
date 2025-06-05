package com.example.notifications.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "notifications")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String operation; // CREATE, UPDATE, DELETE

    @Column(nullable = false)
    private String entityType; // The type of entity that was modified (e.g., "User", "Product")

    @Column(nullable = false)
    private String entityId; // The ID of the entity that was modified

    @Column(length = 1000)
    private String details; // Additional details about the operation

    @Column(nullable = false)
    private String sourceService; // The service that performed the operation

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date timestamp;

    @Column(name = "is_read", nullable = false)
    private boolean read;
}

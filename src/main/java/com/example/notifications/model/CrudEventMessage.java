package com.example.notifications.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * Data Transfer Object for CRUD event messages sent via RabbitMQ.
 * This class is used for communication between services.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CrudEventMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Type of CRUD operation (CREATE, UPDATE, DELETE)
     */
    private String operation;

    /**
     * Type of entity that was modified (e.g., "User", "Product")
     */
    private String entityType;

    /**
     * ID of the entity that was modified
     */
    private String entityId;

    /**
     * Additional details about the operation
     */
    private String details;

    /**
     * The service that performed the operation
     */
    private String sourceService;

    /**
     * When the operation occurred
     */
    private Date timestamp;

    /**
     * Status of the message (PUBLISHED, CONSUMED)
     */
    private String status;
}

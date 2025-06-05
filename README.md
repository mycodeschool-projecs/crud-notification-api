# CRUD Notifications API

## Overview
The CRUD Notifications API is a microservice responsible for managing system notifications. It tracks and provides information about actions performed across the system, allowing users to stay informed about changes to entities.

## Key Features
- Get all notifications
- Filter notifications by:
  - Read status
  - Source service
  - Entity type
  - Operation
  - Entity ID
- Mark notifications as read (individually or all at once)
- Track operations performed on entities across the system

## Service Interactions
- **Command API**: Receives notifications about client data operations
- **Auth API**: May receive notifications about user authentication events
- **Client Application**: The frontend client application displays notifications to users

## Technologies
- Spring Boot
- RESTful API
- Database for notification storage

## Running the Service
### Prerequisites
- Java 11 or higher
- Maven
- Database (configured in application properties)

### Local Development
1. Clone the repository
2. Navigate to the crud-notifications-api directory
3. Run `mvn spring-boot:run`
4. The service will be available at http://localhost:8082

### Docker
The service can also be run using Docker:
```
docker build -t crud-notifications-api .
docker run -p 8082:8082 crud-notifications-api
```

### Kubernetes
Kubernetes deployment configurations are available in the crud-app-k8s directory.

## API Endpoints
- **GET /api/notifications**: Get all notifications
- **GET /api/notifications/status/{read}**: Get notifications by read status
- **GET /api/notifications/service/{sourceService}**: Get notifications by source service
- **GET /api/notifications/entity-type/{entityType}**: Get notifications by entity type
- **GET /api/notifications/operation/{operation}**: Get notifications by operation
- **GET /api/notifications/entity-id/{entityId}**: Get notifications by entity ID
- **PUT /api/notifications/{id}/mark-read**: Mark a notification as read
- **PUT /api/notifications/mark-all-read**: Mark all notifications as read
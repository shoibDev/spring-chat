# GlobalChat - Real-time Chat Application

A real-time chat application built with Spring Boot and WebSocket technology. This application allows users to join different chat rooms and communicate with other users in real-time.

## Table of Contents

- [Technologies Used](#technologies-used)
- [Features](#features)
- [System Requirements](#system-requirements)
- [Setup and Installation](#setup-and-installation)
- [Running the Application](#running-the-application)
- [Project Structure](#project-structure)
- [Technical Implementation Details](#technical-implementation-details)
- [Database](#database)
- [API Endpoints](#api-endpoints)

## Technologies Used

- **Backend**:
  - Java 17
  - Spring Boot 3.4.5
  - Spring WebSocket
  - Spring Data JPA
  - H2 Database (in-memory)
  - Lombok

- **Frontend**:
  - HTML5
  - CSS3
  - JavaScript
  - Bootstrap 3.3.7
  - jQuery 3.1.1
  - SockJS
  - StompJS

## Features

- Real-time messaging using WebSocket technology
- Multiple chat rooms (General, Tech, Random, Help)
- Persistent message history
- User join/leave notifications
- Responsive design

## System Requirements

- Java Development Kit (JDK) 17 or higher
- Maven 3.6 or higher
- Web browser with JavaScript enabled

## Setup and Installation

1. Clone the repository:
   ```
   git clone <repository-url>
   ```

2. Navigate to the project directory:
   ```
   cd spring-chat
   ```

3. Build the project using Maven:
   ```
   mvn clean install
   ```

## Running the Application

1. Start the application:
   ```
   mvn spring-boot:run
   ```

2. Open a web browser and navigate to:
   ```
   http://localhost:8080
   ```

3. Enter your name and select a chat room to start chatting.

## Project Structure

The project follows a standard Spring Boot application structure:

```
spring-chat/
├── chatapp/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/globalchat/chatapp/
│   │   │   │       ├── configs/            # Configuration classes
│   │   │   │       ├── controller/         # REST and WebSocket controllers
│   │   │   │       ├── domain/
│   │   │   │       │   └── entity/         # JPA entities
│   │   │   │       ├── repositories/       # Spring Data JPA repositories
│   │   │   │       ├── services/           # Service interfaces and implementations
│   │   │   │       └── GlobalChatApplication.java  # Main application class
│   │   │   └── resources/
│   │   │       ├── static/                 # Frontend resources
│   │   │       │   ├── app.js              # Client-side JavaScript
│   │   │       │   ├── index.html          # Main HTML page
│   │   │       │   └── styles.css          # CSS styles
│   │   │       └── application.yml         # Application configuration
│   │   └── test/                           # Test classes
│   └── pom.xml                             # Maven configuration
```

## Technical Implementation Details

### Backend

The application is built using Spring Boot and follows a layered architecture:

1. **Controller Layer**:
   - `RoomController`: Handles REST endpoints for room management
   - `ChatMessageController`: Handles REST endpoints for message history
   - `WebSocketController`: Handles WebSocket communication for real-time messaging

2. **Service Layer**:
   - `RoomService`: Manages chat room operations
   - `ChatMessageService`: Manages chat message operations

3. **Repository Layer**:
   - `RoomRepository`: Data access for Room entities
   - `ChatMessageRepository`: Data access for ChatMessage entities

4. **Entity Layer**:
   - `Room`: Represents a chat room
   - `ChatMessage`: Represents a message with types (CHAT, JOIN, LEAVE)

5. **WebSocket Configuration**:
   - Configures STOMP over WebSocket
   - Sets up message broker and endpoints

### Frontend

The frontend is built with HTML, CSS, and JavaScript:

1. **HTML**: Provides the structure for the chat interface
2. **CSS**: Styles the application with a modern, responsive design
3. **JavaScript**: Handles user interactions and WebSocket communication

## Database

The application uses an H2 in-memory database with the following configuration:

- Database URL: `jdbc:h2:mem:globalchatdb`
- Console URL: `http://localhost:8080/h2-console`
- Username: `sa`
- Password: (empty)

The database schema is automatically created and updated by Hibernate based on the entity classes.

## API Endpoints

### REST Endpoints

- `GET /api/rooms`: Get all available chat rooms
- `GET /api/chat/room/{roomId}/history`: Get message history for a specific room

### WebSocket Endpoints

- `/app/chat`: Send a message to all users
- `/app/room/{roomId}`: Send a message to a specific room
- `/topic/messages`: Subscribe to receive all messages
- `/topic/room/{roomId}`: Subscribe to receive messages from a specific room
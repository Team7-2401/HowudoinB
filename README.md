# Howudoin Backend

Welcome to the **Howudoin Backend** repository! This project is the backend implementation of a messaging application, similar to WhatsApp, developed using Java and Spring Boot. It provides robust and scalable APIs for user authentication, friend management, group messaging, and real-time communication.

---

## 🚀 Features

### Functionalities
- **User Registration & Login**
  - Secure user authentication with JWT tokens.
- **Friend Request System**
  - Send, accept, and manage friend requests.
- **Real-Time Messaging**
  - Send and receive messages with friends.
- **Group Messaging**
  - Create groups, manage members, and send group messages.

### Database
- **MongoDB**
  - Document-based storage for users, groups, and messages.
  - Collections include:
    - `Users`
    - `Groups`
    - `Messages`

### Security
- JWT-based authentication and authorization mechanisms.

---

## 🛠️ Technologies Used

- **Language**: Java
- **Framework**: Spring Boot
- **Database**: MongoDB (NoSQL)
- **Authentication**: JWT

---

## 📜 API Documentation

### Public Endpoints
- **POST /register**: Register a new user
  - Request Body: `{ "name": "John", "lastName": "Doe", "email": "example@example.com", "password": "password123" }`
- **POST /login**: Authenticate a user
  - Request Body: `{ "email": "example@example.com", "password": "password123" }`

### Secure Endpoints
- **Friend Management**
  - **POST /friends/add**: Send a friend request
  - **POST /friends/accept**: Accept a friend request
  - **GET /friends**: Retrieve the friend list
- **Messaging**
  - **POST /messages/send**: Send a message to a friend
  - **GET /messages**: Retrieve conversation history
- **Group Management**
  - **POST /groups/create**: Create a new group
  - **POST /groups/:groupId/add-member**: Add a member to a group
  - **POST /groups/:groupId/send**: Send a message to a group
  - **GET /groups/:groupId/messages**: Retrieve group message history
  - **GET /groups/:groupId/members**: Retrieve group member list

---

## 📁 Project Structure

```
📂 src
 ├── 📂 main
 │   ├── 📂 java
 │   │   └── com.howudoin
 │   │       ├── controller
 │   │       ├── service
 │   │       ├── repository
 │   │       └── model
 │   └── 📂 resources
 └── 📂 test
```

---

## 🤝 Contributors

This project was developed by:

- [Nuh Al-Sharafi](https://github.com/N-alsharafi)
- [Ali Ishtay](https://github.com/AliIshtay12)

---

## 📜 License

This repository is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

## 📬 Contact

For any questions or feedback, feel free to reach out:

- Nuh Al-Sharafi: [nuh.sharafi@sabanciuniv.edu](mailto:nuh.sharafi@sabanciuniv.edu)
- Ali Ishtay: [ali.altamimi@sabanicuniv.edu](mailto:ali.altamimi@sabanicuniv.edu)

---

Thank you for visiting our repository! If you like this project, consider giving it a ⭐!

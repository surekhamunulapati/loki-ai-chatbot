# LOKI AI - Smart AI Chatbot
📌 Project Overview
LOKI AI is a web-based Smart AI Chatbot developed to chat with users like ChatGPT. It allows users to ask questions, get AI answers, and manage chat history.

The project is designed to provide a user-friendly AI chat platform with voice, copy and delete features, and save all chats in database.

🚀 Features
User
- Chat with AI in real-time
- Mic / Voice Input
- Copy AI answer
- Delete chat history
- View previous chats
- Clean LOKI UI

🛠️ Technologies Used
Frontend
- HTML
- CSS
- JavaScript
- Thymeleaf

Backend
- Java
- Spring Boot
- Spring Data JPA

Database
- MySQL

AI
- Groq API (Llama-3.3-70b-versatile)

Tools
- IntelliJ IDEA
- MySQL Workbench
- Postman
- Maven
- Git & GitHub

🏗️ Project Architecture
Thymeleaf UI (HTML/CSS/JS)
        |
        | HTTP Request
        ↓
Spring Boot Backend (Controller + Service)
        |
        | JPA / Groq API
        ↓
MySQL Database + Groq AI

📂 Project Structure
loki-ai-chatbot/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── SmartAIChatbot/
│   │   │       ├── controller/
│   │   │       ├── service/
│   │   │       ├── model/
│   │   │       └── repository/
│   │   └── resources/
│   │       ├── templates/
│   │       │   └── chat.html
│   │       ├── static/
│   │       └── application.properties
│   └── test/
│
├── pom.xml
└── README.md

🔑 Main Modules
Chat Module
- Send message to AI and get reply

POST /api/chat

History Module
- Save and view chat history in MySQL

GET /

⚙️ How to Run the Project
1. Clone the Repository
git clone https://github.com/surekhamunulapati/loki-ai-chatbot.git
cd loki-ai-chatbot

2. Configure MySQL
Create a MySQL database:

CREATE DATABASE smart_chatbot;

Update in application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/smart_chatbot
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD
groq.api.key=${GROQ_API_KEY}

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

3. Run the Backend
Run SmartAIChatbotApplication in IntelliJ
OR
mvn spring-boot:run

The app will start on:

http://localhost:8080

🧪 API Testing
APIs can be tested using Postman / Browser

POST /api/chat
GET /

📸 Application Screens
Add screenshots of your application here.

screenshots/
├── chat.png
├── loki-home.png

🎯 Future Enhancements
- Timer-based chat
- Login / Register
- Multiple chat sessions
- Dark Mode
- Email notifications
- Admin dashboard

👩‍💻 Author
T. Surekha

Java Full Stack Developer

Skills Used
Java Spring Boot Thymeleaf MySQL JPA HTML CSS JavaScript Git GitHub Groq AI

# Project Task Manager – Full-Stack Mini Project

## 🧠 Description
A full-stack web application for managing projects and tasks with authentication, progress tracking, and persistent storage.

## 🛠 Tech Stack
- **Backend:** Java 17, Spring Boot, Spring Security, JWT, JPA, Flyway
- **Frontend:** React, Vite, Bootstrap
- **Database:** PostgreSQL
- **Local infrastructure:** Docker Compose

## ✨ Core Features
- User authentication with JWT
- Project and task management
- Persistent PostgreSQL storage
- Database migrations with Flyway
- React-based frontend

## 🚀 Run locally

### 1. Start PostgreSQL
```bash
docker compose up -d db
```

### 2. Configure environment variables
Copy `.env.example` and provide a strong local `JWT_SECRET`.

### 3. Run the backend
```bash
cd backend
mvn spring-boot:run
```

The backend runs on `http://localhost:8080` by default.

### 4. Run the frontend
From another terminal:

```bash
cd frontend
npm install
npm run dev
```

Vite will print the local frontend URL.

## 🔐 Configuration
The backend reads database and JWT settings from environment variables. Do not commit production secrets.

## 🗄️ Database
Flyway migrations under `backend/src/main/resources/db` are applied automatically at startup.

## 📁 Structure
```text
backend/   Spring Boot API
frontend/  React/Vite client
```

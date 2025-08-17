# Bug-Tracker
🐞 Bug Tracker

A lightweight bug tracking application for small teams and startups. Testers often report issues in chats or screenshots, which can get buried. This app helps teams quickly track bugs, assign owners, and manage statuses without complex workflows.


🛠️ Technologies Used

Frontend: React, HTML, CSS

Backend: Java, Spring Boot

Database: SQL


⚡ Features

User registration and login

Create bugs with title, description, severity, reporter, and assignee

Track bug statuses: New, In Progress, Fixed, Verified

Delete bugs when resolved

Quick filtering for high severity and new bugs

Simple, user-friendly interface



💻 Installation

Make sure you have Java, Spring Boot, SQL, Node.js, and npm installed.

Clone the repository:

git clone <https://github.com/CharanLucky1829/Bug-Tracker.git>
cd bug-tracker



Database Schema:

1️⃣ user Table

From your screenshot:

id → bigint (PK, Auto Increment)

name → varchar(255)

email → varchar(255)

password_hash → varchar(255)


CREATE TABLE user (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255),
  email VARCHAR(255) UNIQUE NOT NULL,
  password_hash VARCHAR(255) NOT NULL
);


---

2️⃣ bug Table

From your screenshot:

id → bigint (PK, Auto Increment)

title → varchar(255)

description → varchar(255)

priority → varchar(255)

reported_by_id → bigint (FK → user.id)

assignee_id → bigint (FK → user.id)

assigned_to → varchar(255)

status → varchar(255)

severity → enum('HIGH','LOW','MEDIUM')

created_at → datetime(6)

updated_at → datetime(6)


CREATE TABLE bug (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(255) NOT NULL,
  description VARCHAR(255) NOT NULL,
  priority VARCHAR(255),
  reported_by_id BIGINT,
  assignee_id BIGINT,
  assigned_to VARCHAR(255),
  status VARCHAR(255) DEFAULT 'New',
  severity ENUM('LOW','MEDIUM','HIGH') NOT NULL,
  created_at DATETIME(6) DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME(6) DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (reported_by_id) REFERENCES user(id) ON DELETE SET NULL,
  FOREIGN KEY (assignee_id) REFERENCES user(id) ON DELETE SET NULL
);


---

3️⃣ comment Table

From your screenshot:

id → bigint (PK, Auto Increment)

body → varchar(255)

created_at → datetime(6)

author_id → bigint (FK → user.id)

bug_id → bigint (FK → bug.id)


CREATE TABLE comment (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  body VARCHAR(255) NOT NULL,
  created_at DATETIME(6) DEFAULT CURRENT_TIMESTAMP,
  author_id BIGINT,
  bug_id BIGINT,
  FOREIGN KEY (author_id) REFERENCES user(id) ON DELETE CASCADE,
  FOREIGN KEY (bug_id) REFERENCES bug(id) ON DELETE CASCADE
);



Backend Setup (Spring Boot):

 cd backend
./mvnw spring-boot:run


Make sure your SQL database is running and configured in application.properties.

Frontend Setup (React):

cd frontend
npm install
npm run dev

🚀 Usage

Open the app in your browser.

Register a new account or login if you already have one.

Create a new bug by filling out the form: title, severity, status, reporter, and assignee.

View, edit, or delete bugs in the bug list.

Use the status and severity filters to prioritize bug triage.

📸 Screenshots

Login & Register page:
<img width="1918" height="995" alt="Screenshot 2025-08-18 001013" src="https://github.com/user-attachments/assets/b10eaa4c-0d28-45d4-bf51-058f627f1558" />

Dashboard:
<img width="1918" height="995" alt="Screenshot 2025-08-18 001013" src="https://github.com/user-attachments/assets/4f0b8a8d-57df-4f9b-8585-a49bc6af08fc" />


Bug Dashboard:


📂 Project Structure
backend/       # Spring Boot backend

frontend/      # React frontend

database.sql   # SQL schema

✉️ Contact

Created by Polimera CHARAN – feel free to reach out for questions or suggestions.

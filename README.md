# LRU Cache Project

## Overview
This project implements an **LRU (Least Recently Used) Cache** system with a backend in **Java (Spring Boot)** and a frontend using **HTML, CSS, and JavaScript**.  

The LRU Cache supports fast retrieval and insertion while maintaining the order of recently used items. The frontend interacts with the backend via HTTP requests.

---

## Features
- **LRU Cache Implementation** in Java using `LinkedHashMap`.
- **RESTful API**:
  - `GET /get/{key}`: Retrieve value by key.
  - `PUT /put/{key}/{value}`: Insert or update a key-value pair.
- **Frontend Interface**:
  - Add, view, and remove cache entries.
  - Displays the current cache state.
- **Runs in Eclipse** (backend) and terminal/browser (frontend).

---

## Technologies Used
- **Backend**: Java, Spring Boot, Eclipse IDE
- **Frontend**: HTML, CSS, JavaScript
- **Build Tool**: Maven

---

## Getting Started

### Prerequisites
- Java 8+ installed
- Maven installed
- Eclipse IDE (or any Java IDE)
- Web browser for frontend
- (Optional) Python installed if using HTTP server for frontend

### Backend Setup
1. Open Eclipse and import the project as a **Maven Project**.
2. Navigate to `backend/src/main/java/com/example/lru/` and run `LruCacheApplication.java`.
3. The backend server will start on `http://localhost:8080`.

### Frontend Setup
You can run the frontend either by opening the HTML directly or using a simple HTTP server.

#### **Option 1: Open HTML directly**
```bash
cd path/to/frontend
start index.html
Option 2: Run a simple HTTP server (recommended)
If you have Python installed:

bash
Copy code
cd path/to/frontend
# For Python 3
python -m http.server 8000
Then open your browser at:

arduino
Copy code
http://localhost:8000

# **Voter Registration System (Java Swing + Oracle Database)**

A simple and user-friendly **Java desktop application** that includes a **Login Page** and a **Voter Registration Page**. This project is built using **Java Swing & AWT** for the GUI and **Oracle 21c Express Edition** as the backend database. It demonstrates form validation, database connectivity using JDBC, and a smooth graphical interface for user interaction.

---

## **📌 Project Overview**

The **Voter Registration System** is designed to collect and store voter details securely. The application ensures that only eligible individuals (18+ years) can register. It features a login page for authentication and a registration page where users can enter their **Full Name, Age, and Mobile Number**.

The system uses **Oracle Database** to store user credentials and voter information, making the data persistent and secure.

---

## **✨ Features**

### **🔐 Login Page**

* Simple GUI-based login form
* Username & password authentication
* Redirects to the voter registration form after successful login

### **📝 Registration Page**

* Inputs: Full Name, Age, Mobile Number
* Age validation (must be 18 or above)
* Required field checks
* Saves voter details into Oracle DB
* Shows success/failure messages
* Automatically clears fields after successful registration

### **🗄 Database Integration**

* Uses **Oracle 21c XE**
* JDBC connection using `ojdbc.jar`
* Secure data storage with PreparedStatements

### **🎨 GUI**

* Built using **Java Swing & AWT**
* Custom colors
* Simple and clean layout

---

## **🛠 Technologies Used**

| Technology                        | Purpose                   |
| --------------------------------- | ------------------------- |
| **Java (JDK 8+)**                 | Main programming language |
| **Swing & AWT**                   | Interface design          |
| **Oracle Database 21c XE**        | Backend database          |
| **JDBC (ojdbc8.jar)**             | Database connectivity     |
| **VS Code**                       | IDE (optional)            |

---

## **📂 Project Structure**

```
/src
 ├── LoginForm.java
 ├── VoterRegistrationForm.java
 └── README.md
```

---

## **🧰 Database Setup (Oracle 21c XE)**

### **1. Create the Database Table**

```sql
CREATE TABLE voters (
    id NUMBER GENERATED ALWAYS AS IDENTITY,
    name VARCHAR2(100),
    age NUMBER,
    mobile VARCHAR2(15),
    PRIMARY KEY (id)
);
```

### **2. Create Login Table**

```sql
CREATE TABLE users (
    username VARCHAR2(50) PRIMARY KEY,
    password VARCHAR2(50)
);
```

### **3. Insert Default User**

```sql
INSERT INTO users VALUES ('admin', 'admin123');
```

---

## **⚙️ How to Run the Project**

### **1. Install Requirements**

* Java JDK
* Oracle 21c XE
* ojdbc8.jar / ojdbc11.jar
* Eclipse / IntelliJ (optional)

### **2. Add JDBC Driver**

Place the **ojdbc jar** in your project and add it to the classpath.

### **3. Update Database Credentials**

Inside `DBConnection.java`:

```java
String url = "jdbc:oracle:thin:@localhost:1521:xe";
String user = "system";
String password = "your_password";
```

---

## **📸 Screenshots (Optional Section)**

You can add screenshots of:

* SignUp Page
  <img width="1990" height="1181" alt="SignUp" src="https://github.com/user-attachments/assets/1ef1e283-c9a6-4ceb-bdee-b480d2ba6c7d"/>
* Login Page
  <img width="1990" height="1181" alt="Login" src="https://github.com/user-attachments/assets/f68a1879-f044-4262-887e-61fcad9ac0a4" />
* Registration Page
  <img width="1990" height="1351" alt="Registration" src="https://github.com/user-attachments/assets/cbb3d954-5e1a-44a7-b00e-6ffffa46a67f" />

---
## **▶️ Running the Application**

1. Run **LoginForm.java**
2. Enter username & password
3. Click **Login**
4. Registration form opens
5. Fill details → Click **Register**

---


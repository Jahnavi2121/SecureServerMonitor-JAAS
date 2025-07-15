# SecureServerMonitor-JAAS

## Description

SecureServerMonitor-JAAS is a lightweight Java CLI-based prototype designed to showcase authentication and role-based authorization using JAAS (Java Authentication and Authorization Service). This tool simulates access to server management operations such as viewing server status, restarting services, clearing logs, and shutting down servers. Each operation is gated by user roles: Admin, Operator, Auditor, and Guest.

This project demonstrates how authentication and authorization tactics can be incorporated into software architecture and evaluated through a minimal yet functional implementation. It is intended for use as a reference in secure software development and academic settings.

---

## Purpose

This project was developed to fulfill the requirements of implementing security tactics as part of a software architecture assignment. The focus is on prototyping the use of authentication (verifying user identity) and authorization (enforcing role-based access control) using Java and JAAS.

---

## Installation and Setup

### Requirements
- Java Development Kit (JDK) 8 or above
- Command-line terminal (bash, PowerShell, etc.)

### Steps to Run

1. Clone or download this repository.

2. Navigate to the project root and compile the code:

```
javac -d out src/*.java
```

3. Run the application with the JAAS configuration:

```
java -cp out -Djava.security.auth.login.config=login.config Main
```

---

## User Credentials

| Username | Password   | Role     |
|----------|------------|----------|
| admin    | adminpass  | ADMIN    |
| operator | op123      | OPERATOR |
| auditor  | audit123   | AUDITOR  |
| guest    | guestpass  | GUEST    |

---

## Features

- Role-based access to command-line server management interface
- JAAS-based authentication with a custom `LoginModule`
- Simple CLI for executing commands gated by role
- Configurable login file for plug-and-play credential modules
- Modular design for easy extensibility

---

## Role Permissions

| Role     | Permissions Available                            |
|----------|--------------------------------------------------|
| ADMIN    | view_status, restart_service, clear_logs, shutdown_server |
| OPERATOR | view_status, restart_service                    |
| AUDITOR  | view_status                                     |
| GUEST    | home_info only                                  |

---

## Technologies Used

- Java SE 8
- JAAS (Java Authentication and Authorization Service)
- Standard Java IO and collections

---

## Project Structure

```
SecureServerMonitor-JAAS/
├── login.config
├── README.md
├── design_document.md
└── src/
    ├── Main.java
    ├── ConsoleCallbackHandler.java
    ├── SecureLoginModule.java
    └── ServerCommandExecutor.java
```

---

## How to Extend

- Add new commands in `ServerCommandExecutor.java`
- Connect to an external database or LDAP for real credential management
- Replace CLI with GUI or REST interface using Spring Boot or JavaFX
- Add logging and audit trails for each user operation

---

## License

This project is licensed under the MIT License.

---

## Author

[Your Name]  
Developed for academic demonstration of secure architecture implementation using JAAS.

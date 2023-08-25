# Online Library Java Application

This is an online library Java application built using Spring Boot, Hibernate, Docker, PostgreSQL, Lombok, MapStruct, and AOP.

## Table of Contents

- [Description](#description)
- [Features](#features)
- [User Roles](#user-roles)
- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
    - [Installation](#installation)
- [Configuration](#configuration)
- [Contributing](#contributing)
- [License](#license)
- [Contact](#contact)

## Description

The Online Library Java Application is designed to provide an easy-to-use platform for managing books and users in a library setting. It leverages Spring Boot to handle the backend operations, Hibernate for database management, Docker for containerization, PostgreSQL as the database, Lombok for reducing boilerplate code, MapStruct for mapping between DTOs and entities, and AOP for aspect-oriented programming.

## Features

- User Registration and Authentication
- Browse and Search Books
- Buy books

## User Roles

### Super Admin

- **Username**: superadmin@gmail.com
- **Password**: superadmin
- Create and Manage Admins
- Handle Sales Reports

### Admin

- **Username**: admin@gmail.com
- **Password**: admin
- Manage Books
- Manage User Data

## Prerequisites

Before you begin, ensure you have met the following requirements:

- Java Development Kit (JDK) 17 or later
- Docker and Docker Compose (for containerization)
- PostgreSQL or another supported database

## Getting Started

### Installation

1. Clone the repository:

   ```sh
   git clone https://github.com/AsatryanA/online_library.git
   branch develop

2. Build and run the Docker containers:
   ```sh
   docker-compose up 

3. Access the application:

    Open your web browser and navigate to http://localhost:8080/swagger-ui/index.html.

## Configuration

Additional configuration options and environment variables can be found in the application.yaml file.

## Contributing

Contributions are welcome! Please fork the repository and create a pull request.

## License
This project is licensed under the  ...

## Contact

For questions or support, please contact asatryan.arsen1990@gmail.com.
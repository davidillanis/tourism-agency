
# Documentación *Tourism Agency*
![Logo](https://avatars.githubusercontent.com/u/144494659?v=4)

Clone the Repository
```bash
https://github.com/davidillanis/tourism-agency.git
```


## Run Backend

This application developed using Spring Boot. It allows managing resources through a RESTful API.

### Run Locally
Navigate to the project directory
```bash
cd ./TourismAgency-backend
```
Import file in keycloak
```bash
TourismAgency-backend/tourism/src/main/resources/realm-export-keycloak.json
```

Ensure you have JDK 17 or later installed. You can verify this by running, or Download and install JDK from [Oracle](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) or [OpenJDK](https://openjdk.java.net/)
```bash
java -version
```

Spring Boot projects can use either Maven or Gradle as a build tool. Ensure you have one of these installed. You can verify by running or Download Maven from [Apache Maven](https://maven.apache.org/download.cgi).

```bash
mvn -v
```

- This Example configuration database.

Create Database in MySQL preference.
```bash
CREATE DATABASE tourism
```

Environment variables, this is example
```bash
URL_DATABASE=jdbc:mysql://localhost:3306/tourism;
USERNAME_DATABASE=username;
PASSWORD_DATABASE=password;
```


## Run Frontend

![Logo](https://portfolio-b1bb8.web.app/assets/images/projects/system-tourism.svg)

Tourism system, this project is a TodoCode hackathon, unfortunately it could not be presented. Users will be able to purchase services and a set of services, the administrator will be able to add, edit, disable services or the set of services.


## Run Locally


1. **Navigate to the project directory:**
   Change directory to move to the project you cloned:
```bash
   cd ./TourismAgency-backend
```

2. **Install dependencies:**
   Make sure you have Node.js and npm installed. Then, install the project dependencies by running:
```bash
   npm install
```
3. **Inicia el servidor de desarrollo:**
   Once the dependencies are installed, you can start the Angular development server with the following command:
```bash
   ng serve --open
```





### Authors
- [@David Abel](https://github.com/davidillanis)
### 🔗 Links
[![portfolio](https://img.shields.io/badge/my_portfolio-000?style=for-the-badge&logo=ko-fi&logoColor=white)](https://portfolio-b1bb8.web.app/home)
[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/david-abel-81645a1b5/)

![Telesport](/chatop.png)


# About The Project

A backend system to handle the connection between the future tenants and owners for seasonal rentals.

# Built With

This section should list any major frameworks/libraries/Dependency used in the project. Here are a few examples.
 - Spring Boots
 - spring-boot-starter-data-jpa
 - mysql-connector-j
 - lombok
 - spring-boot-starter-security
 - io.jsonwebtoken and it sub dependencies 
 - spring-boot-starter-mail
 - springdoc-openapi-starter-webmvc-ui


# Getting Started
This project is the backEnd of the front project ```https://github.com/OpenClassrooms-Student-Center/P3-Full-Stack-portail-locataire```

**1. Requirements**
    - JDK 17 or above
    - Maven
<br>
**2. Clone the repo ```https://github.com/Pertoldi/OC_P2_2023.git```**
<br>

**3. Install your DB:**

This project use [MySql](https://www.mysql.com/fr/downloads/).

Create a new database named chatop in MySql and execute the code in ```script-sql.sql```.

It is a dump of the database structure.


**4. Configure your ```application.properties```:** 
```
# spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update

#Tomcat configuration
server.port=3001

#Log
logging.level.web=DEBUG
logging.level.root=INFO
logging.level.api.spring==INFO
spring.mvc.log-request-details=true 
```

**5. Configure your secrets variables environement:**

On vs-code: go to ```Run``` -> ```Open Configurations``` -> it will open lauch.json file. Add under the configuration key add ```"envFile": "${workspaceFolder}/.env"```
Once you're done add a ```.env``` file and add thoses keys:
``` 
#Database access
spring.datasource.url=jdbc:mysql://localhost:3306/chatop
spring.datasource.username=
spring.datasource.password=

#Secret
jwtSecretKey=
```

**6. Running the application locally:**  

- There are several ways to run a Spring Boot application on your local machine. One way is to execute the main method in the **Chatop4Application class** from your IDE.

- Alternatively you can use the Spring Boot Maven plugin like so:
    >`mvn spring-boot:run`

### Contact

Mailto : <a href="mailto:antoine.pertoldi+chatop@gmail.com">antoine.pertoldi@gmail.com</a>
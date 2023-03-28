# Auto Service API 💻🚗

AutoService is an API for managing sales in an auto service. This project is built using Spring Boot, Java 17 and PostgreSQL.

## Project Structure 📂

The project is structured as follows:

- `java/`: contains all Java source code
    - `controller/`: contains the API controllers
    - `dto/`: contains the data transfer objects used by the controllers
    - `inject/`: contains InjectController class for injecting test data into the database
    - `lib/`: contains custom annotations for validating input parameters
    - `model/`: contains the domain models
    - `repository/`: contains the data repositories
    - `service/`: contains the business logic services
- `resources/`: contains the `application.properties` file

## Technologies Used 🛠️

- Spring Boot
- Java 17
- PostgreSQL

## How to Configure the Database And Run the Project 🚀

1. Clone the repository: `git clone https://github.com/your-username/autoservice.git`
2. Install PostgreSQL on your local machine, if it's not already installed. 
3. Create a new user for the project: `CREATE USER autoservice_user WITH PASSWORD 'autoservice_password';`
4. Grant the user all privileges on the database: `GRANT ALL PRIVILEGES ON DATABASE autoservice TO autoservice_user;`
5. Update the `application.properties` file 
6. Run the project

# crud Project
This project is a CRUD where by each element like Order, Customer, ShippingAddress or Product you can save, delete, update and search the information realted to them.

## Features
CRUD for customers and products.
ADD and DELETE customer orders.
Search customers, products, and orders.
Search order details.

## Business Logic:
Customer’s phone and email must be unique.
Product ID must be unique.
Order number must be unique.
An order can contain multiple products and must include at least one product.
Order’s total value is a sum of the order’s product/s prices.

## Technologies Used
Java
Spring Boot
Spring Data JPA
JUnit
Mockito
Getting Started
To get started with the project, follow these steps:

##Prerequisites:
Make sure you have Java and Maven installed on your system.
Clone the repository: Use Git to clone this repository to your local machine. link https://github.com/danielrv01/SpringBootCRUD
Build the project: Open a terminal or command prompt and navigate to the project's root directory. Run the following command to build the project and resolve dependencies:
mvn clean install
Run the application: After the build is successful, you can run the application using the following command:
mvn spring-boot:run
Access the API: Once the application is running, you can access the API endpoints using a web browser and typing the next url (http://localhost:8080/swagger-ui/index.html).
Running Unit Tests
This project includes unit tests for services and repositories. To run the unit tests, navigate to the project's root directory in a terminal or command prompt and execute the following command:

mvn test
The tests will be executed, and the test results will be displayed in the console.

##Contact
If you have any questions or suggestions, feel free to reach out to the next email luisfelipemarincano@gmail.com.

##Acknowledgments
Spring Boot
JUnit
Mockito
Spring Data JPA
java

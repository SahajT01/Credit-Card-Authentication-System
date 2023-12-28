[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/9NZqTcR2)

# **Credit Card Processor**

## **Overview**
This project is a Java-based application for processing credit card data. It demonstrates the use of various design patterns and efficient handling of different file formats to read and process credit card information.

## **Key Features**
- Parse credit card data from CSV, JSON, and XML files.
- Identify the type of credit card (Visa, MasterCard, American Express, Discover, etc.).
- Handle invalid or unknown credit card formats.
- Output processed data in the same format as the input.

## **Environment**
- Java Version: 19.0.1
- IDE: IntelliJ IDEA

## **Design Patterns Used**
1. Chain of Responsibility
- Purpose: To pass the request along a chain of handlers. Upon receiving a request, each handler decides either to process it or to pass it to the next handler in the chain.
- Usage: Used in processing different types of credit cards. Each handler (Visa, MasterCard, etc.) checks whether it can handle a given credit card number. If not, it passes the number to the next handler in the chain.

2. Factory
- Purpose: To create objects without specifying the exact class of object that will be created.
- Usage: Used to create instances of different types of credit cards.

3. Adapter
- Purpose: To allow interfaces of incompatible classes to work together.
- Usage: Used for reading different file formats (CSV, JSON, XML). Each file type has an adapter that parses the file and returns data in a uniform format.

4. Strategy
- Purpose: To enable selecting an algorithm's behavior at runtime.
- Usage: Employed in the file parsing mechanism, where the parsing strategy is selected based on the file format.

## **Unit Testing**
Unit tests have been extensively implemented to ensure the robustness and reliability of the application. The tests cover a variety of scenarios including:

- Parsing and validation of credit card data from different file formats.
- Correct instantiation and processing by various credit card handlers.
- Proper functioning of the design patterns applied in the project.
- Testing ensures the system behaves as expected under various conditions and improves the maintainability of the code.

## **Project Structure**
- CreditCardProcessor: Main class to process credit cards.
- CreditCard: Base class for different types of credit cards.
- CreditCardHandler: Abstract handler in the chain of responsibility pattern.
- FileParser: Interface for file parsing strategies.
  
## **How to Run**
Ensure that Java 19.0.1 is installed and properly set up. Open the project in IntelliJ IDEA and run the CreditCardProcessor class. Also include org.json as a dependency

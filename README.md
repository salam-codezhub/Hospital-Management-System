Hospital Management System
A command-line Hospital Management System developed using Core Java only. The project demonstrates Object-Oriented Programming, collections, exception handling, validation, sorting/searching, and file-based persistence without a database.
Features
Patient management
Doctor management
Appointment booking with conflict validation
Medical records
Billing and payment status
Patient search
File-based data persistence
Input validation and custom exceptions
Command-line execution
Technologies
Java 17 or newer
Java Standard Library
No database
No external dependencies
No GUI
Project Structure
```text
HospitalManagementSystem/
├── README.md
├── statement.md
├── src/
│   ├── Main.java
│   ├── Person.java
│   ├── Patient.java
│   ├── Doctor.java
│   ├── Appointment.java
│   ├── MedicalRecord.java
│   ├── Bill.java
│   ├── Hospital.java
│   ├── FileManager.java
│   ├── InputValidator.java
│   └── HospitalException.java
└── data/
```
Requirements
Install Java JDK 17 or later and make sure `java` and `javac` are available in the terminal.
Check:
```bash
java -version
javac -version
```
Compile
From the repository root:
```bash
javac -d out src/*.java
```
Run
```bash
java -cp out Main
```
The application creates `.dat` files inside the `data` directory when records are saved.
Test Flow
Add a patient.
Add a doctor.
Book an appointment using the existing patient and doctor IDs.
Try booking the same doctor at the same date/time to test conflict validation.
Add a medical record.
Create a bill.
Mark the bill as paid.
View and search records.
OOP Concepts Demonstrated
Encapsulation: private fields with controlled accessors.
Inheritance: `Patient` and `Doctor` inherit from abstract `Person`.
Abstraction: `Person` defines common behavior using abstract methods.
Polymorphism: `Patient` and `Doctor` override `displayInfo()` and `getRole()`.
Composition: `Hospital` manages collections of domain objects.
Data Persistence
Because this project does not use a database, Java object serialization is used to store application data in the `data/` directory. This keeps the project fully executable from the command line with no external setup.
Author
Student Project - Hospital Management System

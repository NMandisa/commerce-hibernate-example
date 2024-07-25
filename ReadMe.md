# Java JPA Hibernate 6 ORM demo - Commerce Domain

### Overview
This demo showcases the use of Java Persistence API (JPA) and Hibernate ORM 6 using Commerce domain knowledge as a Proof of Concept.
It demonstrates the latest Hibernate 6 Object Relational Mapping (ORM) concepts.

## Project Structure

- src/main/java/za/co/fynbos
 - model: Commerce domain entities (Customer, Brand, Product, etc.)
 - utils: Utilities classes for JPA and Hibernate
- src/main/resources
 - persistence.xml: JPA configuration file
 - hibernate.cfg.xml: Hibernate configuration file
   ![project_structure](project_structure.png)
 - 
## Model and Relationship Diagram
   ![ models](img.png)

## Technologies Used

- Java 17
- JPA 3.0
- Hibernate ORM 6
- MySQL 8.0
- Maven 3.8.6

##### Notes

- This demo uses MySQL as the database. You can switch to other databases by modifying the persistence.xml and hibernate.cfg.xml files.
- Make sure to update the database credentials and URLs in persistence.xml and hibernate.cfg.xml before running the application.

### License

This project is licensed with as CLOSED-SOURCE. See LICENSE file for details.
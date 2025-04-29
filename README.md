🐾 Animal Shelter API
Welcome to the Animal Shelter API!
This API digitalizes animal shelter operations by enabling staff to:
Manage shelters, animals, and their full medical histories.
Track adoption applications and handle approval workflows.
Simplify daily tasks like animal registration, medical updates, and adoption management.
It provides a clean, secure RESTful solution to help shelters operate more efficiently, ensure better animal care, and boost successful adoptions.

✨ What This API Can Do
Shelters:
Create, view, update, and delete shelter records.

Animals:
Register new animals, update their info, filter by species, manage their adoption status.

Medical History:
Track treatments, and more for each animal.

Adoption Applications:
Submit, review, approve, or reject adoption requests.

Error Handling:
Clean global error responses (404, 400, 500).

Pagination:
All listing endpoints support pagination for better performance.

🛠️ Technologies Used
Java 21
Spring Boot 3.4.5
Spring Data JPA (Hibernate)
MySQL
Maven
Lombok
Postman for testing

🛠️ Project Structure
Organized into clear layers:

controllers/ → Expose the API
services/ → Handle business logic
repositories/ → Talk to the database
converters/ → Map Entities ⇆ DTOs
dto/ → Data transfer objects
entity/ → Database models
exceptions/ → Global error handling

✍️ Author
Created by AniILK

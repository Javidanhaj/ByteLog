# ByteLog

ByteLog is a modular blogging platform built with Spring Boot. Featuring a clean architecture and robust RESTful API design, this project highlights advanced Java development skills and effective use of modern frameworks and best practices. It demonstrates expertise in backend engineering, domain-driven design, and API development.
More new features are on the way!!

---

## 📁 Project Structure & Components

### src/main/java/com/javidanhaj/bytelog

- **ByteLogApplication.java**  
  Entry point; bootstraps the Spring application.

#### **Controllers**
- **CategoryController**  
  Manages category-related endpoints (`/api/v1/categories`). Implements CRUD operations with OpenAPI documentation for seamless integration.
- **PostController**  
  Handles post management (`/api/v1/posts`), including fetching published/draft posts, with optional filtering by category and tag.
- **TagController**  
  Provides endpoints for tag CRUD operations (`/api/v1/tags`), supporting bulk creation and retrieval.

#### **Services**
- **CategoryService / CategoryServiceImpl**  
  Defines and implements business logic for category management, including creation, deletion, update, and listing. Validates uniqueness and manages relationships with posts.
- **TagService / TagServiceImpl**  
  Handles tag lifecycle, bulk creation, deletion, and lookup. Ensures efficient management of tag-post relationships.
- **PostServiceImpl**  
  Contains logic for post CRUD, filtering, draft handling, and calculates reading time for posts.

#### **Repositories**
- **CategoryRepository**  
  Spring Data JPA repository for Category entities. Includes custom queries for post counts and case-insensitive name existence.
- **TagRepository**  
  Repository for Tag entities; supports bulk retrieval, post associations, and uniqueness checks.
- **PostRepository**  
  Repository for Post entities; offers advanced query methods for filtering by status, category, tag, and author.

#### **Entities**
- **Category**  
  Represents a blog category with a unique name and associated posts. Utilizes JPA annotations for ORM mapping.
- (Other entities like Post, Tag, User assumed present—managing core domain data.)

---

## ✨ Key Features

- RESTful API with robust input validation and descriptive OpenAPI docs.
- Modular service and repository layers for scalability and testability.
- Advanced query support for filtering and bulk operations.
- Exception handling for business rules (e.g., preventing deletion of categories with posts).
- Dependency Injection via Lombok and Spring annotations for maintainable code.
- Domain-driven design principles for clear separation of concerns.

---

## 🧩 Dependencies

- **Spring Boot** (core framework)
- **Spring Data JPA** (ORM and persistence)
- **Lombok** (reduces boilerplate)
- **Jakarta Persistence** (JPA annotations)
- **Swagger/OpenAPI** (API documentation)

---

*For more details, explore the source code and architecture in this repository.*

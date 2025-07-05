# GitHub Repository Searcher 🚀

A Spring Boot application to search GitHub repositories using the GitHub API, save them to a PostgreSQL database, and retrieve them with optional filtering and sorting.

---

## 🔧 Technologies Used
- Java 17
- Spring Boot 3.1.1
- Spring Web
- Spring Data JPA
- PostgreSQL
- Maven

---

## 📂 Project Structure
```text
src
 └── main
      ├── java
      │    └── com.example.github
      │          ├── GithubController.java
      │          ├── GithubRepository.java
      │          ├── GithubRepositoryEntity.java
      │          ├── GithubRepositoryRepository.java
      │          ├── GithubSearchRequest.java
      │          ├── GithubService.java
      │          └── GithubRepoSearcherApplication.java
      └── resources
           └── application.properties

1. Clone the Repository
bash
Copy
Edit
git clone https://github.com/MohitKundu360/github-repo-searcher.git
cd github-repo-searcher
2. Configure PostgreSQL Database
Make sure PostgreSQL is running and create a database named:

sql
Copy
Edit
CREATE DATABASE github_db;
Update your application.properties:

properties
Copy
Edit
spring.datasource.url=jdbc:postgresql://localhost:5432/github_db
spring.datasource.username=postgres
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
3. Run the Application
bash
Copy
Edit
mvn spring-boot:run
The server will start on:
http://localhost:8080

📮 API Endpoints
1. Search GitHub Repositories (Save to DB)
http
Copy
Edit
POST /api/github/search
Request Body:

json
Copy
Edit
{
  "query": "spring boot"
}
Response: List of repositories.

2. Get All Saved Repositories
http
Copy
Edit
GET /api/github/repositories
Response: All repositories from the database.

3. Filter & Sort Repositories
http
Copy
Edit
GET /api/github/repositories?nameFilter=spring&sortBy=name
nameFilter → Optional search keyword.

sortBy → Optional field to sort by (name).

📦 Sample Postman Collection
I’ve provided a Postman collection for testing the APIs.

📝 License
This project is licensed under the GNU General Public License v2.0.

🙌 Acknowledgements
GitHub REST API

Spring Boot Documentation

yaml
Copy
Edit

---

### ✅ Would you like me to:
- Prepare this file and push it directly to your GitHub repository?
- Generate a **.gitignore** file for your project?

Let me know how you’d like to proceed! 😊

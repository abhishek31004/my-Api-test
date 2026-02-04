Objective 
Design and implement a modular API Automation Framework REST APIs: 
● Token-based Authentication 
● CRUD operations on Users 
● Negative scenario validations 
● Response schema + status code verification 
● Modular, reusable API client design 

Ref: https://gorest.co.in/ 
The implementation should be done using RestAssured with Java. 
Framework Requirements 
The candidate must build a modular, reusable, extensible API automation framework that meets the following requirements: 
Framework must include: 
● Request builder utilities 
● Authentication handler (Bearer Token injection) 
● Timestamp / logging utilities 
● Status code validator 


● JSON field + schema validator 
● Common HTTP client (GET / POST / PUT / PATCH / DELETE)

---

## Project layout (what was scaffolded) 📁

- `src/test/java/com/example/auth` — `TokenProvider` (env/system property support)
- `src/test/java/com/example/utils` — `RequestSpecFactory`, `LoggingUtils`, `RandomUtils`
- `src/test/java/com/example/client` — `UsersClient` (GET/POST/PUT/PATCH/DELETE, query params)
- `src/test/java/com/example/models` — `UserRequest`, `UserResponse`
- `src/test/java/com/example/validators` — `ResponseValidator` (status/schema/field checks)
- `src/test/resources/schemas` — `user-schema.json` (example JSON Schema)

This scaffolding covers the framework-level utilities and example tests required for the assignment.

**TestNG** — Added TestNG as a test dependency so you can author TestNG-style tests alongside JUnit (`org.testng:testng:7.8.1`).

**Note** — I searched the test sources and found no `public static void main` methods; nothing needed to be removed.

Authentication Handler 
Automatically inject: 
● Authorization: Bearer <TOKEN> 
● Content-Type: application/json 
● Accept: application/json 

Framework should support: 
● Config-based token management 
● Secure storage of token in config / env variable 

API Client Abstractions 
Create client classes/modules for each API group: 
● UsersClient ○ GET /users 
○ GET /users/{id} 
○ POST /users 
○ PUT /users/{id} 
○ PATCH /users/{id} 
○ DELETE /users/{id} 


Each client must: 
● Accept request model 


● Send HTTP request 
● Return parsed response object 
● Contain reusable helper methods 

-->Test Requirements 
Token Generation – GoREST Public API 
candidate must generate an access token from GoREST. 
Steps to Generate the Token 
1. Log in to GoREST via your browser (You may log in using GitHub, Google, or Microsoft.) https://gorest.co.in/ 
2. Navigate to your Access Token page: https://gorest.co.in/my-account/access-tokens 
3. Click on “Create New Access Token”A new token will be generated. 
4. Copy the generated token string Store it in your framework config (env variable or config file). 
5. Use the token in your API requests: Authorization: Bearer YOUR_TOKEN_HERE 

Candidate must automate the following: 
1. User – GET 

● Get all users 
● Get users with pagination 
● Get user by valid ID 
● Get user by invalid ID 

2. User – POST 
● Create user with valid payload 
● Missing mandatory field (email → 422)
● Invalid email format → 422 
● Create user without token → 401 

3. User – PUT 
● Full update with all fields 

4. User – PATCH 
● Partial update (status only) 

5. User – DELETE 
● Delete valid user 
● Delete invalid user 

Each test must verify: 
● Response code 
● JSON response fields 
● Error messages 
● Business rules (validation, required fields) 


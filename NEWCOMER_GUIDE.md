# Newcomer Guide: AuthService

## What this project is
AuthService is a small Spring Boot 3 application that demonstrates basic Spring Security setup with HTTP Basic authentication.

At the moment, it uses:
- `spring-boot-starter-web` for REST endpoints
- `spring-boot-starter-security` for security filters and authentication
- an **in-memory** user store (`InMemoryUserDetailsManager`)

## High-level structure
- `src/main/java/com/security/authservice/AuthServiceApplication.java`
  - Spring Boot entry point (`main` method).
- `src/main/java/com/security/authservice/config/SecurityConfig.java`
  - Central security configuration.
  - Defines URL authorization rules and HTTP Basic.
  - Defines `UserDetailsService` and password encoder beans.
- `src/main/java/com/security/authservice/controller/HomeController.java`
  - Public `/home` endpoint.
- `src/main/java/com/security/authservice/controller/AuthController.java`
  - Protected `/hello` endpoint.
- `build.gradle`
  - Gradle plugins, Java 17 toolchain, and dependencies.
- `src/main/resources/application.properties`
  - Runtime app settings (`spring.application.name`, `server.port=8081`).

## Security behavior to know
From `SecurityConfig`:
- CSRF is disabled.
- `/`, `/home`, and `/login` are publicly accessible.
- Any other endpoint requires authentication.
- Authentication method is HTTP Basic.
- One in-memory user is configured:
  - username: `user12`
  - password (raw): `pass123`
  - role: `USER`

This means `GET /home` works anonymously, while `GET /hello` requires valid basic auth credentials.

## How to run and try it quickly
1. Start app: `./gradlew bootRun`
2. Public endpoint: `curl http://localhost:8081/home`
3. Protected endpoint without creds: `curl http://localhost:8081/hello`
4. Protected endpoint with creds:
   `curl -u user12:pass123 http://localhost:8081/hello`

## What to learn next (recommended order)
1. **Spring Security filter chain basics**
   - Understand `SecurityFilterChain` and request authorization DSL.
2. **Authentication providers & user stores**
   - Replace in-memory users with JDBC/JPA-backed users.
3. **Password storage**
   - Keep using BCrypt and learn password migration/versioning.
4. **Session vs token auth**
   - Move from HTTP Basic to JWT-based stateless auth.
5. **Method-level security**
   - Add `@PreAuthorize` with roles/authorities.
6. **Testing security rules**
   - Use `spring-security-test` (`@WithMockUser`, mock MVC tests).

## Practical improvements you can implement in this repo
- Add a dedicated auth module with register/login endpoints.
- Persist users and roles in a DB (H2 for local, Postgres for real usage).
- Add integration tests for public vs protected routes.
- Add profile-based config (`application-dev.properties`, etc.).
- Re-enable CSRF if browser-based forms are introduced.

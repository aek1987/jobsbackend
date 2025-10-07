Job Platform - Spring Boot starter

How to run locally:
1. Install JDK 17 and Maven.
2. Edit src/main/resources/application.properties and set your Supabase DB credentials.
3. Run: mvn spring-boot:run
4. API endpoints: GET /api/offres

Supabase tip:
- In Supabase project -> Settings -> Database you'll find host/user/password.
- Use jdbc:postgresql://<host>:5432/<database> as spring.datasource.url

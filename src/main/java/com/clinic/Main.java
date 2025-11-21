package com.clinic;

import com.clinic.config.EnvConfig;
import com.clinic.config.FlywayMigration;
import com.clinic.controller.PatientController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.javalin.Javalin;
import io.javalin.json.JavalinJackson;

import java.sql.Connection;
import java.sql.DriverManager;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Clinic REST API Starting ===\n");

        try {
            // 1. Load environment configuration
            System.out.println("1. Loading Configuration...");
            EnvConfig.printConfiguration();

            // 2. Test database connection
            System.out.println("2. Testing Database Connection...");
            testDatabaseConnection();

            // 3. Run database migrations
            System.out.println("3. Running Database Migrations...");
            FlywayMigration migration = new FlywayMigration();
            migration.migrate();
            System.out.println();

            // 4. Start REST API server
            System.out.println("4. Starting REST API Server...");
            startRestApiServer();

        } catch (Exception e) {
            System.err.println("\n✗ Startup failed: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }

    private static void testDatabaseConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(
                    EnvConfig.getDbUrl(),
                    EnvConfig.getDbUser(),
                    EnvConfig.getDbPassword()
            )) {
                System.out.println("   ✓ Database connection successful");
                System.out.println();
            }
        } catch (Exception e) {
            throw new RuntimeException("Database connection failed", e);
        }
    }

    private static void startRestApiServer() {
        int port = EnvConfig.getAppPort();

        // Configure Jackson ObjectMapper for Java 8 date/time support
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        Javalin app = Javalin.create(config -> {
            config.showJavalinBanner = false;
            config.jsonMapper(new JavalinJackson(objectMapper));
        }).start(port);

        // Initialize controllers
        PatientController patientController = new PatientController();

        // Health check endpoint
        app.get("/", ctx -> ctx.result("Clinic REST API is running!"));
        app.get("/health", ctx -> {
            ctx.json(new HealthResponse("OK", "Clinic API is healthy"));
        });

        // Patient endpoints
        app.get("/api/patients", patientController::getAllPatients);
        app.get("/api/patients/{id}", patientController::getPatientById);
        app.post("/api/patients", patientController::createPatient);
        app.put("/api/patients/{id}", patientController::updatePatient);
        app.delete("/api/patients/{id}", patientController::deletePatient);

        // TODO: Add more endpoints for doctors, appointments, etc.

        System.out.println("✓ REST API Server started successfully");
        System.out.println("   URL: http://localhost:" + port);
        System.out.println("   Health Check: http://localhost:" + port + "/health");
        System.out.println("   API Endpoints:");
        System.out.println("   - GET    /api/patients");
        System.out.println("   - GET    /api/patients/{id}");
        System.out.println("   - POST   /api/patients");
        System.out.println("   - PUT    /api/patients/{id}");
        System.out.println("   - DELETE /api/patients/{id}");
        System.out.println("\n=== Server is ready to accept requests ===");
    }

    // Health response class
    static class HealthResponse {
        private String status;
        private String message;

        public HealthResponse(String status, String message) {
            this.status = status;
            this.message = message;
        }

        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }

        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
    }
}

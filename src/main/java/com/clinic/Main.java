package com.clinic;

import com.clinic.config.EnvConfig;
import com.clinic.config.FlywayMigration;
import com.clinic.controller.AppointmentController;
import com.clinic.controller.DoctorController;
import com.clinic.controller.MedicalRecordController;
import com.clinic.controller.PatientController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.javalin.Javalin;
import io.javalin.json.JavalinJackson;
import io.javalin.http.Context;

import java.sql.Connection;
import java.sql.DriverManager;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

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

            // Enable CORS - Javalin 5.x syntax
            config.plugins.enableCors(cors -> {
                cors.add(it -> {
                    it.anyHost();
                });
            });
        }).start(port);

        // Request logging - BEFORE processing
        app.before(ctx -> {
            String timestamp = LocalDateTime.now().format(TIME_FORMATTER);
            String method = ctx.method().toString();
            String path = ctx.path();
            String ip = ctx.ip();

            System.out.println("\n┌─────────────────────────────────────────────────────────");
            System.out.println("│ [" + timestamp + "] INCOMING REQUEST");
            System.out.println("├─────────────────────────────────────────────────────────");
            System.out.println("│ Method    : " + method);
            System.out.println("│ Path      : " + path);
            System.out.println("│ IP        : " + ip);
            System.out.println("│ User-Agent: " + ctx.header("User-Agent"));
            System.out.println("│ Accept    : " + ctx.header("Accept"));

            // Log request body for POST/PUT
            if ("POST".equals(method) || "PUT".equals(method)) {
                String contentType = ctx.header("Content-Type");
                System.out.println("│ Content-Type: " + contentType);
                String body = ctx.body();
                if (body != null && !body.isEmpty()) {
                    System.out.println("│ Body      : " + (body.length() > 200 ? body.substring(0, 200) + "..." : body));
                }
            }

            // Log query parameters
            if (!ctx.queryParamMap().isEmpty()) {
                System.out.println("│ Query Params: " + ctx.queryParamMap());
            }

            // Log path parameters
            if (!ctx.pathParamMap().isEmpty()) {
                System.out.println("│ Path Params: " + ctx.pathParamMap());
            }

            // CORS headers (manual addition for compatibility)
            ctx.header("Access-Control-Allow-Origin", "*");
            ctx.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
            ctx.header("Access-Control-Allow-Headers", "Content-Type, Authorization, Accept");
            ctx.header("Access-Control-Max-Age", "3600");

            // Store start time for response timing
            ctx.attribute("startTime", System.currentTimeMillis());
        });

        // Response logging - AFTER processing
        app.after(ctx -> {
            Long startTime = ctx.attribute("startTime");
            long duration = startTime != null ? System.currentTimeMillis() - startTime : 0;

            String timestamp = LocalDateTime.now().format(TIME_FORMATTER);
            int status = ctx.statusCode();
            String statusEmoji = status < 300 ? "✓" : status < 400 ? "⚠" : "✗";

            System.out.println("├─────────────────────────────────────────────────────────");
            System.out.println("│ [" + timestamp + "] RESPONSE");
            System.out.println("├─────────────────────────────────────────────────────────");
            System.out.println("│ Status    : " + statusEmoji + " " + status);
            System.out.println("│ Duration  : " + duration + "ms");
            System.out.println("│ Content-Type: " + ctx.contentType());

            // Log response body preview - Fixed for Javalin 5.x
            String responseBody = ctx.result();
            if (responseBody != null && !responseBody.isEmpty()) {
                String preview = responseBody.length() > 200 ?
                        responseBody.substring(0, 200) + "..." : responseBody;
                System.out.println("│ Body Preview: " + preview);
            }

            System.out.println("└─────────────────────────────────────────────────────────\n");
        });

        // Handle preflight OPTIONS requests
        app.options("/*", ctx -> {
            System.out.println("│ Handling OPTIONS preflight request");
            ctx.status(204);
        });

        // Initialize controllers
        PatientController patientController = new PatientController();
        DoctorController doctorController = new DoctorController();
        AppointmentController appointmentController = new AppointmentController();
        MedicalRecordController medicalRecordController = new MedicalRecordController();

        // Health check endpoint
        app.get("/", ctx -> {
            System.out.println("│ Handler: Root endpoint");
            ctx.result("Clinic REST API is running!");
        });

        app.get("/health", ctx -> {
            System.out.println("│ Handler: Health check");
            ctx.json(new HealthResponse("OK", "Clinic API is healthy"));
        });

        // Patient endpoints
        app.get("/api/patients", ctx -> {
            System.out.println("│ Handler: Get all patients");
            patientController.getAllPatients(ctx);
        });

        app.get("/api/patients/search", ctx -> {
            System.out.println("│ Handler: Search patients");
            patientController.searchPatients(ctx);
        });

        app.get("/api/patients/social/{socialId}", ctx -> {
            System.out.println("│ Handler: Get patient by social ID");
            patientController.getPatientBySocialId(ctx);
        });

        app.get("/api/patients/{id}", ctx -> {
            System.out.println("│ Handler: Get patient by ID");
            patientController.getPatientById(ctx);
        });

        app.post("/api/patients", ctx -> {
            System.out.println("│ Handler: Create patient");
            patientController.createPatient(ctx);
        });

        app.put("/api/patients/{id}", ctx -> {
            System.out.println("│ Handler: Update patient");
            patientController.updatePatient(ctx);
        });

        app.delete("/api/patients/{id}", ctx -> {
            System.out.println("│ Handler: Delete patient");
            patientController.deletePatient(ctx);
        });

        // Doctor endpoints
        app.get("/api/doctors", ctx -> {
            System.out.println("│ Handler: Get all doctors");
            doctorController.getAllDoctors(ctx);
        });

        app.get("/api/doctors/{id}", ctx -> {
            System.out.println("│ Handler: Get doctor by ID");
            doctorController.getDoctorById(ctx);
        });

        app.get("/api/doctors/specialty/{specialty}", ctx -> {
            System.out.println("│ Handler: Get doctors by specialty");
            doctorController.getDoctorsBySpecialty(ctx);
        });

// Appointment endpoints
        app.get("/api/appointments", ctx -> {
            System.out.println("│ Handler: Get all appointments");
            appointmentController.getAllAppointments(ctx);
        });

        app.get("/api/appointments/{id}", ctx -> {
            System.out.println("│ Handler: Get appointment by ID");
            appointmentController.getAppointmentById(ctx);
        });

        app.post("/api/appointments", ctx -> {
            System.out.println("│ Handler: Create appointment");
            appointmentController.createAppointment(ctx);
        });

        app.put("/api/appointments/{id}/complete", ctx -> {
            System.out.println("│ Handler: Complete appointment");
            appointmentController.completeAppointment(ctx);
        });

// Medical Record endpoints
        app.get("/api/medical-records/appointment/{appointmentId}", ctx -> {
            System.out.println("│ Handler: Get medical record by appointment ID");
            medicalRecordController.getMedicalRecordByAppointmentId(ctx);
        });

        app.post("/api/medical-records", ctx -> {
            System.out.println("│ Handler: Create medical record");
            medicalRecordController.createMedicalRecord(ctx);
        });

        // Global error handler
        app.exception(Exception.class, (e, ctx) -> {
            String timestamp = LocalDateTime.now().format(TIME_FORMATTER);
            System.err.println("\n╔═════════════════════════════════════════════════════════");
            System.err.println("║ [" + timestamp + "] EXCEPTION OCCURRED");
            System.err.println("╠═════════════════════════════════════════════════════════");
            System.err.println("║ Path      : " + ctx.path());
            System.err.println("║ Method    : " + ctx.method());
            System.err.println("║ Exception : " + e.getClass().getSimpleName());
            System.err.println("║ Message   : " + e.getMessage());
            System.err.println("╚═════════════════════════════════════════════════════════");
            e.printStackTrace();

            ctx.status(500);
            ctx.json(new ErrorResponse("Internal server error: " + e.getMessage()));
        });

        // 404 handler
        app.error(404, ctx -> {
            System.err.println("│ ✗ 404 Not Found: " + ctx.path());
            ctx.json(new ErrorResponse("Endpoint not found: " + ctx.path()));
        });

        System.out.println("✓ REST API Server started successfully");
        System.out.println("   URL: http://localhost:" + port);
        System.out.println("   Health Check: http://localhost:" + port + "/health");
        System.out.println("   CORS: Enabled (all origins allowed)");
        System.out.println("   Logging: Detailed request/response logging enabled");
        System.out.println("   API Endpoints:");
        System.out.println("   - GET    /api/patients");
        System.out.println("   - GET    /api/patients/{id}");
        System.out.println("   - POST   /api/patients");
        System.out.println("   - PUT    /api/patients/{id}");
        System.out.println("   - DELETE /api/patients/{id}");
        System.out.println("\n=== Server is ready to accept requests ===");
        System.out.println("=== Waiting for connections... ===\n");
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

    static class ErrorResponse {
        private String error;

        public ErrorResponse(String error) {
            this.error = error;
        }

        public String getError() { return error; }
        public void setError(String error) { this.error = error; }
    }
}

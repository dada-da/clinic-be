package com.clinic;

import com.clinic.config.EnvConfig;
import com.clinic.config.FlywayMigration;
import org.flywaydb.core.api.output.MigrateResult;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== REST API Project Setup Check ===\n");

        try {
            // 1. Validate and print environment configuration
            System.out.println("1. Environment Configuration:");
            if (!EnvConfig.validateRequiredEnvVars()) {
                System.err.println("\n✗ Missing required environment variables!");
                System.err.println("Please check your .env file");
                return;
            }
            EnvConfig.printConfiguration();

            // 2. Test database connection
            System.out.println("2. Database Connection:");
            testDatabaseConnection();

            // 3. Run Flyway migrations
            System.out.println("3. Database Migrations (Flyway):");
            FlywayMigration migration = new FlywayMigration();

            // Print current migration status
            migration.printMigrationStatus();

            // Run migrations if needed
            if (migration.needsMigration()) {
                System.out.println("Running pending migrations...");
                MigrateResult result = migration.migrate();
            } else {
                System.out.println("No pending migrations.");
            }

            // Validate migrations
            migration.validate();

            System.out.println("Current database version: " + migration.getCurrentVersion());

            // 4. Verify database schema
            System.out.println("\n4. Database Schema Verification:");
            verifyDatabaseSchema();

            System.out.println("\n=== ✓ All checks passed! ===");
            System.out.println("Your project is ready to run.");
            System.out.println("\nNext steps:");
            System.out.println("1. Create your REST endpoints in the controller package");
            System.out.println("2. Add business logic in the service package");
            System.out.println("3. Implement data access in the repository package");

        } catch (Exception e) {
            System.err.println("\n✗ Setup check failed: " + e.getMessage());
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
                System.out.println("   Connected to: " + conn.getMetaData().getURL());
            }
        } catch (Exception e) {
            System.err.println("   ✗ Database connection failed: " + e.getMessage());
            System.err.println("   Please check:");
            System.err.println("   - MySQL is running");
            System.err.println("   - Database '" + EnvConfig.getDbName() + "' exists");
            System.err.println("   - Credentials in .env are correct");
            throw new RuntimeException("Database connection failed", e);
        }
    }

    private static void verifyDatabaseSchema() {
        try (Connection conn = DriverManager.getConnection(
                EnvConfig.getDbUrl(),
                EnvConfig.getDbUser(),
                EnvConfig.getDbPassword()
        )) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SHOW TABLES");

            System.out.println("   Tables in database:");
            boolean hasTables = false;
            while (rs.next()) {
                System.out.println("   - " + rs.getString(1));
                hasTables = true;
            }

            if (!hasTables) {
                System.out.println("   (No tables yet - migrations may not have run)");
            }

            System.out.println("   ✓ Database schema verified");
        } catch (Exception e) {
            System.err.println("   ✗ Schema verification failed: " + e.getMessage());
            throw new RuntimeException("Schema verification failed", e);
        }
    }
}

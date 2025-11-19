package com.clinic.config;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.MigrationInfo;
import org.flywaydb.core.api.MigrationInfoService;
import org.flywaydb.core.api.output.MigrateResult;
import org.flywaydb.core.api.configuration.FluentConfiguration;

import java.io.File;
import java.net.URL;

/**
 * Handles database migrations using Flyway
 * Provides methods to migrate, validate, and check migration status
 */
public class FlywayMigration {
    private final Flyway flyway;

    public FlywayMigration() {
        this.flyway = configureFlyway();
    }

    /**
     * Configure Flyway with database connection and settings
     */
    private Flyway configureFlyway() {
        FluentConfiguration config = Flyway.configure()
                .dataSource(
                        EnvConfig.getDbUrl(),
                        EnvConfig.getDbUser(),
                        EnvConfig.getDbPassword()
                )
                .locations("classpath:db/migration")
                .baselineOnMigrate(true)
                .baselineVersion("0")
                .table("flyway_schema_history")
                .validateOnMigrate(true)
                .cleanDisabled(true); // Safety: prevent accidental data loss

        return config.load();
    }

    /**
     * Debug: Check if migration files exist
     */
    public void checkMigrationFiles() {
        System.out.println("Checking for migration files...");

        // Check classpath
        URL migrationUrl = getClass().getClassLoader().getResource("db/migration");
        if (migrationUrl != null) {
            System.out.println("✓ Migration directory found: " + migrationUrl.getPath());

            File migrationDir = new File(migrationUrl.getPath());
            if (migrationDir.exists() && migrationDir.isDirectory()) {
                File[] files = migrationDir.listFiles((dir, name) -> name.endsWith(".sql"));
                if (files != null && files.length > 0) {
                    System.out.println("✓ Found " + files.length + " SQL migration file(s):");
                    for (File file : files) {
                        System.out.println("  - " + file.getName());
                    }
                } else {
                    System.err.println("✗ No SQL files found in migration directory");
                }
            }
        } else {
            System.err.println("✗ Migration directory 'db/migration' not found in classpath");
            System.err.println("  Expected location: src/main/resources/db/migration/");
        }
        System.out.println();
    }

    /**
     * Run all pending migrations
     */
    public MigrateResult migrate() {
        try {
            System.out.println("Starting database migration...");
            MigrateResult result = flyway.migrate();

            if (result.migrationsExecuted > 0) {
                System.out.println("✓ Successfully applied " + result.migrationsExecuted + " migration(s)");
                if (result.targetSchemaVersion != null) {
                    System.out.println("  Target version: " + result.targetSchemaVersion);
                }
            } else {
                System.out.println("✓ Database is up to date. No migrations needed.");
            }

            return result;
        } catch (Exception e) {
            System.err.println("✗ Migration failed: " + e.getMessage());
            throw new RuntimeException("Database migration failed", e);
        }
    }

    /**
     * Validate applied migrations against available migration scripts
     */
    public boolean validate() {
        try {
            System.out.println("Validating database migrations...");
            flyway.validate();
            System.out.println("✓ All migrations are valid");
            return true;
        } catch (Exception e) {
            System.err.println("✗ Migration validation failed: " + e.getMessage());
            return false;
        }
    }

    /**
     * Get information about all migrations
     */
    public MigrationInfoService info() {
        return flyway.info();
    }

    /**
     * Print migration status
     */
    public void printMigrationStatus() {
        System.out.println("=== Database Migration Status ===");

        MigrationInfoService infoService = flyway.info();
        MigrationInfo[] migrations = infoService.all();

        if (migrations.length == 0) {
            System.out.println("⚠ No migrations found in classpath");
            System.out.println("  Please ensure migration files exist at:");
            System.out.println("  src/main/resources/db/migration/V1__*.sql");
        } else {
            System.out.println("Total migrations: " + migrations.length);
            System.out.println("Pending migrations: " + infoService.pending().length);
            System.out.println("\nMigration Details:");
            System.out.println("-".repeat(80));
            System.out.printf("%-15s %-30s %-20s %-10s%n", "Version", "Description", "Installed On", "State");
            System.out.println("-".repeat(80));

            for (MigrationInfo migration : migrations) {
                System.out.printf("%-15s %-30s %-20s %-10s%n",
                        migration.getVersion() != null ? migration.getVersion() : "N/A",
                        truncate(migration.getDescription(), 30),
                        migration.getInstalledOn() != null ? migration.getInstalledOn().toString() : "Pending",
                        migration.getState());
            }
            System.out.println("-".repeat(80));
        }
        System.out.println("=================================\n");
    }

    /**
     * Check if database needs migration
     */
    public boolean needsMigration() {
        MigrationInfoService infoService = flyway.info();
        return infoService.pending().length > 0;
    }

    /**
     * Baseline the database (for existing databases without Flyway)
     */
    public void baseline() {
        try {
            System.out.println("Creating Flyway baseline...");
            flyway.baseline();
            System.out.println("✓ Baseline created successfully");
        } catch (Exception e) {
            System.err.println("✗ Baseline creation failed: " + e.getMessage());
            throw new RuntimeException("Baseline creation failed", e);
        }
    }

    /**
     * Repair Flyway schema history table
     * Use this to fix checksum mismatches or remove failed migrations
     */
    public void repair() {
        try {
            System.out.println("Repairing Flyway schema history...");
            flyway.repair();
            System.out.println("✓ Schema history repaired successfully");
        } catch (Exception e) {
            System.err.println("✗ Repair failed: " + e.getMessage());
            throw new RuntimeException("Schema history repair failed", e);
        }
    }

    /**
     * Get current database version
     */
    public String getCurrentVersion() {
        MigrationInfoService infoService = flyway.info();
        MigrationInfo current = infoService.current();
        return current != null && current.getVersion() != null ?
                current.getVersion().toString() : "No version";
    }

    private String truncate(String str, int length) {
        if (str == null) return "";
        return str.length() > length ? str.substring(0, length - 3) + "..." : str;
    }
}

-- ============================================
-- Clinic Database Schema - Simplified Version
-- Version: 1.0
-- Description: Core tables for patient appointments and medical records
-- ============================================

-- Drop tables if they exist (for clean recreation)
DROP TABLE IF EXISTS `medical_record`;
DROP TABLE IF EXISTS `appointment`;
DROP TABLE IF EXISTS `doctor`;
DROP TABLE IF EXISTS `patient`;

-- ============================================
-- Table: patient (with social_id as unique key)
-- ============================================
CREATE TABLE IF NOT EXISTS `patient`
(
    `id`         INT AUTO_INCREMENT PRIMARY KEY,
    `social_id`  VARCHAR(20) NOT NULL UNIQUE COMMENT 'National ID/SSN/Identity Number',
    `full_name`  VARCHAR(255) NOT NULL,
    `dob`        DATE,
    `gender`     ENUM('MALE', 'FEMALE', 'OTHER') NOT NULL,
    `address`    VARCHAR(500),
    `phone`      VARCHAR(20),
    `email`      VARCHAR(100),
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_social_id (`social_id`),
    INDEX idx_full_name (`full_name`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ============================================
-- Table: doctor
-- ============================================
CREATE TABLE IF NOT EXISTS `doctor`
(
    `id`         INT AUTO_INCREMENT PRIMARY KEY,
    `first_name` VARCHAR(255) NOT NULL,
    `last_name`  VARCHAR(255) NOT NULL,
    `specialty`  VARCHAR(255) NOT NULL,
    `phone`      VARCHAR(20),
    `email`      VARCHAR(100),
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_specialty (`specialty`),
    INDEX idx_name (`last_name`, `first_name`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ============================================
-- Table: appointment
-- ============================================
CREATE TABLE IF NOT EXISTS `appointment`
(
    `id`         INT AUTO_INCREMENT PRIMARY KEY,
    `patient_id` INT NOT NULL,
    `doctor_id`  INT NOT NULL,
    `date_time`  DATETIME NOT NULL,
    `reason`     TEXT COMMENT 'Reason for visit',
    `status`     ENUM('SCHEDULED', 'COMPLETED', 'CANCELLED', 'NO_SHOW') DEFAULT 'SCHEDULED',
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`) ON DELETE CASCADE,
    FOREIGN KEY (`doctor_id`) REFERENCES `doctor` (`id`) ON DELETE CASCADE,
    INDEX idx_appointment_patient (`patient_id`),
    INDEX idx_appointment_doctor (`doctor_id`),
    INDEX idx_appointment_datetime (`date_time`),
    INDEX idx_appointment_status (`status`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ============================================
-- Table: medical_record
-- ============================================
CREATE TABLE IF NOT EXISTS `medical_record`
(
    `id`             INT AUTO_INCREMENT PRIMARY KEY,
    `appointment_id` INT NOT NULL UNIQUE,
    `symptoms`       TEXT COMMENT 'Patient symptoms',
    `diagnosis`      TEXT COMMENT 'Doctor diagnosis',
    `treatment`      TEXT COMMENT 'Treatment plan, doctor orders, and prescribed medicines as free text',
    `created_at`     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (`appointment_id`) REFERENCES `appointment` (`id`) ON DELETE CASCADE,
    INDEX idx_medical_record_appointment (`appointment_id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

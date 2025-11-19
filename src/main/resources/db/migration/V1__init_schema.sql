-- SQL schema for clinic DB (MySQL)
CREATE DATABASE IF NOT EXISTS clinicdb CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE clinicdb;

-- Drop tables if they exist (for clean recreation)
DROP TABLE IF EXISTS `prescription_item`;
DROP TABLE IF EXISTS `prescription`;
DROP TABLE IF EXISTS `medical_record`;
DROP TABLE IF EXISTS `appointment`;
DROP TABLE IF EXISTS `medicine`;
DROP TABLE IF EXISTS `doctor`;
DROP TABLE IF EXISTS `patient`;

CREATE TABLE IF NOT EXISTS `patient`
(
    `id`         INT AUTO_INCREMENT PRIMARY KEY,
    `full_name`  VARCHAR(255) NOT NULL,
    `dob`        DATE,
    `gender`     ENUM('MALE', 'FEMALE', 'OTHER') NOT NULL,
    `address`    VARCHAR(500),
    `phone`      VARCHAR(20),
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
    );

CREATE TABLE IF NOT EXISTS `doctor`
(
    `id`         INT AUTO_INCREMENT PRIMARY KEY,
    `first_name` VARCHAR(255) NOT NULL,
    `last_name`  VARCHAR(255) NOT NULL,
    `specialty`  ENUM('GENERAL', 'CARDIOLOGY', 'PEDIATRICS', 'DERMATOLOGY', 'ORTHOPEDICS', 'NEUROLOGY') NOT NULL,
    `phone`      VARCHAR(20),
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

CREATE TABLE IF NOT EXISTS `appointment`
(
    `id`         INT AUTO_INCREMENT PRIMARY KEY,
    `patient_id` INT NOT NULL,
    `doctor_id`  INT NOT NULL,
    `date_time`  DATETIME NOT NULL,
    `status`     ENUM('SCHEDULED', 'COMPLETED', 'CANCELLED', 'NO_SHOW') DEFAULT 'SCHEDULED',
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`) ON DELETE CASCADE,
    FOREIGN KEY (`doctor_id`) REFERENCES `doctor` (`id`) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS `medical_record`
(
    `id`             INT AUTO_INCREMENT PRIMARY KEY,
    `appointment_id` INT NOT NULL UNIQUE,
    `symptoms`       TEXT,
    `diagnosis`      TEXT,
    `notes`          TEXT,
    `created_at`     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (`appointment_id`) REFERENCES `appointment` (`id`) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS `prescription`
(
    `id`                INT AUTO_INCREMENT PRIMARY KEY,
    `medical_record_id` INT NOT NULL,
    `doctor_id`         INT NOT NULL,
    `created_at`        TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (`medical_record_id`) REFERENCES `medical_record` (`id`) ON DELETE CASCADE,
    FOREIGN KEY (`doctor_id`) REFERENCES `doctor` (`id`) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS `medicine`
(
    `id`             INT AUTO_INCREMENT PRIMARY KEY,
    `name`           VARCHAR(255) NOT NULL,
    `description`    TEXT,
    `price`          DECIMAL(10, 2) NOT NULL,
    `stock_quantity` INT DEFAULT 0,
    `created_at`     TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

CREATE TABLE IF NOT EXISTS `prescription_item`
(
    `id`              INT AUTO_INCREMENT PRIMARY KEY,
    `prescription_id` INT NOT NULL,
    `medicine_id`     INT NOT NULL,
    `quantity`        INT NOT NULL,
    `dosage`          VARCHAR(255) NOT NULL,
    FOREIGN KEY (`prescription_id`) REFERENCES `prescription` (`id`) ON DELETE CASCADE,
    FOREIGN KEY (`medicine_id`) REFERENCES `medicine` (`id`) ON DELETE CASCADE
    );

-- Create indexes for better performance
CREATE INDEX idx_appointment_patient ON `appointment`(`patient_id`);
CREATE INDEX idx_appointment_doctor ON `appointment`(`doctor_id`);
CREATE INDEX idx_appointment_datetime ON `appointment`(`date_time`);
CREATE INDEX idx_medical_record_appointment ON `medical_record`(`appointment_id`);
CREATE INDEX idx_prescription_medical_record ON `prescription`(`medical_record_id`);

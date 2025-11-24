package com.clinic.service;

import com.clinic.model.Patient;
import com.clinic.repository.PatientRepository;

import java.sql.SQLException;
import java.util.List;

public class PatientService {
    private final PatientRepository patientRepository;

    public PatientService() {
        this.patientRepository = new PatientRepository();
    }

    public List<Patient> getAllPatients() throws SQLException {
        return patientRepository.findAll();
    }

    public Patient getPatientById(Integer id) throws SQLException {
        return patientRepository.findById(id);
    }

    public Patient getPatientBySocialId(String socialId) throws SQLException {
        return patientRepository.findBySocialId(socialId);
    }

    public Patient createPatient(Patient patient) throws SQLException {
        validatePatient(patient);

        if (patientRepository.existsBySocialId(patient.getSocialId())) {
            throw new IllegalArgumentException("Patient with social ID " + patient.getSocialId() + " already exists");
        }

        return patientRepository.create(patient);
    }

    public Patient updatePatient(Integer id, Patient patient) throws SQLException {
        validatePatient(patient);

        Patient existing = patientRepository.findById(id);
        if (existing == null) {
            return null;
        }

        if (!existing.getSocialId().equals(patient.getSocialId())) {
            if (patientRepository.existsBySocialId(patient.getSocialId())) {
                throw new IllegalArgumentException("Another patient with social ID " + patient.getSocialId() + " already exists");
            }
        }

        return patientRepository.update(id, patient);
    }

    public boolean deletePatient(Integer id) throws SQLException {
        return patientRepository.delete(id);
    }

    public List<Patient> searchPatientsByName(String name) throws SQLException {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Search name cannot be empty");
        }
        return patientRepository.searchByName(name);
    }

    private void validatePatient(Patient patient) {
        if (patient.getSocialId() == null || patient.getSocialId().trim().isEmpty()) {
            throw new IllegalArgumentException("Social ID is required");
        }
        if (patient.getFullName() == null || patient.getFullName().trim().isEmpty()) {
            throw new IllegalArgumentException("Patient name is required");
        }
        if (patient.getGender() == null || patient.getGender().trim().isEmpty()) {
            throw new IllegalArgumentException("Gender is required");
        }

        // Validate social ID format (example: alphanumeric, 9-12 characters)
        if (!patient.getSocialId().matches("^[A-Z0-9]{9,12}$")) {
            throw new IllegalArgumentException("Invalid social ID format. Must be 9-12 alphanumeric characters.");
        }
    }
}

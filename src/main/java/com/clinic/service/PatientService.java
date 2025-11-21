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

    public Patient createPatient(Patient patient) throws SQLException {
        validatePatient(patient);
        return patientRepository.create(patient);
    }

    public Patient updatePatient(Integer id, Patient patient) throws SQLException {
        validatePatient(patient);
        return patientRepository.update(id, patient);
    }

    public boolean deletePatient(Integer id) throws SQLException {
        return patientRepository.delete(id);
    }

    private void validatePatient(Patient patient) {
        if (patient.getFullName() == null || patient.getFullName().trim().isEmpty()) {
            throw new IllegalArgumentException("Patient name is required");
        }
        if (patient.getGender() == null || patient.getGender().trim().isEmpty()) {
            throw new IllegalArgumentException("Gender is required");
        }
    }
}

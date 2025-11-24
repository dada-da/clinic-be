package com.clinic.service;

import com.clinic.model.MedicalRecord;
import com.clinic.repository.MedicalRecordRepository;

import java.sql.SQLException;

public class MedicalRecordService {
    private final MedicalRecordRepository medicalRecordRepository;

    public MedicalRecordService() {
        this.medicalRecordRepository = new MedicalRecordRepository();
    }

    public MedicalRecord getMedicalRecordByAppointmentId(Integer appointmentId) throws SQLException {
        return medicalRecordRepository.findByAppointmentId(appointmentId);
    }

    public MedicalRecord createMedicalRecord(MedicalRecord medicalRecord) throws SQLException {
        validateMedicalRecord(medicalRecord);
        return medicalRecordRepository.create(medicalRecord);
    }

    private void validateMedicalRecord(MedicalRecord medicalRecord) {
        if (medicalRecord.getAppointmentId() == null) {
            throw new IllegalArgumentException("Appointment ID is required");
        }
        if (medicalRecord.getDiagnosis() == null || medicalRecord.getDiagnosis().trim().isEmpty()) {
            throw new IllegalArgumentException("Diagnosis is required");
        }
    }
}

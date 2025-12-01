package com.clinic.repository;

import com.clinic.config.DatabaseConnection;
import com.clinic.model.MedicalRecord;

import java.sql.*;

public class MedicalRecordRepository {
    private final DatabaseConnection dbConnection;

    public MedicalRecordRepository() {
        this.dbConnection = DatabaseConnection.getInstance();
    }

    public MedicalRecord findByAppointmentId(Integer appointmentId) throws SQLException {
        String sql = "SELECT * FROM medical_record WHERE appointment_id = ?";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, appointmentId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToMedicalRecord(rs);
                }
            }
        }

        return null;
    }

    public MedicalRecord create(MedicalRecord medicalRecord) throws SQLException {
        String sql = "INSERT INTO medical_record (appointment_id, symptoms, diagnosis, treatment) " +
                "VALUES (?, ?, ?, ?)";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, medicalRecord.getAppointmentId());
            pstmt.setString(2, medicalRecord.getSymptoms());
            pstmt.setString(3, medicalRecord.getDiagnosis());
            pstmt.setString(4, medicalRecord.getTreatment());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating medical record failed, no rows affected.");
            }

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    medicalRecord.setId(generatedKeys.getInt(1));
                    return findByAppointmentId(medicalRecord.getAppointmentId());
                } else {
                    throw new SQLException("Creating medical record failed, no ID obtained.");
                }
            }
        }
    }

    private MedicalRecord mapResultSetToMedicalRecord(ResultSet rs) throws SQLException {
        MedicalRecord record = new MedicalRecord();
        record.setId(rs.getInt("id"));
        record.setAppointmentId(rs.getInt("appointment_id"));
        record.setSymptoms(rs.getString("symptoms"));
        record.setDiagnosis(rs.getString("diagnosis"));
        record.setTreatment(rs.getString("treatment"));

        Timestamp createdAt = rs.getTimestamp("created_at");
        if (createdAt != null) {
            record.setCreatedAt(createdAt.toLocalDateTime());
        }

        return record;
    }
}

package com.clinic.repository;

import com.clinic.config.DatabaseConnection;
import com.clinic.model.Patient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientRepository {
    private final DatabaseConnection dbConnection;

    public PatientRepository() {
        this.dbConnection = DatabaseConnection.getInstance();
    }

    public List<Patient> findAll() throws SQLException {
        List<Patient> patients = new ArrayList<>();
        String sql = "SELECT * FROM patient ORDER BY id";

        try (Connection conn = dbConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                patients.add(mapResultSetToPatient(rs));
            }
        }

        return patients;
    }

    public Patient findById(Integer id) throws SQLException {
        String sql = "SELECT * FROM patient WHERE id = ?";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToPatient(rs);
                }
            }
        }

        return null;
    }

    public Patient create(Patient patient) throws SQLException {
        String sql = "INSERT INTO patient (full_name, dob, gender, address, phone) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, patient.getFullName());
            pstmt.setDate(2, patient.getDob() != null ? Date.valueOf(patient.getDob()) : null);
            pstmt.setString(3, patient.getGender());
            pstmt.setString(4, patient.getAddress());
            pstmt.setString(5, patient.getPhone());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating patient failed, no rows affected.");
            }

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    patient.setId(generatedKeys.getInt(1));
                    return findById(patient.getId());
                } else {
                    throw new SQLException("Creating patient failed, no ID obtained.");
                }
            }
        }
    }

    public Patient update(Integer id, Patient patient) throws SQLException {
        String sql = "UPDATE patient SET full_name = ?, dob = ?, gender = ?, address = ?, phone = ? WHERE id = ?";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, patient.getFullName());
            pstmt.setDate(2, patient.getDob() != null ? Date.valueOf(patient.getDob()) : null);
            pstmt.setString(3, patient.getGender());
            pstmt.setString(4, patient.getAddress());
            pstmt.setString(5, patient.getPhone());
            pstmt.setInt(6, id);

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
                return null;
            }

            return findById(id);
        }
    }

    public boolean delete(Integer id) throws SQLException {
        String sql = "DELETE FROM patient WHERE id = ?";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            int affectedRows = pstmt.executeUpdate();

            return affectedRows > 0;
        }
    }

    private Patient mapResultSetToPatient(ResultSet rs) throws SQLException {
        Patient patient = new Patient();
        patient.setId(rs.getInt("id"));
        patient.setFullName(rs.getString("full_name"));

        Date dob = rs.getDate("dob");
        if (dob != null) {
            patient.setDob(dob.toLocalDate());
        }

        patient.setGender(rs.getString("gender"));
        patient.setAddress(rs.getString("address"));
        patient.setPhone(rs.getString("phone"));

        Timestamp createdAt = rs.getTimestamp("created_at");
        if (createdAt != null) {
            patient.setCreatedAt(createdAt.toLocalDateTime());
        }

        Timestamp updatedAt = rs.getTimestamp("updated_at");
        if (updatedAt != null) {
            patient.setUpdatedAt(updatedAt.toLocalDateTime());
        }

        return patient;
    }
}

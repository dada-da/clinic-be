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

    public Patient findBySocialId(String socialId) throws SQLException {
        String sql = "SELECT * FROM patient WHERE social_id = ?";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, socialId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToPatient(rs);
                }
            }
        }

        return null;
    }

    public boolean existsBySocialId(String socialId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM patient WHERE social_id = ?";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, socialId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }

        return false;
    }

    public Patient create(Patient patient) throws SQLException {
        String sql = "INSERT INTO patient (social_id, full_name, dob, gender, address, phone, email) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, patient.getSocialId());
            pstmt.setString(2, patient.getFullName());
            pstmt.setDate(3, patient.getDob() != null ? Date.valueOf(patient.getDob()) : null);
            pstmt.setString(4, patient.getGender());
            pstmt.setString(5, patient.getAddress());
            pstmt.setString(6, patient.getPhone());
            pstmt.setString(7, patient.getEmail());

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
        String sql = "UPDATE patient SET social_id = ?, full_name = ?, dob = ?, gender = ?, " +
                "address = ?, phone = ?, email = ? WHERE id = ?";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, patient.getSocialId());
            pstmt.setString(2, patient.getFullName());
            pstmt.setDate(3, patient.getDob() != null ? Date.valueOf(patient.getDob()) : null);
            pstmt.setString(4, patient.getGender());
            pstmt.setString(5, patient.getAddress());
            pstmt.setString(6, patient.getPhone());
            pstmt.setString(7, patient.getEmail());
            pstmt.setInt(8, id);

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

    public List<Patient> searchByName(String name) throws SQLException {
        List<Patient> patients = new ArrayList<>();
        String sql = "SELECT * FROM patient WHERE full_name LIKE ? ORDER BY full_name";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%" + name + "%");

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    patients.add(mapResultSetToPatient(rs));
                }
            }
        }

        return patients;
    }

    private Patient mapResultSetToPatient(ResultSet rs) throws SQLException {
        Patient patient = new Patient();
        patient.setId(rs.getInt("id"));
        patient.setSocialId(rs.getString("social_id"));
        patient.setFullName(rs.getString("full_name"));

        Date dob = rs.getDate("dob");
        if (dob != null) {
            patient.setDob(dob.toLocalDate());
        }

        patient.setGender(rs.getString("gender"));
        patient.setAddress(rs.getString("address"));
        patient.setPhone(rs.getString("phone"));
        patient.setEmail(rs.getString("email"));

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

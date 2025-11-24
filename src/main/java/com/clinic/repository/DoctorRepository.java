package com.clinic.repository;

import com.clinic.config.DatabaseConnection;
import com.clinic.model.Doctor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorRepository {
    private final DatabaseConnection dbConnection;

    public DoctorRepository() {
        this.dbConnection = DatabaseConnection.getInstance();
    }

    public List<Doctor> findAll() throws SQLException {
        List<Doctor> doctors = new ArrayList<>();
        String sql = "SELECT * FROM doctor ORDER BY last_name, first_name";

        try (Connection conn = dbConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                doctors.add(mapResultSetToDoctor(rs));
            }
        }

        return doctors;
    }

    public Doctor findById(Integer id) throws SQLException {
        String sql = "SELECT * FROM doctor WHERE id = ?";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToDoctor(rs);
                }
            }
        }

        return null;
    }

    public List<Doctor> findBySpecialty(String specialty) throws SQLException {
        List<Doctor> doctors = new ArrayList<>();
        String sql = "SELECT * FROM doctor WHERE specialty = ? ORDER BY last_name, first_name";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, specialty);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    doctors.add(mapResultSetToDoctor(rs));
                }
            }
        }

        return doctors;
    }

    private Doctor mapResultSetToDoctor(ResultSet rs) throws SQLException {
        Doctor doctor = new Doctor();
        doctor.setId(rs.getInt("id"));
        doctor.setFirstName(rs.getString("first_name"));
        doctor.setLastName(rs.getString("last_name"));
        doctor.setSpecialty(rs.getString("specialty"));
        doctor.setPhone(rs.getString("phone"));

        Timestamp createdAt = rs.getTimestamp("created_at");
        if (createdAt != null) {
            doctor.setCreatedAt(createdAt.toLocalDateTime());
        }

        return doctor;
    }
}

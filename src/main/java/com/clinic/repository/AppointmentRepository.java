package com.clinic.repository;

import com.clinic.config.DatabaseConnection;
import com.clinic.model.Appointment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentRepository {
    private final DatabaseConnection dbConnection;

    public AppointmentRepository() {
        this.dbConnection = DatabaseConnection.getInstance();
    }

    public List<Appointment> findAll() throws SQLException {
        List<Appointment> appointments = new ArrayList<>();
        String sql = "SELECT a.*, p.full_name as patient_name, " +
                "CONCAT(d.first_name, ' ', d.last_name) as doctor_name " +
                "FROM appointment a " +
                "JOIN patient p ON a.patient_id = p.id " +
                "JOIN doctor d ON a.doctor_id = d.id " +
                "ORDER BY a.date_time DESC";

        try (Connection conn = dbConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                appointments.add(mapResultSetToAppointment(rs));
            }
        }

        return appointments;
    }

    public Appointment findById(Integer id) throws SQLException {
        String sql = "SELECT a.*, p.full_name as patient_name, " +
                "CONCAT(d.first_name, ' ', d.last_name) as doctor_name " +
                "FROM appointment a " +
                "JOIN patient p ON a.patient_id = p.id " +
                "JOIN doctor d ON a.doctor_id = d.id " +
                "WHERE a.id = ?";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToAppointment(rs);
                }
            }
        }

        return null;
    }

    public Appointment create(Appointment appointment) throws SQLException {
        String sql = "INSERT INTO appointment (patient_id, doctor_id, date_time, status, reason) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, appointment.getPatientId());
            pstmt.setInt(2, appointment.getDoctorId());
            pstmt.setTimestamp(3, Timestamp.valueOf(appointment.getDateTime()));
            pstmt.setString(4, appointment.getStatus());
            pstmt.setString(5, appointment.getReason());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating appointment failed, no rows affected.");
            }

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    appointment.setId(generatedKeys.getInt(1));
                    return findById(appointment.getId());
                } else {
                    throw new SQLException("Creating appointment failed, no ID obtained.");
                }
            }
        }
    }

    public Appointment updateStatus(Integer id, String status) throws SQLException {
        String sql = "UPDATE appointment SET status = ? WHERE id = ?";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, status);
            pstmt.setInt(2, id);

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
                return null;
            }

            return findById(id);
        }
    }

    private Appointment mapResultSetToAppointment(ResultSet rs) throws SQLException {
        Appointment appointment = new Appointment();
        appointment.setId(rs.getInt("id"));
        appointment.setPatientId(rs.getInt("patient_id"));
        appointment.setDoctorId(rs.getInt("doctor_id"));

        Timestamp dateTime = rs.getTimestamp("date_time");
        if (dateTime != null) {
            appointment.setDateTime(dateTime.toLocalDateTime());
        }

        appointment.setStatus(rs.getString("status"));
        appointment.setReason(rs.getString("reason"));

        Timestamp createdAt = rs.getTimestamp("created_at");
        if (createdAt != null) {
            appointment.setCreatedAt(createdAt.toLocalDateTime());
        }

        // Joined fields
        appointment.setPatientName(rs.getString("patient_name"));
        appointment.setDoctorName(rs.getString("doctor_name"));

        return appointment;
    }
}

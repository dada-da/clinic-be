package com.clinic.repository;

import com.clinic.config.DatabaseConnection;
import com.clinic.model.Appointment;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AppointmentRepository {
    private final DatabaseConnection dbConnection;

    public AppointmentRepository() {
        this.dbConnection = DatabaseConnection.getInstance();
    }

    public List<Appointment> findAll() throws SQLException {
        return findWithFilters(null, null, null, null, null, null, null);
    }

    /**
     * Find appointments with filters following REST API query parameter standards
     *
     * @param startDate Filter by date range start (inclusive)
     * @param endDate Filter by date range end (inclusive)
     * @param patientFirstName Filter by patient first name (partial match, case-insensitive)
     * @param patientLastName Filter by patient last name (partial match, case-insensitive)
     * @param doctorFirstName Filter by doctor first name (partial match, case-insensitive)
     * @param doctorLastName Filter by doctor last name (partial match, case-insensitive)
     * @param status Filter by exact status match
     * @return List of appointments matching the filters
     * @throws SQLException if database error occurs
     */
    public List<Appointment> findWithFilters(
            LocalDate startDate,
            LocalDate endDate,
            String patientFirstName,
            String patientLastName,
            String doctorFirstName,
            String doctorLastName,
            String status) throws SQLException {

        List<Appointment> appointments = new ArrayList<>();
        StringBuilder sql = new StringBuilder(
                "SELECT a.*, p.full_name as patient_name, " +
                        "CONCAT(d.first_name, ' ', d.last_name) as doctor_name " +
                        "FROM appointment a " +
                        "JOIN patient p ON a.patient_id = p.id " +
                        "JOIN doctor d ON a.doctor_id = d.id " +
                        "WHERE 1=1 ");

        List<Object> params = new ArrayList<>();

        // Date range filter
        if (startDate != null) {
            sql.append("AND DATE(a.date_time) >= ? ");
            params.add(Date.valueOf(startDate));
        }
        if (endDate != null) {
            sql.append("AND DATE(a.date_time) <= ? ");
            params.add(Date.valueOf(endDate));
        }

        // Patient name filters (partial match, case-insensitive)
        if (patientFirstName != null && !patientFirstName.trim().isEmpty()) {
            sql.append("AND LOWER(p.full_name) LIKE LOWER(?) ");
            params.add("%" + patientFirstName.trim() + "%");
        }
        if (patientLastName != null && !patientLastName.trim().isEmpty()) {
            sql.append("AND LOWER(p.full_name) LIKE LOWER(?) ");
            params.add("%" + patientLastName.trim() + "%");
        }

        // Doctor name filters (partial match, case-insensitive)
        if (doctorFirstName != null && !doctorFirstName.trim().isEmpty()) {
            sql.append("AND LOWER(d.first_name) LIKE LOWER(?) ");
            params.add("%" + doctorFirstName.trim() + "%");
        }
        if (doctorLastName != null && !doctorLastName.trim().isEmpty()) {
            sql.append("AND LOWER(d.last_name) LIKE LOWER(?) ");
            params.add("%" + doctorLastName.trim() + "%");
        }

        // Status filter (exact match)
        if (status != null && !status.trim().isEmpty()) {
            sql.append("AND a.status = ? ");
            params.add(status.trim());
        }

        sql.append("ORDER BY a.date_time DESC");

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {

            // Set parameters
            for (int i = 0; i < params.size(); i++) {
                Object param = params.get(i);
                if (param instanceof Date) {
                    pstmt.setDate(i + 1, (Date) param);
                } else if (param instanceof String) {
                    pstmt.setString(i + 1, (String) param);
                }
            }

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    appointments.add(mapResultSetToAppointment(rs));
                }
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

    public List<Appointment> findByDate(LocalDate date) throws SQLException {
        return findWithFilters(date, date, null, null, null, null, null);
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

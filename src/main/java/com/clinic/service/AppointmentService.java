package com.clinic.service;

import com.clinic.model.Appointment;
import com.clinic.repository.AppointmentRepository;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class AppointmentService {
    private final AppointmentRepository appointmentRepository;

    public AppointmentService() {
        this.appointmentRepository = new AppointmentRepository();
    }

    public List<Appointment> getAllAppointments() throws SQLException {
        return appointmentRepository.findAll();
    }

    public List<Appointment> getAppointmentsByDate(LocalDate date) throws SQLException {
        return appointmentRepository.findByDate(date);
    }

    public List<Appointment> getTodayAppointments() throws SQLException {
        return appointmentRepository.findByDate(LocalDate.now());
    }

    public Appointment getAppointmentById(Integer id) throws SQLException {
        return appointmentRepository.findById(id);
    }

    public Appointment createAppointment(Appointment appointment) throws SQLException {
        validateAppointment(appointment);

        if (appointment.getStatus() == null || appointment.getStatus().isEmpty()) {
            appointment.setStatus("SCHEDULED");
        }

        if (appointment.getDateTime() == null) {
            appointment.setDateTime(LocalDateTime.now());
        }

        return appointmentRepository.create(appointment);
    }

    public Appointment completeAppointment(Integer id) throws SQLException {
        return appointmentRepository.updateStatus(id, "COMPLETED");
    }

    public Appointment cancelAppointment(Integer id) throws SQLException {
        return appointmentRepository.updateStatus(id, "CANCELLED");
    }

    private void validateAppointment(Appointment appointment) {
        if (appointment.getPatientId() == null) {
            throw new IllegalArgumentException("Patient ID is required");
        }
        if (appointment.getDoctorId() == null) {
            throw new IllegalArgumentException("Doctor ID is required");
        }
    }
}

package com.clinic.controller;

import com.clinic.model.Appointment;
import com.clinic.service.AppointmentService;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;

import java.sql.SQLException;
import java.util.List;

public class AppointmentController {
    private final AppointmentService appointmentService;

    public AppointmentController() {
        this.appointmentService = new AppointmentService();
    }

    public void getAllAppointments(Context ctx) {
        try {
            List<Appointment> appointments = appointmentService.getAllAppointments();
            ctx.json(appointments);
        } catch (SQLException e) {
            ctx.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .json(new ErrorResponse("Failed to fetch appointments: " + e.getMessage()));
        }
    }

    public void getAppointmentById(Context ctx) {
        try {
            Integer id = Integer.parseInt(ctx.pathParam("id"));
            Appointment appointment = appointmentService.getAppointmentById(id);

            if (appointment != null) {
                ctx.json(appointment);
            } else {
                ctx.status(HttpStatus.NOT_FOUND)
                        .json(new ErrorResponse("Appointment not found with id: " + id));
            }
        } catch (NumberFormatException e) {
            ctx.status(HttpStatus.BAD_REQUEST)
                    .json(new ErrorResponse("Invalid appointment ID"));
        } catch (SQLException e) {
            ctx.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .json(new ErrorResponse("Failed to fetch appointment: " + e.getMessage()));
        }
    }

    public void createAppointment(Context ctx) {
        try {
            Appointment appointment = ctx.bodyAsClass(Appointment.class);
            Appointment createdAppointment = appointmentService.createAppointment(appointment);
            ctx.status(HttpStatus.CREATED).json(createdAppointment);
        } catch (IllegalArgumentException e) {
            ctx.status(HttpStatus.BAD_REQUEST)
                    .json(new ErrorResponse(e.getMessage()));
        } catch (SQLException e) {
            ctx.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .json(new ErrorResponse("Failed to create appointment: " + e.getMessage()));
        }
    }

    public void completeAppointment(Context ctx) {
        try {
            Integer id = Integer.parseInt(ctx.pathParam("id"));
            Appointment appointment = appointmentService.completeAppointment(id);

            if (appointment != null) {
                ctx.json(appointment);
            } else {
                ctx.status(HttpStatus.NOT_FOUND)
                        .json(new ErrorResponse("Appointment not found with id: " + id));
            }
        } catch (NumberFormatException e) {
            ctx.status(HttpStatus.BAD_REQUEST)
                    .json(new ErrorResponse("Invalid appointment ID"));
        } catch (SQLException e) {
            ctx.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .json(new ErrorResponse("Failed to complete appointment: " + e.getMessage()));
        }
    }

    public static class ErrorResponse {
        private String error;

        public ErrorResponse(String error) {
            this.error = error;
        }

        public String getError() { return error; }
        public void setError(String error) { this.error = error; }
    }
}

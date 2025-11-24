package com.clinic.controller;

import com.clinic.model.Doctor;
import com.clinic.service.DoctorService;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;

import java.sql.SQLException;
import java.util.List;

public class DoctorController {
    private final DoctorService doctorService;

    public DoctorController() {
        this.doctorService = new DoctorService();
    }

    public void getAllDoctors(Context ctx) {
        try {
            List<Doctor> doctors = doctorService.getAllDoctors();
            ctx.json(doctors);
        } catch (SQLException e) {
            ctx.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .json(new ErrorResponse("Failed to fetch doctors: " + e.getMessage()));
        }
    }

    public void getDoctorById(Context ctx) {
        try {
            Integer id = Integer.parseInt(ctx.pathParam("id"));
            Doctor doctor = doctorService.getDoctorById(id);

            if (doctor != null) {
                ctx.json(doctor);
            } else {
                ctx.status(HttpStatus.NOT_FOUND)
                        .json(new ErrorResponse("Doctor not found with id: " + id));
            }
        } catch (NumberFormatException e) {
            ctx.status(HttpStatus.BAD_REQUEST)
                    .json(new ErrorResponse("Invalid doctor ID"));
        } catch (SQLException e) {
            ctx.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .json(new ErrorResponse("Failed to fetch doctor: " + e.getMessage()));
        }
    }

    public void getDoctorsBySpecialty(Context ctx) {
        try {
            String specialty = ctx.pathParam("specialty");
            List<Doctor> doctors = doctorService.getDoctorsBySpecialty(specialty);
            ctx.json(doctors);
        } catch (SQLException e) {
            ctx.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .json(new ErrorResponse("Failed to fetch doctors: " + e.getMessage()));
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

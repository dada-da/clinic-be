package com.clinic.controller;

import com.clinic.model.Patient;
import com.clinic.service.PatientService;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;

import java.sql.SQLException;
import java.util.List;

public class PatientController {
    private final PatientService patientService;

    public PatientController() {
        this.patientService = new PatientService();
    }

    public void getAllPatients(Context ctx) {
        try {
            List<Patient> patients = patientService.getAllPatients();
            ctx.json(patients);
        } catch (SQLException e) {
            ctx.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .json(new ErrorResponse("Failed to fetch patients: " + e.getMessage()));
        }
    }

    public void getPatientById(Context ctx) {
        try {
            Integer id = Integer.parseInt(ctx.pathParam("id"));
            Patient patient = patientService.getPatientById(id);

            if (patient != null) {
                ctx.json(patient);
            } else {
                ctx.status(HttpStatus.NOT_FOUND)
                        .json(new ErrorResponse("Patient not found with id: " + id));
            }
        } catch (NumberFormatException e) {
            ctx.status(HttpStatus.BAD_REQUEST)
                    .json(new ErrorResponse("Invalid patient ID"));
        } catch (SQLException e) {
            ctx.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .json(new ErrorResponse("Failed to fetch patient: " + e.getMessage()));
        }
    }

    public void getPatientBySocialId(Context ctx) {
        try {
            String socialId = ctx.pathParam("socialId");
            Patient patient = patientService.getPatientBySocialId(socialId);

            if (patient != null) {
                ctx.json(patient);
            } else {
                ctx.status(HttpStatus.NOT_FOUND)
                        .json(new ErrorResponse("Patient not found with social ID: " + socialId));
            }
        } catch (SQLException e) {
            ctx.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .json(new ErrorResponse("Failed to fetch patient: " + e.getMessage()));
        }
    }

    public void searchPatients(Context ctx) {
        try {
            String name = ctx.queryParam("name");

            if (name == null || name.trim().isEmpty()) {
                ctx.status(HttpStatus.BAD_REQUEST)
                        .json(new ErrorResponse("Search name parameter is required"));
                return;
            }

            List<Patient> patients = patientService.searchPatientsByName(name);
            ctx.json(patients);
        } catch (IllegalArgumentException e) {
            ctx.status(HttpStatus.BAD_REQUEST)
                    .json(new ErrorResponse(e.getMessage()));
        } catch (SQLException e) {
            ctx.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .json(new ErrorResponse("Failed to search patients: " + e.getMessage()));
        }
    }

    public void createPatient(Context ctx) {
        try {
            Patient patient = ctx.bodyAsClass(Patient.class);
            Patient createdPatient = patientService.createPatient(patient);
            ctx.status(HttpStatus.CREATED).json(createdPatient);
        } catch (IllegalArgumentException e) {
            ctx.status(HttpStatus.BAD_REQUEST)
                    .json(new ErrorResponse(e.getMessage()));
        } catch (SQLException e) {
            ctx.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .json(new ErrorResponse("Failed to create patient: " + e.getMessage()));
        }
    }

    public void updatePatient(Context ctx) {
        try {
            Integer id = Integer.parseInt(ctx.pathParam("id"));
            Patient patient = ctx.bodyAsClass(Patient.class);
            Patient updatedPatient = patientService.updatePatient(id, patient);

            if (updatedPatient != null) {
                ctx.json(updatedPatient);
            } else {
                ctx.status(HttpStatus.NOT_FOUND)
                        .json(new ErrorResponse("Patient not found with id: " + id));
            }
        } catch (NumberFormatException e) {
            ctx.status(HttpStatus.BAD_REQUEST)
                    .json(new ErrorResponse("Invalid patient ID"));
        } catch (IllegalArgumentException e) {
            ctx.status(HttpStatus.BAD_REQUEST)
                    .json(new ErrorResponse(e.getMessage()));
        } catch (SQLException e) {
            ctx.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .json(new ErrorResponse("Failed to update patient: " + e.getMessage()));
        }
    }

    public void deletePatient(Context ctx) {
        try {
            Integer id = Integer.parseInt(ctx.pathParam("id"));
            boolean deleted = patientService.deletePatient(id);

            if (deleted) {
                ctx.status(HttpStatus.NO_CONTENT);
            } else {
                ctx.status(HttpStatus.NOT_FOUND)
                        .json(new ErrorResponse("Patient not found with id: " + id));
            }
        } catch (NumberFormatException e) {
            ctx.status(HttpStatus.BAD_REQUEST)
                    .json(new ErrorResponse("Invalid patient ID"));
        } catch (SQLException e) {
            ctx.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .json(new ErrorResponse("Failed to delete patient: " + e.getMessage()));
        }
    }

    // Error response class
    public static class ErrorResponse {
        private String error;

        public ErrorResponse(String error) {
            this.error = error;
        }

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }
    }
}

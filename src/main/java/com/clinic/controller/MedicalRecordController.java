package com.clinic.controller;

import com.clinic.model.MedicalRecord;
import com.clinic.service.AppointmentService;
import com.clinic.service.MedicalRecordService;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;

import java.sql.SQLException;

public class MedicalRecordController {
    private final MedicalRecordService medicalRecordService;
    private final AppointmentService appointmentService;

    public MedicalRecordController() {
        this.medicalRecordService = new MedicalRecordService();
        this.appointmentService = new AppointmentService();
    }

    public void getMedicalRecordByAppointmentId(Context ctx) {
        try {
            Integer appointmentId = Integer.parseInt(ctx.pathParam("appointmentId"));
            MedicalRecord record = medicalRecordService.getMedicalRecordByAppointmentId(appointmentId);

            if (record != null) {
                ctx.json(record);
            } else {
                ctx.status(HttpStatus.NOT_FOUND)
                        .json(new ErrorResponse("Medical record not found for appointment id: " + appointmentId));
            }
        } catch (NumberFormatException e) {
            ctx.status(HttpStatus.BAD_REQUEST)
                    .json(new ErrorResponse("Invalid appointment ID"));
        } catch (SQLException e) {
            ctx.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .json(new ErrorResponse("Failed to fetch medical record: " + e.getMessage()));
        }
    }

    public void createMedicalRecord(Context ctx) {
        try {
            MedicalRecord medicalRecord = ctx.bodyAsClass(MedicalRecord.class);
            MedicalRecord createdRecord = medicalRecordService.createMedicalRecord(medicalRecord);

            appointmentService.completeAppointment(medicalRecord.getAppointmentId());

            ctx.status(HttpStatus.CREATED).json(createdRecord);
        } catch (IllegalArgumentException e) {
            ctx.status(HttpStatus.BAD_REQUEST)
                    .json(new ErrorResponse(e.getMessage()));
        } catch (SQLException e) {
            ctx.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .json(new ErrorResponse("Failed to create medical record: " + e.getMessage()));
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

package com.clinic.model;

import java.time.LocalDateTime;

public class MedicalRecord {
    private Integer id;
    private Integer appointmentId;
    private String symptoms;
    private String diagnosis;
    private String treatment; // Doctor's orders and medical prescription as text
    private String notes;
    private LocalDateTime createdAt;

    // Constructors
    public MedicalRecord() {}

    public MedicalRecord(Integer id, Integer appointmentId, String symptoms, String diagnosis,
                         String treatment, String notes, LocalDateTime createdAt) {
        this.id = id;
        this.appointmentId = appointmentId;
        this.symptoms = symptoms;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.notes = notes;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getAppointmentId() { return appointmentId; }
    public void setAppointmentId(Integer appointmentId) { this.appointmentId = appointmentId; }

    public String getSymptoms() { return symptoms; }
    public void setSymptoms(String symptoms) { this.symptoms = symptoms; }

    public String getDiagnosis() { return diagnosis; }
    public void setDiagnosis(String diagnosis) { this.diagnosis = diagnosis; }

    public String getTreatment() { return treatment; }
    public void setTreatment(String treatment) { this.treatment = treatment; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}

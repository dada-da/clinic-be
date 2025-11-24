package com.clinic.model;

import java.time.LocalDateTime;

public class Doctor {
    private Integer id;
    private String firstName;
    private String lastName;
    private String specialty;
    private String phone;
    private String email;
    private LocalDateTime createdAt;

    // Constructors
    public Doctor() {}

    public Doctor(Integer id, String firstName, String lastName, String specialty,
                  String phone, String email, LocalDateTime createdAt) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialty = specialty;
        this.phone = phone;
        this.email = email;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getSpecialty() { return specialty; }
    public void setSpecialty(String specialty) { this.specialty = specialty; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public String getFullName() {
        return firstName + " " + lastName;
    }
}

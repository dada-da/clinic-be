package com.clinic.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Patient {
    private Integer id;
    private String socialId;  // NEW: National ID/SSN
    private String fullName;
    private LocalDate dob;
    private String gender;
    private String address;
    private String phone;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Patient() {}

    public Patient(Integer id, String socialId, String fullName, LocalDate dob, String gender,
                   String address, String phone, String email, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.socialId = socialId;
        this.fullName = fullName;
        this.dob = dob;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getSocialId() { return socialId; }
    public void setSocialId(String socialId) { this.socialId = socialId; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public LocalDate getDob() { return dob; }
    public void setDob(LocalDate dob) { this.dob = dob; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}

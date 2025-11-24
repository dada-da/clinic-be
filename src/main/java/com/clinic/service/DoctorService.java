package com.clinic.service;

import com.clinic.model.Doctor;
import com.clinic.repository.DoctorRepository;

import java.sql.SQLException;
import java.util.List;

public class DoctorService {
    private final DoctorRepository doctorRepository;

    public DoctorService() {
        this.doctorRepository = new DoctorRepository();
    }

    public List<Doctor> getAllDoctors() throws SQLException {
        return doctorRepository.findAll();
    }

    public Doctor getDoctorById(Integer id) throws SQLException {
        return doctorRepository.findById(id);
    }

    public List<Doctor> getDoctorsBySpecialty(String specialty) throws SQLException {
        return doctorRepository.findBySpecialty(specialty);
    }
}

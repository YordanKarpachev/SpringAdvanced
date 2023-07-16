package com.example.Caching.service;

import com.example.Caching.model.StudentDTO;

import java.util.List;
import java.util.Optional;

public interface StudentServiceItf {

    List<StudentDTO> getAllStudents();

    Optional<StudentDTO> getStudentByName(String name);

    void refreshStudents();
}

package com.example.Caching.service;

import com.example.Caching.model.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import repository.StudentRepository;

import java.util.List;
import java.util.Optional;


@Service
public class StudentService implements StudentServiceItf {

    private final StudentRepository studentRepository;
    @Cacheable("students")
    @Override

    public List<StudentDTO> getAllStudents() {
        return this.studentRepository.findAllStudent();
    }

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Optional<StudentDTO> getStudentByName(String name) {
        return this.studentRepository.findAllStudent()
                .stream()
                .filter(a -> a.getName().equals(name))
                .findAny();
    }

    @CacheEvict(cacheNames = "students", allEntries = true)
    @Override
    public void refreshStudents() {

    }
}

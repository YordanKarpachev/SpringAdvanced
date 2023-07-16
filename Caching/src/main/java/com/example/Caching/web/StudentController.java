package com.example.Caching.web;

import com.example.Caching.model.StudentDTO;
import com.example.Caching.service.StudentServiceItf;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {

    private StudentServiceItf studentServiceItf;

    public StudentController(StudentServiceItf studentServiceItf) {
        this.studentServiceItf = studentServiceItf;
    }


    @GetMapping("/students/all")
    public ResponseEntity<List<StudentDTO>> findAll() {
        List<StudentDTO> studentDTOS = studentServiceItf.getAllStudents();

        studentDTOS.forEach(System.out::println);
        return ResponseEntity.ok(studentDTOS);
    }


    @GetMapping("/students/all/evict")
    public ResponseEntity<List<StudentDTO>> findAllAndEvict() {
        List<StudentDTO> studentDTOS = studentServiceItf.getAllStudents();
        studentServiceItf.refreshStudents();
        return ResponseEntity.ok(studentDTOS);
    }


    @GetMapping("/students/find")
    @Cacheable("students")
    public ResponseEntity<StudentDTO> findStudentByName(@RequestParam("q") String q) {

        Optional<StudentDTO> optionalStudentDTO = studentServiceItf.getStudentByName(q);

        return optionalStudentDTO
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

    }
}

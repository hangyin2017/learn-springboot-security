package com.example.demo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {

    private static final List<Student> STUDENTS = Arrays.asList(
            new Student(1, "James"),
            new Student(2, "Leo"),
            new Student(3, "Tom")
    );

    @GetMapping(path = "/{studentId}")
    //@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_STUDENT')")
    public Student getStudent(@PathVariable("studentId") Integer studentId) {
        return STUDENTS.stream()
                .filter(student -> studentId.equals(student.getStudentId()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(("Student" + studentId + "does not exist.")));
    }

    @PostMapping
    public void addStudent(@RequestBody Student student) {
        System.out.println("Add new student.");
        System.out.println(student);
    }

    @PutMapping(path = "/{studentId}")
    public void updateStudent(@PathVariable("sutdentId") Integer studentId, @RequestBody Student student) {
        System.out.println("Update student.");
        System.out.println(String.format("%s, %s", studentId, student));
    }

    @DeleteMapping(path = "/{studentId}")
    public void updateStudent(@PathVariable("sutdentId") Integer studentId) {
        System.out.println("Delete student.");
        System.out.println(studentId);
    }
}

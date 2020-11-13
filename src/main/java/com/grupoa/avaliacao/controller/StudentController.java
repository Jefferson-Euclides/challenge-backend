package com.grupoa.avaliacao.controller;

import com.grupoa.avaliacao.model.Student;
import com.grupoa.avaliacao.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<Student> listStudents() {
        return studentService.findAllStudents();
    }

    @GetMapping("/{academicRegister}")
    public Student findStudentById(@PathVariable Long academicRegister) {
        return studentService.findStudent(academicRegister);
    }

    @DeleteMapping("/{academicRegister}")
    public void removeStudentById(@PathVariable Long academicRegister) {
        studentService.removeStudent(academicRegister);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Student saveNewStudent(@Valid @RequestBody Student student) {
        return studentService.save(student);
    }

    @PutMapping("/{academicRegister}")
    public Student updateStudent(@PathVariable Long academicRegister,
                                 @Valid @RequestBody Student student) {
        return studentService.updateStudent(academicRegister, student);
    }

}

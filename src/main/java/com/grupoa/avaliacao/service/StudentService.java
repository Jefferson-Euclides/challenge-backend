package com.grupoa.avaliacao.service;

import com.grupoa.avaliacao.exceptions.StudentNotFoundException;
import com.grupoa.avaliacao.model.Student;
import com.grupoa.avaliacao.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    public Student findStudent(Long academicRegister) {
        return studentRepository.findById(academicRegister)
                .orElseThrow(() -> new StudentNotFoundException(academicRegister));
    }

    public void removeStudent(Long academicRegister) {
        if (studentRepository.existsById(academicRegister)) {
            studentRepository.deleteById(academicRegister);
        } else {
            throw new StudentNotFoundException(academicRegister);
        }
    }

    public Student save(Student student) {
        return studentRepository.save(student);
    }

    public Student updateStudent(Long academicRegister, Student student) {
        if (studentRepository.existsById(academicRegister)) {
            student.setAcademicRegister(academicRegister);

            return save(student);
        } else {
            throw new StudentNotFoundException(academicRegister);
        }
    }
}

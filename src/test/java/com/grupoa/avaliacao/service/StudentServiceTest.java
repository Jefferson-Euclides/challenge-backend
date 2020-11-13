package com.grupoa.avaliacao.service;

import com.grupoa.avaliacao.exceptions.StudentNotFoundException;
import com.grupoa.avaliacao.model.Student;
import com.grupoa.avaliacao.repository.StudentRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@AutoConfigureMockMvc
public class StudentServiceTest extends BaseTest{

    @Mock
    StudentRepository studentRepository;

    @InjectMocks
    StudentService studentService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findAllStudentsShouldReturnAllStudents() {
        Student studentOne = generateRandomStudent();
        Student studentTwo = generateRandomStudent();
        Student studentThree = generateRandomStudent();

        List<Student> listMocks = Arrays.asList(studentOne, studentTwo, studentThree);

        when(studentRepository.findAll()).thenReturn(listMocks);

        List<Student> listStudents = studentService.findAllStudents();

        assertEquals(3, listStudents.size());
        verify(studentRepository, times(1)).findAll();
    }

    @Test
    public void findAllStudentsEmptyListShoudlReturnEmptyList() {
        List<Student> listMocks = Collections.emptyList();

        when(studentRepository.findAll()).thenReturn(listMocks);

        List<Student> listStudents = studentService.findAllStudents();

        assertEquals(0, listStudents.size());
        verify(studentRepository, times(1)).findAll();
    }

    @Test
    public void findStudentShouldReturnOneStudent() {
        Optional<Student> studentMock = Optional.of(generateRandomStudent());
        Long randomLong = getRandomLong();

        Mockito.<Optional<Student>> when(studentRepository.findById(randomLong)).thenReturn(studentMock);

        Student student = studentService.findStudent(randomLong);

        assertEquals(studentMock.get().getName(), student.getName());
    }

    @Test(expected = StudentNotFoundException.class)
    public void findInvalidStudentShouldReturnStudentNotFoundException() {
        Long randomLong = getRandomLong();

        Student student = studentService.findStudent(randomLong);
    }

    @Test
    public void saveStudentShouldNoException() {
        Student studentMock = generateRandomStudent();

        when(studentService.save(studentMock)).thenReturn(studentMock);

        Student newStudent = studentService.save(studentMock);

        assertEquals(studentMock.getName(), newStudent.getName());
    }

    @Test
    public void updateStudentShouldNoException() {
        Student studentMock = generateRandomStudent();
        Long randomLong = getRandomLong();

        when(studentRepository.existsById(randomLong)).thenReturn(true);
        when(studentRepository.save(studentMock)).thenReturn(studentMock);

        Student updatedStudent = studentService.updateStudent(randomLong, studentMock);

        assertEquals(studentMock.getName(), updatedStudent.getName());
        verify(studentRepository, times(1)).existsById(randomLong);
        verify(studentRepository, times(1)).save(studentMock);
    }

    @Test(expected = StudentNotFoundException.class)
    public void updateInvalidStudentShouldStudentNotFoundException() {
        Student studentMock = generateRandomStudent();
        Long randomLong = getRandomLong();

        studentService.updateStudent(randomLong, studentMock);
    }

    @Test
    public void removeStudentShouldNoException() {
        Long randomLong = getRandomLong();

        when(studentRepository.existsById(randomLong)).thenReturn(true);

        studentService.removeStudent(randomLong);

        verify(studentRepository, times(1)).deleteById(randomLong);
    }

    @Test(expected = StudentNotFoundException.class)
    public void removeInvalidStudentShouldStudentNotFoundException() {
        Long randomLong = getRandomLong();

        studentService.removeStudent(randomLong);
    }

}

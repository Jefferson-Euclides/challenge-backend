package com.grupoa.avaliacao.controller;

import com.grupoa.avaliacao.model.Student;
import com.grupoa.avaliacao.service.BaseTest;
import com.grupoa.avaliacao.service.StudentService;
import com.grupoa.avaliacao.util.Utils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class StudentControllerTest extends BaseTest {

    @MockBean
    StudentService studentService;

    @Autowired
    private MockMvc mvc;

    @Test
    public void getAllStudentsShouldReturnAllStudents() throws Exception {

        List<Student> allStudents = Arrays.asList(generateRandomStudent());

        given(studentService.findAllStudents()).willReturn(allStudents);
        mvc.perform(get("/api/students")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is(allStudents.get(0).getName())));
    }

    @Test
    public void insertStudentShouldReturnCreatedStudent() throws Exception {
        Student student = generateRandomStudent();

        mvc.perform(post("/api/students")
                .content(Utils.asJsonString(student))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

    }

    @Test
    public void updateStudentShouldReturnUpdatedStudent() throws Exception {
        Student student = generateRandomStudent();

        mvc.perform(put("/api/students/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Utils.asJsonString(student)))
                .andExpect(status().isOk());

    }

}

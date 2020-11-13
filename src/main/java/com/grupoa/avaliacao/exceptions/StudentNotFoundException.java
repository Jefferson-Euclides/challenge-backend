package com.grupoa.avaliacao.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such student")
public class StudentNotFoundException extends RuntimeException{

    public StudentNotFoundException(Long academicRegister) {
        super("Estudante com o registro acadêmico: " + academicRegister + " não encontrado");
    }
}

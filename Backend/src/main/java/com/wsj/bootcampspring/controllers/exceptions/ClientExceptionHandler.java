package com.wsj.bootcampspring.controllers.exceptions;

import com.wsj.bootcampspring.services.exceptions.ClientNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



@ControllerAdvice
public class ClientExceptionHandler {

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<StandardError> notFoundException(ClientNotFoundException client, HttpServletRequest request) {
        StandardError st = new StandardError();
        st.setType("client_not_found");
        st.setMessage(client.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(st);
    }
}
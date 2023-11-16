package com.codergm.kafkademo.exception;

import com.codergm.kafkademo.model.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class KafkaExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage handleError(Exception e){
        System.out.println("stackTrace:");
        List<String> details = Arrays.asList(e.getStackTrace())
                .stream()
                .filter(st -> st.toString().contains("com.codergm.kafkademo"))
                .map(st -> st.toString())
                .collect(Collectors.toList());
        return ErrorMessage
                .builder()
                .error(e.getMessage())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .details(details)
                .build();
    }
}

package com.codergm.kafkademo.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ErrorMessage {

    private String error;
    private int status;
    private List<String> details;
}

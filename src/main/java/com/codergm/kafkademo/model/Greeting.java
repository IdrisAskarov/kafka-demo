package com.codergm.kafkademo.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Greeting {
    private String msg;
    private String name;
}

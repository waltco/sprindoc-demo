package com.example.waltco.springdocsdemo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Pokemon {

    private Long id;
    private String name;
    private String type;

}
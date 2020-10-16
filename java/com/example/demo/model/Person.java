package com.example.demo.model;


import java.util.List;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person
{

    @NotBlank    
    int personId;
    String firstName;
    String lastName;
    
    List<Book> books;
    
}

package com.example.demo.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book
{

    int bookId;
    @NotBlank
    String name;
    String author;
    String publisher;
    
    @NotNull
    @Size(min = 1, max = 20)
    int price;
    
}

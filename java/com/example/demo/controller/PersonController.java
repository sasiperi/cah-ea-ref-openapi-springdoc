package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.NoDataFoundException;
import com.example.demo.model.Book;
import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import com.fasterxml.jackson.databind.exc.InvalidTypeIdException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
//@SecurityRequirement(name = "sasi-sec") // this is not needed, due to the global policy. Need to be ebabled onky for exceptions.
public class PersonController
{
    @Autowired
    PersonService personService;
    
    
    @GetMapping(value = "/person/{personId}", produces = {MediaType.APPLICATION_JSON_VALUE})    
     public Person getPersonByPersonId(@PathVariable("personId")  int personId) throws NoDataFoundException
     {
         
         return personService.getPersonByPersonId(personId);         
         
     }
    
    /*
     * YOU DONT PASS PersoNID *IDEALLY* IN POST, as it wll be xreated. 
     * I am adding it as path-var, JUST for demo purposes, so I can throw demo exception when it's 5
     */
    @PostMapping(value = "/person/{personId}", produces = {MediaType.APPLICATION_JSON_VALUE})    
    public int createPersonByPersonId(@RequestBody Person person, @PathVariable("personId")  int personId)
    {
        
        return personService.createPerson(person);         
        
    }
    
    @Operation(summary = "Gets a random book, independent of the book if id=1 passed, no books if id=0 passed")
    @ApiResponses(value = { 
            @ApiResponse(responseCode = "200", description = "Found the book", 
              content = { @Content(mediaType = "application/json", 
                schema = @Schema(implementation = Book.class)) })})
    @GetMapping(value = "/doBook/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})    
    public Book purpleCow(@Parameter(description = "takes value 0 or 1, to send random book or none")@PathVariable("id")  int id) throws InvalidTypeIdException
    {
        Book book;
        switch (id)
        {
            case 1 ->  book = new Book (5, "Design Patterns", "Sasi Peri", "Orielly", 50 );
            case 0 ->  book = new Book();
            default -> throw new InvalidTypeIdException(null, "No data was found mathcing the requested criteria == " + id, null, "Book");
        }         
        
        return book;
    }
    

}

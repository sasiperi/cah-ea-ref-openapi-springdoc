package com.example.demo.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.exception.NoDataFoundException;
import com.example.demo.model.Book;
import com.example.demo.model.Person;

@Service
public class PersonService
{

    public Person getPersonByPersonId(int personId) throws NoDataFoundException
    {

        Person person;
        List<Book> books = Arrays.asList(new Book[] { new Book(), new Book() });

        switch (personId)
        {
            case 1 -> person = new Person(1, "fName1", "lname1", books);
            case 2 -> person = new Person(2, "fName2", "lname2", books);
            default -> throw new NoDataFoundException("No data was found mathcing the requested criteria == " + personId);
        }

        return person;

    }

    public int createPerson(Person person)
    {
        if (person.getPersonId() == 5)
            throw new IllegalArgumentException();

        return 0;
    }

}

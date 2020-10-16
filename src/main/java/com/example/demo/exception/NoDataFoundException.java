package com.example.demo.exception;

public class NoDataFoundException extends Exception
{
    /**
     *  whatever...
     */
    private static final long serialVersionUID = 1L;

    public NoDataFoundException(String msg)
    {
        super(msg);
    }
}

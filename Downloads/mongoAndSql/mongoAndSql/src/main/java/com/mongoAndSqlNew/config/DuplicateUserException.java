package com.mongoAndSqlNew.config;

public class DuplicateUserException extends RuntimeException{
    public DuplicateUserException (String message)
    {
        super(message);
    }
}

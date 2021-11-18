package com.project;

public class InvalidLoginException extends Exception {
    public InvalidLoginException() {
        super("Invalid login");
    }
}

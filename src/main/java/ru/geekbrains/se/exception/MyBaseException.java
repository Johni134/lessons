package ru.geekbrains.se.exception;

public class MyBaseException extends Exception {
    MyBaseException(String message) {
        super(message);
    }

    public String getCustomErrorInfo() {
        return super.getMessage();
    }
}

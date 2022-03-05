package ru.kotikov.appliances.exceptions;

public class ApplianceAlreadyExistException extends Exception {
    public ApplianceAlreadyExistException(String message) {
        super(message);
    }
}

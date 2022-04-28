package ru.kotikov.appliances.exceptions;

public class ApplianceNotFoundException extends Exception {
    public ApplianceNotFoundException(String message) {
        super(message);
    }
}
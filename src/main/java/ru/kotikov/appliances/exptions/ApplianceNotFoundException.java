package ru.kotikov.appliances.exptions;

public class ApplianceNotFoundException extends Exception{
    public ApplianceNotFoundException(String message) {
        super(message);
    }
}

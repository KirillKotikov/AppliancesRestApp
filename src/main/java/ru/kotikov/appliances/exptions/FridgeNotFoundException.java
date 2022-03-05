package ru.kotikov.appliances.exptions;

public class FridgeNotFoundException extends Exception{
    public FridgeNotFoundException(String message) {
        super(message);
    }
}

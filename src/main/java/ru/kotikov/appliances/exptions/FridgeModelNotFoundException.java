package ru.kotikov.appliances.exptions;

public class FridgeModelNotFoundException extends Exception{
    public FridgeModelNotFoundException(String message) {
        super(message);
    }
}

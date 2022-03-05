package ru.kotikov.appliances.exptions;

public class FridgeAlreadyExistException extends Exception{
    public FridgeAlreadyExistException(String message) {
        super(message);
    }
}

package ru.kotikov.appliances.exptions;

public class FridgeModelAlreadyExistException extends Exception{
    public FridgeModelAlreadyExistException(String message) {
        super(message);
    }
}

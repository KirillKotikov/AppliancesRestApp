package ru.kotikov.appliances.exptions;

public class HooverAlreadyExistException extends Exception{
    public HooverAlreadyExistException(String message) {
        super(message);
    }
}

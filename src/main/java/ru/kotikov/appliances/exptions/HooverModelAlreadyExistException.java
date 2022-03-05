package ru.kotikov.appliances.exptions;

public class HooverModelAlreadyExistException extends Exception{
    public HooverModelAlreadyExistException(String message) {
        super(message);
    }
}

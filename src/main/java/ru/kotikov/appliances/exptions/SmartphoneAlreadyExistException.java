package ru.kotikov.appliances.exptions;

public class SmartphoneAlreadyExistException extends Exception{
    public SmartphoneAlreadyExistException(String message) {
        super(message);
    }
}

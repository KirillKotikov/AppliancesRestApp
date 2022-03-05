package ru.kotikov.appliances.exptions;

public class SmartphoneModelAlreadyExistException extends Exception{
    public SmartphoneModelAlreadyExistException(String message) {
        super(message);
    }
}

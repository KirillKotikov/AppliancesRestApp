package ru.kotikov.appliances.exptions;

public class TelevisionAlreadyExistException extends Exception {
    public TelevisionAlreadyExistException(String message) {
        super(message);
    }
}

package ru.kotikov.appliances.exptions;

public class TelevisionModelAlreadyExistException extends Exception{
    public TelevisionModelAlreadyExistException(String message) {
        super(message);
    }
}

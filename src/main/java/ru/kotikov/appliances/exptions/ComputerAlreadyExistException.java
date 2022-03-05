package ru.kotikov.appliances.exptions;

public class ComputerAlreadyExistException extends Exception{
    public ComputerAlreadyExistException(String message) {
        super(message);
    }
}

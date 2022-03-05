package ru.kotikov.appliances.exptions;

public class ComputerModelAlreadyExistException extends Exception{
    public ComputerModelAlreadyExistException(String message) {
        super(message);
    }
}

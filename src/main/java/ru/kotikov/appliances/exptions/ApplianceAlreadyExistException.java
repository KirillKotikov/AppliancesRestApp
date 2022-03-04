package ru.kotikov.appliances.exptions;

public class ApplianceAlreadyExistException extends Exception {
        public ApplianceAlreadyExistException(String message) {
            super(message);
        }
    }


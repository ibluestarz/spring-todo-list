package fr.lernejo.todo;

public class BadCreateUserRequestException extends RuntimeException{
    public BadCreateUserRequestException(String message) {
        super(message);
    }
}

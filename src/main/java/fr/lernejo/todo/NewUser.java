package fr.lernejo.todo;

import jakarta.validation.constraints.Email;

public record NewUser(@Email String email, String password) {
}

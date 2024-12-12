package fr.lernejo.todo;

import java.time.Instant;
import java.util.UUID;

public record UserEntity(
    Long id,
    UUID uuid,
    Instant createdAt,
    String email,
    String password) {
}

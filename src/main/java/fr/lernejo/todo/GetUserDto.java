package fr.lernejo.todo;

import java.time.Instant;
import java.util.UUID;

public record GetUserDto(String email, Instant created_at, UUID uuid) {
}

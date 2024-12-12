package fr.lernejo.todo;

import java.time.Instant;
import java.util.UUID;

public class UserDto {
    private String email;
    private String password;
    private Instant createdAt;
    private UUID id;

    public UserDto() {
    }

    public UserDto(String email, String password, Instant createdAt, UUID id) {
        this.email = email;
        this.password = password;
        this.createdAt = createdAt;
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}

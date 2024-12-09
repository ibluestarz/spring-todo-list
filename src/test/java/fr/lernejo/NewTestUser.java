package fr.lernejo;

import fr.lernejo.todo.NewUser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NewTestUser {

    @Test
    void validNewUser() {
        NewUser newUser = new NewUser("test@test.com", "0123456789");
        assertNotNull(newUser);
        assertEquals("test@test.com", newUser.email());
        assertEquals("0123456789", newUser.password());
    }

    @Test
    void invalidNewUser_blankPassword() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new NewUser("test@test.com", " "));
        assertEquals("Password cannot be null or empty", exception.getMessage());
    }

    @Test
    void invalidNewUser_blankEmail() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new NewUser(" ", "0123456789"));
        assertEquals("Email cannot be null or empty", exception.getMessage());
    }

    @Test
    void invalidNewUser_nullPassword() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new NewUser("test@test.com", null));
        assertEquals("Password cannot be null or empty", exception.getMessage());
    }

    @Test
    void invalidNewUser_nullEmail() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new NewUser(null, "0123456789"));
        assertEquals("Email cannot be null or empty", exception.getMessage());
    }
}

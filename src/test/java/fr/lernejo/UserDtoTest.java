package fr.lernejo;

import fr.lernejo.todo.UserDto;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UserDtoTest {

    @Test
    void validUserDto() {
        OffsetDateTime now = OffsetDateTime.now();
        UUID id = UUID.randomUUID();
        UserDto userDto = new UserDto("test@test.com", "0123456789", now.toInstant(), id);
        assertNotNull(userDto);
        assertEquals("test@test.com", userDto.getEmail());
        assertEquals("0123456789", userDto.getPassword());
        assertEquals(id, userDto.getId());
    }

    @Test
    void invalidUserDto_nullEmail() {
        OffsetDateTime now = OffsetDateTime.now();
        UUID id = UUID.randomUUID();
        UserDto userDto = new UserDto(null, "0123456789", now.toInstant(), id);
        assertNull(userDto.getEmail());
        assertEquals("0123456789", userDto.getPassword());
        assertEquals(id, userDto.getId());
    }

    @Test
    void invalidUserDto_nullPassword() {
        OffsetDateTime now = OffsetDateTime.now();
        UUID id = UUID.randomUUID();
        UserDto userDto = new UserDto("test@test.com", null, now.toInstant(), id);
        assertEquals("test@test.com", userDto.getEmail());
        assertNull(userDto.getPassword());
        assertEquals(id, userDto.getId());
    }

    @Test
    void invalidUserDto_nullCreatedAt() {
        UUID id = UUID.randomUUID();
        UserDto userDto = new UserDto("test@test.com", "0123456789", null, id);
        assertEquals("test@test.com", userDto.getEmail());
        assertEquals("0123456789", userDto.getPassword());
        assertNull(userDto.getCreatedAt());
        assertEquals(id, userDto.getId());
    }

    @Test
    void invalidUserDto_nullUuid() {
        OffsetDateTime now = OffsetDateTime.now();
        UserDto userDto = new UserDto("test@test.com", "0123456789", now.toInstant(), null);
        assertEquals("test@test.com", userDto.getEmail());
        assertEquals("0123456789", userDto.getPassword());
        assertNull(userDto.getId());
    }
}

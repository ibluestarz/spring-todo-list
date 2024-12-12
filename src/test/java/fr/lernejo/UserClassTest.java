package fr.lernejo;

import fr.lernejo.todo.UserEntity;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UserClassTest {

    @Test
    void validUserEntity() {
        Instant now = OffsetDateTime.now().toInstant();
        UUID uuid = UUID.randomUUID();
        UserEntity userEntity = new UserEntity(1L, uuid, now, "test@test.com", "0123456789");
        assertNotNull(userEntity);
        assertEquals("test@test.com", userEntity.email());
        assertEquals("0123456789", userEntity.password());
        assertEquals(now, userEntity.createdAt());
        assertEquals(uuid, userEntity.uuid());
    }
}

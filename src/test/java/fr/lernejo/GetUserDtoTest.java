package fr.lernejo;

import fr.lernejo.todo.GetUserDto;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class GetUserDtoTest {

    @Test
    void validUserGetUSerDto() {
        OffsetDateTime now = OffsetDateTime.now();
        UUID id = UUID.randomUUID();
        GetUserDto userSummaryDto = new GetUserDto("test@test.com", now.toInstant(), id);
        assertNotNull(userSummaryDto);
        assertEquals(id, userSummaryDto.uuid());
        assertEquals("test@test.com", userSummaryDto.email());
        assertEquals(now.toInstant(), userSummaryDto.created_at());
    }

    @Test
    void invalidUserGetUserDto_nullUuid() {
        OffsetDateTime now = OffsetDateTime.now();
        GetUserDto userSummaryDto = new GetUserDto("test@test.com", now.toInstant(), null);
        assertNull(userSummaryDto.uuid());
        assertEquals("test@test.com", userSummaryDto.email());
        assertEquals(now.toInstant(), userSummaryDto.created_at());
    }

    @Test
    void invalidUserGetUserDto_nullEmail() {
        OffsetDateTime now = OffsetDateTime.now();
        UUID id = UUID.randomUUID();
        GetUserDto userSummaryDto = new GetUserDto(null, now.toInstant(), id);;
        assertEquals(id, userSummaryDto.uuid());
        assertNull(userSummaryDto.email());
        assertEquals(now.toInstant(), userSummaryDto.created_at());
    }

    @Test
    void invalidUserGetUserDto_nullCreatedAt() {
        UUID id = UUID.randomUUID();
        GetUserDto userSummaryDto = new GetUserDto("test@test.com", null, id);
        assertEquals(id, userSummaryDto.uuid());
        assertEquals("test@test.com", userSummaryDto.email());
        assertNull(userSummaryDto.created_at());
    }
}

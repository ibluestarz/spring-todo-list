package fr.lernejo;

import fr.lernejo.todo.UserEntity;
import fr.lernejo.todo.UserRepository;
import fr.lernejo.todo.UserRowMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserRepoTest {

    private NamedParameterJdbcTemplate jdbcTemplate;
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        jdbcTemplate = Mockito.mock(NamedParameterJdbcTemplate.class);
        userRepository = new UserRepository(jdbcTemplate);
    }

    @Test
    void findByEmail_success() {
        UserEntity userEntity = new UserEntity(1L, UUID.randomUUID(), OffsetDateTime.now().toInstant(), "test@test.com", "0123456789");

        when(jdbcTemplate.queryForObject(anyString(), any(Map.class), any(UserRowMapper.class))).thenReturn(userEntity);

        UserEntity result = userRepository.findByEmail("test@test.com");

        assertNotNull(result);
        assertEquals("test@test.com", result.email());
    }

    @Test
    void findByEmail_userNotFound() {
        when(jdbcTemplate.queryForObject(anyString(), any(Map.class), any(UserRowMapper.class))).thenThrow(EmptyResultDataAccessException.class);

        UserEntity result = userRepository.findByEmail("test@test.com");

        assertNull(result);
    }
}

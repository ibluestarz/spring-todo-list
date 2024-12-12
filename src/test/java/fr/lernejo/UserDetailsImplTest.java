package fr.lernejo;

import fr.lernejo.todo.UserDetailsImpl;
import fr.lernejo.todo.UserEntity;
import fr.lernejo.todo.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.time.OffsetDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class UserDetailsImplTest {

    private UserRepository userRepository;
    private UserDetailsImpl userDetailsService;

    @BeforeEach
    void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        userDetailsService = new UserDetailsImpl(userRepository);
    }

    @Test
    void loadUserByUsername_success() {
        UserEntity userEntity = new UserEntity(1L, UUID.randomUUID(), OffsetDateTime.now().toInstant(), "test@test.com", "0123456789");

        when(userRepository.findByEmail(anyString())).thenReturn(userEntity);

        UserDetails userDetails = userDetailsService.loadUserByUsername("test@test.com");

        assertNotNull(userDetails);
        assertEquals("test@test.com", userDetails.getUsername());
        assertEquals("0123456789", userDetails.getPassword());
    }

    @Test
    void loadUserByUsername_userNotFound() {
        when(userRepository.findByEmail(anyString())).thenReturn(null);

        assertThrows(UsernameNotFoundException.class, () -> userDetailsService.loadUserByUsername("unknown@example.com"));
    }
}

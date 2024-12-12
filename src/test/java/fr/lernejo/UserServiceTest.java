package fr.lernejo;

import fr.lernejo.todo.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.OffsetDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceTest {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        passwordEncoder = Mockito.mock(PasswordEncoder.class);
        userService = new UserService(userRepository, passwordEncoder);
    }

    @Test
    void createUser() throws Exception {
        NewUserDto newUserDto = new NewUserDto("test@test.com", "0123456789");
        UserEntity userEntity = new UserEntity(1L, UUID.randomUUID(), OffsetDateTime.now().toInstant(), "test@test.com", "0123456789");

        when(passwordEncoder.encode(any(String.class))).thenReturn("encodedPassword");
        when(userRepository.create(any(NewUser.class))).thenReturn(userEntity);

        UserDto userDto = userService.createUser(newUserDto);

        assertNotNull(userDto);
        assertEquals("test@test.com", userDto.getEmail());
        assertEquals("0123456789", userDto.getPassword());
    }


    @Test
    void createUser_throwsBadCreateUserRequest() {
        NewUserDto newUserDto = new NewUserDto("test@test.com", "short");

        assertThrows(BadCreateUserRequestException.class, () -> userService.createUser(newUserDto));
    }

    @Test
    void createUser_throwsIllegalArgumentException() {
        NewUserDto newUserDto = new NewUserDto("test@test.com", null);

        assertThrows(NullPointerException.class, () -> userService.createUser(newUserDto));
    }

    @Test
    void getUserByEmail() {
        UserEntity userEntity = new UserEntity(1L, UUID.randomUUID(), OffsetDateTime.now().toInstant(), "test@test.com", "0123456789");

        when(userRepository.findByEmail(any(String.class))).thenReturn(userEntity);

        GetUserDto userSummaryDto = userService.getUserByEmail("test@test.com");

        assertNotNull(userSummaryDto);
        assertEquals("test@test.com", userSummaryDto.email());
    }
}

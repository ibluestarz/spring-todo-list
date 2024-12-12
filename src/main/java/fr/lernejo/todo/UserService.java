package fr.lernejo.todo;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //service logique metier, controler c'est l'api et le repo contacte la bdd, changer le dto permet de ne
    // pas changer le controller mais seulement les dto


    public UserDto createUser(NewUserDto newUserDto) throws Exception {
        if (newUserDto.getPassword().length() < 10) {
            throw new BadCreateUserRequestException("Password must be at least 10 characters long");
        }
        NewUser newUser = new NewUser(newUserDto.getEmail(), passwordEncoder.encode(newUserDto.getPassword()));
        try {
            UserEntity createdUser = userRepository.create(newUser);
            return new UserDto(
                createdUser.email(),
                createdUser.password(),
                Instant.now(),
                UUID.randomUUID()
            );
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (BadCreateUserRequestException e) {
            throw new BadCreateUserRequestException(e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public GetUserDto getUserByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);
        return new GetUserDto(
            userEntity.email(),
            userEntity.createdAt(),
            userEntity.uuid()
        );
    }
}

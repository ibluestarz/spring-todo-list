package fr.lernejo.todo;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

@Service
public class UserDetailsImpl implements UserDetailsService {

    private final UserRepository userRepository;

    private final Map<String, User> usersMap = Map.of(
        "mbappe", new User("mbappe", hash("mbappepwd"), Set.of()),
        "barcola", new User("barcola", hash("barcolapwd"), Set.of())
    );

    public UserDetailsImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private String hash(String password) {
        return new SecurityConfiguration().passwordEncoder().encode(password);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(user.email(), user.password(), Set.of());
    }
}

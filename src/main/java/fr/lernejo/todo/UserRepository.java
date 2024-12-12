package fr.lernejo.todo;


import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class UserRepository {

    private final NamedParameterJdbcTemplate template;
    private final UserRowMapper mapper = new UserRowMapper();

    public UserRepository(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    public UserEntity findByEmail(String email) {
        try {
            return template.queryForObject(
                """
                        SELECT *
                        FROM "user"
                        WHERE email = :email
                        """,
                Map.of("email", email),
                mapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public UserEntity create(NewUser user) throws Exception {
        try {
            if (findByEmail(user.email()) != null) {
                throw new IllegalArgumentException("User already exists");
            }
            return template.queryForObject(
                """
                    INSERT INTO "user"
                        (email, encoded_password)
                    VALUES
                        (:email, :encoded_password)
                    ON CONFLICT (email) DO NOTHING
                    RETURNING *
                    """,
                Map.of("email", user.email(), "encoded_password", user.password()),
                mapper
            );
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
 }

package fr.lernejo.todo;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class UserRowMapper implements RowMapper<UserEntity> {
    @Override
    public UserEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new UserEntity(
            rs.getLong("id"),
            UUID.fromString(rs.getString("uuid")),
            rs.getTimestamp("created_at").toInstant(),
            rs.getString("email"),
            rs.getString("encoded_password")
        );
    }

}

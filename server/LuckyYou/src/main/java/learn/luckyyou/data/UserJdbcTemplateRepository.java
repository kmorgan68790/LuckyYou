package learn.luckyyou.data;

import learn.luckyyou.models.Users;
import org.apache.tomcat.jni.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;
@Repository
public class UserJdbcTemplateRepository implements UserRepository{
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final class UserRowMapper implements RowMapper<Users> {
        @Override
        public Users mapRow(ResultSet rs, int rowNum) throws SQLException {
            Users users = new Users();
            users.setUserId(rs.getInt("user_id"));
            users.setUserName(rs.getString("user_name"));
            users.setPassword(rs.getString("user_password"));
            users.setEmail(rs.getString("email"));
            users.setFirstName(rs.getString("first_name"));
            users.setMiddleName(rs.getString("middle_name"));
            users.setLastName(rs.getString("last_name"));
            users.setDob(rs.getDate("dob").toLocalDate());
            // Handle zodiac_id mapping if needed
            users.setZodiacId(rs.getInt("zodiac_id"));
            return users;
        }
    }

    @Override
    public List<Users> findAll() {
        String sql = "SELECT * FROM users;";
        return jdbcTemplate.query(sql, new UserRowMapper());
    }

    @Override
    public Users findById(int userId) {
        String sql = "SELECT * FROM users WHERE user_id = ?;";
        return jdbcTemplate.query(sql, new UserRowMapper(), userId).stream()
                .findFirst().orElse(null);
    }

    @Override
    public Users add(Users user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO users (user_name, user_password, email, first_name, middle_name, last_name, dob, " +
                "zodiac_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, user.getUserName());
                ps.setString(2, user.getPassword());
                ps.setString(3, user.getEmail());
                ps.setString(4, user.getFirstName());
                ps.setString(5, user.getMiddleName());
                ps.setString(6, user.getLastName());
                ps.setDate(7, java.sql.Date.valueOf(user.getDob()));
                ps.setInt(8, user.getZodiacId());
                return ps;
            }
        }, keyHolder);

        // Set the generated key on the user object
        user.setUserId(keyHolder.getKey().intValue());
        return user;
    }

    @Override
    public boolean update(Users users) {
        String sql = "UPDATE users SET user_name = ?, user_password = ?, email = ?, first_name = ?, middle_name = ?, " +
                "last_name = ? WHERE user_id = ?;";
        return jdbcTemplate.update(sql, users.getUserName(), users.getPassword(), users.getEmail(), users.getFirstName(),
                users.getMiddleName(), users.getLastName(), users.getUserId()) > 0;
    }

    @Override
    public boolean deleteById(int userId) {
        String sql = "DELETE FROM users WHERE user_id = ?;";
        return jdbcTemplate.update(sql, userId) > 0;
    }
}

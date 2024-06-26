package learn.luckyyou.data;

import learn.luckyyou.models.UserNumerologyMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserNumerologyMappingJdbcTemplateRepository implements UserNumerologyMappingRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserNumerologyMappingJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final class UserNumerologyMappingRowMapper implements RowMapper<UserNumerologyMapping> {
        @Override
        public UserNumerologyMapping mapRow(ResultSet rs, int rowNum) throws SQLException {
            UserNumerologyMapping userNumerologyMapping = new UserNumerologyMapping();
            userNumerologyMapping.setUserNumerologyMappingId(rs.getInt("user_numerology_mapping_id"));
            userNumerologyMapping.setUserId(rs.getInt("user_id"));
            userNumerologyMapping.setNumerologyType(rs.getString("numerology_type"));
            userNumerologyMapping.setNumerologyDescriptionId(rs.getInt("numerology_description_id"));
            return userNumerologyMapping;
        }
    }

    @Override
    public List<UserNumerologyMapping> findAll() {
        String sql = "SELECT * FROM user_numerology_mapping;";
        return jdbcTemplate.query(sql, new UserNumerologyMappingRowMapper());
    }

    @Override
    public UserNumerologyMapping findById(int userNumerologyMappingId) {
        String sql = "SELECT * FROM user_numerology_mapping WHERE user_numerology_mapping_id = ?;";
        return jdbcTemplate.query(sql, new UserNumerologyMappingRowMapper(), userNumerologyMappingId).stream()
                .findFirst().orElse(null);
    }

    @Override
    public List<UserNumerologyMapping> findByUserId(int userId) {
        String sql = "SELECT * FROM user_numerology_mapping WHERE user_id = ?;";
        return jdbcTemplate.query(sql, new UserNumerologyMappingRowMapper(), userId);
    }

    @Override
    public List<UserNumerologyMapping> findByNumerologyType(String numerologyType) {
        String sql = "SELECT * FROM user_numerology_mapping WHERE numerology_type = ?;";
        return jdbcTemplate.query(sql, new UserNumerologyMappingRowMapper(), numerologyType);
    }

    @Override
    public List<UserNumerologyMapping> findByNumerologyDescriptionId(int numerologyDescriptionId) {
        String sql = "SELECT * FROM user_numerology_mapping WHERE numerology_description_id = ?;";
        return jdbcTemplate.query(sql, new UserNumerologyMappingRowMapper(), numerologyDescriptionId);
    }

    @Override
    public UserNumerologyMapping findByUserIdAndNumerologyType(int userId, String numerologyType) {
        final String sql = "SELECT * FROM user_numerology_mapping WHERE user_id = ? AND numerology_type = ?;";
        return jdbcTemplate.queryForObject(sql, new UserNumerologyMappingRowMapper(), userId, numerologyType);
    }
    @Override
    public boolean saveNumerologyMapping(UserNumerologyMapping mapping) {
        final String sql = "INSERT INTO user_numerology_mapping (user_id, numerology_type, numerology_description_id) " +
                "VALUES (?, ?, ?)";
        int rowsAffected = jdbcTemplate.update(sql, mapping.getUserId(), mapping.getNumerologyType(),
                mapping.getNumerologyDescriptionId());
        return rowsAffected > 0;
    }

}


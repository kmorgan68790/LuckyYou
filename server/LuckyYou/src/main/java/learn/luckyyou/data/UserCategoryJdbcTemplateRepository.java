package learn.luckyyou.data;

import learn.luckyyou.models.UserCategory;
import learn.luckyyou.models.Users;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
@Repository
public class UserCategoryJdbcTemplateRepository implements UserCategoryRepository{
    private final JdbcTemplate jdbcTemplate;

    public UserCategoryJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final class UserCategoryRowMapper implements RowMapper<UserCategory> {
        @Override
        public UserCategory mapRow(ResultSet rs, int rowNum) throws SQLException {
            UserCategory userCategory = new UserCategory();
            userCategory.setUserCategoryId(rs.getInt("user_category_id"));
            userCategory.setCategoryId(rs.getInt("category_id"));
            userCategory.setUserId(rs.getInt("user_id"));
            userCategory.setNumerologyId(rs.getInt("numerology_id"));
            userCategory.setZodiacId(rs.getInt("zodiac_id"));
            userCategory.setConcordGroupId(rs.getInt("concord_group_id"));
            return userCategory;
        }
    }

    @Override
    public List<UserCategory> findAll() {
        String sql = "SELECT * FROM user_categories;";
        return jdbcTemplate.query(sql, new UserCategoryRowMapper());
    }

    @Override
    public UserCategory findById(int userCategoryId) {
        String sql = "SELECT * FROM user_categories WHERE user_category_id = ?;";
        return jdbcTemplate.queryForObject(sql, new UserCategoryRowMapper(), userCategoryId);
    }

    @Override
    public UserCategory findByCategoryId(int categoryId) {
        String sql = "SELECT * FROM user_categories WHERE category_id = ?;";
        return jdbcTemplate.queryForObject(sql, new UserCategoryRowMapper(), categoryId);
    }

    @Override
    public UserCategory findByUserId(int userId) {
        String sql = "SELECT * FROM user_categories WHERE user_id = ?;";
        return jdbcTemplate.queryForObject(sql, new UserCategoryRowMapper(), userId);
    }

    @Override
    public UserCategory findByNumerologyId(int numerologyId) {
        String sql = "SELECT * FROM user_categories WHERE numerology_id = ?;";
        return jdbcTemplate.queryForObject(sql, new UserCategoryRowMapper(), numerologyId);
    }

    @Override
    public UserCategory findByZodiacId(int zodiacId) {
        String sql = "SELECT * FROM user_categories WHERE category_id = ?;";
        return jdbcTemplate.queryForObject(sql, new UserCategoryRowMapper(), zodiacId);
    }

    @Override
    public UserCategory findByConcordGroupId(int concordGroupId) {
        String sql = "SELECT * FROM user_categories WHERE category_id = ?;";
        return jdbcTemplate.queryForObject(sql, new UserCategoryRowMapper(), concordGroupId);
    }


//    @Override
//    public UserCategory add(UserCategory userCategory) {
//        String sql = "INSERT INTO user_categories (category_id, user_id, numerology_id, zodiac_id, concord_group_id) " +
//                "VALUES (?, ?, ?, ?, ?)";
//        jdbcTemplate.update(sql, userCategory.getCategoryId(), userCategory.getUserId(),
//                userCategory.getNumerologyId(), userCategory.getZodiacId(),
//                userCategory.getConcordGroupId());
//
//        // Assuming user_category_id is auto-generated, retrieve the inserted entity
//        int newUserCategoryId = jdbcTemplate.queryForObject("SELECT lastval()", Integer.class);
//        userCategory.setUserCategoryId(newUserCategoryId);
//
//        return userCategory;
//    }
}

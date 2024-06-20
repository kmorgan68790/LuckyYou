package learn.luckyyou.data;

import learn.luckyyou.models.ConcordBirthday;
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
public class ConcordBirthdayJdbcTemplateRepository implements ConcordBirthdayRepository{
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ConcordBirthdayJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final class ConcordBirthdayRowMapper implements RowMapper<ConcordBirthday> {
        @Override
        public ConcordBirthday mapRow(ResultSet rs, int rowNum) throws SQLException {
            ConcordBirthday concordBirthday = new ConcordBirthday();
            concordBirthday.setConcordBirthdayNumberId(rs.getInt("concord_birthday_number_id"));
            concordBirthday.setConcordBirthdayNumber(rs.getInt("concord_birthday_number"));
            concordBirthday.setConcordGroupId(rs.getInt("concord_group_id"));
            return concordBirthday;
        }
    }

    @Override
    public ConcordBirthday findById(int concordBirthdayId) {
        String sql = "SELECT * FROM concord_birthday WHERE concord_birthday_number_id = ?;";
        return jdbcTemplate.query(sql, new ConcordBirthdayRowMapper(), concordBirthdayId).stream()
                .findFirst().orElse(null);
    }

    @Override
    public List<ConcordBirthday> findAll() {
        String sql = "SELECT * FROM concord_birthday;";
        return jdbcTemplate.query(sql, new ConcordBirthdayRowMapper());
    }

    @Override
    public List<ConcordBirthday> findByGroupId(int concordGroupId) {
        String sql = "SELECT * FROM concord_birthday WHERE concord_group_id = ?;";
        return jdbcTemplate.query(sql, new ConcordBirthdayRowMapper(), concordGroupId);
    }

    @Override
    public ConcordBirthday findConcordBirthdayNumber(int birthdayNumber) {
        final String sql = "SELECT * FROM concord_birthday WHERE concord_birthday_number = ?;";
        return jdbcTemplate.queryForObject(sql, new ConcordBirthdayRowMapper(), birthdayNumber);
    }
//    @Override
//    public ConcordBirthday add(ConcordBirthday concordBirthday) {
//        KeyHolder keyHolder = new GeneratedKeyHolder();
//        String sql = "INSERT INTO concord_birthday (concord_birthday_number, concord_group_id) " +
//                "VALUES (?, ?);";
//
//        jdbcTemplate.update(new PreparedStatementCreator() {
//            @Override
//            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
//                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//                ps.setInt(1, concordBirthday.getConcordBirthdayNumber());
//                ps.setInt(2, concordBirthday.getConcordGroupId());
//                return ps;
//            }
//        }, keyHolder);
//
//        // Set the generated key on the user object
//        concordBirthday.setConcordBirthdayNumberId(keyHolder.getKey().intValue());
//        return concordBirthday;

//    @Override
//    public boolean update(ConcordBirthday concordBirthday) {
//        String sql = "UPDATE concord_birthday SET concord_birthday_number = ?, concord_group_id = ? " +
//                "WHERE concord_birthday_number_id = ?;";
//        return jdbcTemplate.update(sql, concordBirthday.getConcordBirthdayNumber(), concordBirthday.getConcordGroupId(),
//                concordBirthday.getConcordBirthdayNumberId()) > 0;
//    }
}

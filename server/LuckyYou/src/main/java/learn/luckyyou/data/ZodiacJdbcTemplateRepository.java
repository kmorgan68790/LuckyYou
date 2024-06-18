package learn.luckyyou.data;

import learn.luckyyou.models.Zodiac;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class ZodiacJdbcTemplateRepository implements ZodiacRepository{
    private final JdbcTemplate jdbcTemplate;

    public ZodiacJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final class ZodiacRowMapper implements RowMapper<Zodiac> {
        @Override
        public Zodiac mapRow(ResultSet rs, int rowNum) throws SQLException {
            Zodiac zodiac = new Zodiac();
            zodiac.setZodiacId(rs.getInt("zodiac_id"));
            zodiac.setZodiacName(rs.getString("zodiac_name"));
            zodiac.setZodiacStart(rs.getDate("start_date").toLocalDate());
            zodiac.setZodiacEnd(rs.getDate("end_date").toLocalDate());
            zodiac.setZodiacDescription(rs.getString("zodiac_description"));
            return zodiac;
        }
    }

    @Override
    public List<Zodiac> findAll() {
        final String sql = "SELECT * FROM zodiac;";
        return jdbcTemplate.query(sql, new ZodiacRowMapper());
    }

    @Override
    public Zodiac findById(int zodiacId) {
        final String sql = "SELECT * FROM zodiac WHERE zodiac_id = ?;";
        return jdbcTemplate.queryForObject(sql, new ZodiacRowMapper(), zodiacId);
    }

    @Override
    public Zodiac findZodiacStartAndEnd(LocalDate zodiacDateStart, LocalDate zodiacDateEnd) {
        final String sql = "SELECT * FROM zodiac WHERE start_date = ? AND end_date = ?;";
        return jdbcTemplate.queryForObject(sql, new ZodiacRowMapper(), zodiacDateStart, zodiacDateEnd);
    }


    @Override
    public Zodiac findByDate(LocalDate date) {
        final String sql = "SELECT * FROM zodiac WHERE ? BETWEEN start_date AND end_date;";
        return jdbcTemplate.queryForObject(sql, new ZodiacRowMapper(), date);
    }
}

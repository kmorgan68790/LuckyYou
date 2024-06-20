package learn.luckyyou.data;

import learn.luckyyou.models.Zodiac;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
@Repository
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
            zodiac.setZodiacStart(rs.getDate("start_dates").toLocalDate());
            zodiac.setZodiacEnd(rs.getDate("end_dates").toLocalDate());
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
        final String sql = "SELECT * FROM zodiac WHERE " +
                "(EXTRACT(MONTH FROM start_dates) < EXTRACT(MONTH FROM ?) OR " +
                "(EXTRACT(MONTH FROM start_dates) = EXTRACT(MONTH FROM ?) AND EXTRACT(DAY FROM start_dates) <= EXTRACT(DAY FROM ?))) " +
                "AND " +
                "(EXTRACT(MONTH FROM end_dates) > EXTRACT(MONTH FROM ?) OR " +
                "(EXTRACT(MONTH FROM end_dates) = EXTRACT(MONTH FROM ?) AND EXTRACT(DAY FROM end_dates) >= EXTRACT(DAY FROM ?)))";
//        return jdbcTemplate.queryForObject(sql, new ZodiacRowMapper(), zodiacDateStart, zodiacDateEnd);
        return jdbcTemplate.queryForObject(sql, new Object[]{zodiacDateStart, zodiacDateStart, zodiacDateStart,
                zodiacDateEnd, zodiacDateEnd, zodiacDateEnd}, (rs, rowNum) ->
                new Zodiac(
                        rs.getInt("zodiac_id"),
                        rs.getString("zodiac_name"),
                        rs.getString("zodiac_description"),
                        rs.getDate("start_dates").toLocalDate(),
                        rs.getDate("end_dates").toLocalDate()
                )
        );
    }


    @Override
    public Zodiac findByDate(LocalDate date) {
        final String sql = "SELECT * FROM zodiac WHERE (EXTRACT(MONTH FROM ?), EXTRACT(DAY FROM ?)) " +
                "BETWEEN (EXTRACT(MONTH FROM start_dates), EXTRACT(DAY FROM start_dates)) " +
                "AND (EXTRACT(MONTH FROM end_dates), EXTRACT(DAY FROM end_dates))";
//        return jdbcTemplate.queryForObject(sql, new ZodiacRowMapper(), date);
        return jdbcTemplate.queryForObject(sql, new Object[]{date, date}, (rs, rowNum) ->
                new Zodiac(
                        rs.getInt("zodiac_id"),
                        rs.getString("zodiac_name"),
                        rs.getString("zodiac_description"),
                        rs.getDate("start_dates").toLocalDate(),
                        rs.getDate("end_dates").toLocalDate()
                    )
                );
    }
}

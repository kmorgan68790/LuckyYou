package learn.luckyyou.data;

import learn.luckyyou.models.Numerology;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class NumerologyJdbcTemplateRepository implements NumerologyRepository{
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public NumerologyJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final class NumerologyRowMapper implements RowMapper<Numerology> {
        @Override
        public Numerology mapRow(ResultSet rs, int rowNum) throws SQLException {
            Numerology numerology = new Numerology();
            numerology.setNumerologyId(rs.getInt("numerology_id"));
            numerology.setLifePathNumber(rs.getInt("life_path_number"));
            numerology.setBirthdayNumber(rs.getInt("birthday_number"));
            numerology.setExpressionNumber(rs.getInt("expression_number"));
            numerology.setPersonalityNumber(rs.getInt("personality_number"));
            numerology.setSoulUrgeNumber(rs.getInt("soul_urge_number"));
            numerology.setLuckyNumberMonth(rs.getInt("lucky_number_month"));
            numerology.setLuckyNumberDay(rs.getInt("lucky_number_day"));
            numerology.setLuckyNumberYear(rs.getInt("lucky_number_year"));
            numerology.setNumerologyDescriptionId(rs.getInt("numerology_description_id"));
            return numerology;
        }
    }

    @Override
    public List<Numerology> findAll() {
        String sql = "SELECT * FROM numerology;";
        return jdbcTemplate.query(sql, new NumerologyRowMapper());
    }

    @Override
    public Numerology findById(int numerologyId) {
        String sql = "SELECT * FROM numerology WHERE numerology_id = ?;";
        return jdbcTemplate.query(sql, new NumerologyRowMapper(), numerologyId).stream()
                .findFirst().orElse(null);
    }

    @Override
    public Numerology findByLifePathNumber(int lifePathNumber) {
        String sql = "SELECT life_path_number FROM numerology WHERE numerology_id = ?;";
        return jdbcTemplate.query(sql, new NumerologyRowMapper(), lifePathNumber).stream()
                .findFirst().orElse(null);
    }

    @Override
    public Numerology findByExpressionNumber(int expressionNumber) {
        String sql = "SELECT expression_number FROM numerology WHERE numerology_id = ?;";
        return jdbcTemplate.query(sql, new NumerologyRowMapper(), expressionNumber).stream()
                .findFirst().orElse(null);
    }

    @Override
    public Numerology findByPersonalityNumber(int personalityNumber) {
        String sql = "SELECT personality_number FROM numerology WHERE numerology_id = ?;";
        return jdbcTemplate.query(sql, new NumerologyRowMapper(), personalityNumber).stream()
                .findFirst().orElse(null);
    }

    @Override
    public Numerology findBySoulUrgeNumber(int soulUrgeNumber) {
        String sql = "SELECT soul_urge_number FROM numerology WHERE numerology_id = ?;";
        return jdbcTemplate.query(sql, new NumerologyRowMapper(), soulUrgeNumber).stream()
                .findFirst().orElse(null);
    }

    @Override
    public Numerology findByBirthdayNumber(int birthdayNumber) {
        String sql = "SELECT birthday_number FROM numerology WHERE numerology_id = ?;";
        return jdbcTemplate.query(sql, new NumerologyRowMapper(), birthdayNumber).stream()
                .findFirst().orElse(null);
    }

    @Override
    public List<Numerology> findByLuckyNumber(int month, int day, int year) {
        String sql = "SELECT lucky_number_month, lucky_number_day,lucky_number_year FROM numerology " +
                "WHERE numerology_id = ?;";
        return jdbcTemplate.query(sql, new NumerologyRowMapper(), month,day,year);
    }
}

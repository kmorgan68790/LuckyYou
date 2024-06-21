package learn.luckyyou.data;

import learn.luckyyou.models.NumerologyDescription;
import learn.luckyyou.models.UserNumerologyMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class NumerologyDescriptionJdbcTemplateRepository implements NumerologyDescriptionRepository{
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public NumerologyDescriptionJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final class NumerologyDescriptionRowMapper implements RowMapper<NumerologyDescription> {
        @Override
        public NumerologyDescription mapRow(ResultSet rs, int rowNum) throws SQLException {
            NumerologyDescription numerologyDescription = new NumerologyDescription();
            numerologyDescription.setNumerologyDescriptionId(rs.getInt("numerology_description_id"));
            numerologyDescription.setNumerologyType(rs.getString("numerology_type"));
            numerologyDescription.setNumerologyDescription(rs.getString("numerology_description"));
            numerologyDescription.setNumerologyNumber(rs.getInt("numerology_number"));
            return numerologyDescription;
        }
    }

    @Override
    public List<NumerologyDescription> findAll() {
        String sql = "SELECT * FROM numerology_description;";
        return jdbcTemplate.query(sql, new NumerologyDescriptionJdbcTemplateRepository.NumerologyDescriptionRowMapper());
    }

    @Override
    public NumerologyDescription findById(int numerologyDescriptionId) {
        String sql = "SELECT * FROM numerology_description WHERE numerology_description_id = ?;";
        return jdbcTemplate.query(sql, new NumerologyDescriptionRowMapper(), numerologyDescriptionId).stream()
                .findFirst().orElse(null);
    }

    @Override
    public NumerologyDescription findByNumerologyType(String numerologyType) {
        String sql = "SELECT * FROM numerology_description WHERE numerology_type = ?;";
        return jdbcTemplate.query(sql, new NumerologyDescriptionRowMapper(), numerologyType).stream()
                .findFirst().orElse(null);
    }

    @Override
    public NumerologyDescription findByNumerologyDescription(String numerologyDescription) {
        String sql = "SELECT * FROM numerology_description WHERE numerology_description = ?;";
        return jdbcTemplate.query(sql, new NumerologyDescriptionRowMapper(), numerologyDescription).stream()
                .findFirst().orElse(null);
    }

    @Override
    public NumerologyDescription findByNumerologyNumber(int numerologyNumber) {
        String sql = "SELECT * FROM numerology_description WHERE numerology_number = ?;";
        return jdbcTemplate.query(sql, new NumerologyDescriptionRowMapper(), numerologyNumber).stream()
                .findFirst().orElse(null);
    }
}

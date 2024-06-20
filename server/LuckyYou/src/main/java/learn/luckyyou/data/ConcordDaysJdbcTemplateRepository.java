package learn.luckyyou.data;

import learn.luckyyou.models.ConcordDays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ConcordDaysJdbcTemplateRepository implements ConcordDaysRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ConcordDaysJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final class ConcordDayRowMapper implements RowMapper<ConcordDays> {
        @Override
        public ConcordDays mapRow(ResultSet rs, int rowNum) throws SQLException {
            ConcordDays concordDay = new ConcordDays();
            concordDay.setConcordDaysId(rs.getInt("concord_days_id"));
            concordDay.setDayType(rs.getString("day_type"));
            concordDay.setConcordDayNumber(rs.getInt("concord_day_number"));
            concordDay.setConcordGroupId(rs.getInt("concord_group_id"));
            return concordDay;
        }
    }

    @Override
    public ConcordDays findById(int concordDaysId) {
        String sql = "SELECT * FROM concord_days WHERE concord_days_id = ?;";
        return jdbcTemplate.query(sql, new ConcordDayRowMapper(), concordDaysId).stream()
                .findFirst().orElse(null);
    }

    @Override
    public List<ConcordDays> findByConcordGroupId(int concordGroupId) {
        String sql = "SELECT * FROM concord_days WHERE concord_group_id = ?;";
        return jdbcTemplate.query(sql, new ConcordDayRowMapper(), concordGroupId);
    }

    @Override
    public List<ConcordDays> findByDayTypeAndGroupId(String dayType, int concordGroupId) {
        String sql = "SELECT * FROM concord_days WHERE day_type = ? AND concord_group_id = ?;";
        return jdbcTemplate.query(sql, new ConcordDayRowMapper(), dayType, concordGroupId);
    }

    //    @Override
//    public ConcordDays add(ConcordDays concordDays) {
//        String sql = "INSERT INTO concord_days (day_type, concord_day_number, concord_group_id) VALUES (?, ?, ?);";
//        jdbcTemplate.update(sql,
//                concordDays.getDayType(),
//                concordDays.getConcordDayNumber(),
//                concordDays.getConcordGroupId());
//        return concordDays;
//    }
//
//    @Override
//    public boolean update(ConcordDays concordDays) {
//        String sql = "UPDATE concord_days SET day_type = ?, concord_day_number = ?, concord_group_id = ? " +
//                "WHERE concord_days_id = ?;";
//        return jdbcTemplate.update(sql,
//                concordDays.getDayType(),
//                concordDays.getConcordDayNumber(),
//                concordDays.getConcordGroupId(),
//                concordDays.getConcordDaysId()) > 0;
//    }
}

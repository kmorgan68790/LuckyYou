package learn.luckyyou.data;

import learn.luckyyou.models.ConcordGroup;
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
public class ConcordGroupJdbcTemplateRepository implements ConcordGroupRepository{
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ConcordGroupJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final class ConcordGroupRowMapper implements RowMapper<ConcordGroup> {
        @Override
        public ConcordGroup mapRow(ResultSet rs, int rowNum) throws SQLException {
            ConcordGroup concordGroup = new ConcordGroup();
            concordGroup.setConcordGroupId(rs.getInt("concord_group_id"));
            concordGroup.setConcordGroupNumber(rs.getInt("concord_group_number"));
            concordGroup.setConcordGroupDescription(rs.getString("concord_group_description"));
            return concordGroup;
        }
    }

//    @Override
//    public ConcordGroup add(ConcordGroup concordGroup) {
//        String sql = "INSERT INTO concord_group (concord_group_number, concord_group_description) VALUES (?, ?);";
//
//            KeyHolder keyHolder = new GeneratedKeyHolder();
//            int rowsAffected = jdbcTemplate.update(connection -> {
//                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//                ps.setInt(1, concordGroup.getConcordGroupId());
//                ps.setInt(2, concordGroup.getConcordGroupNumber());
//                ps.setString(3, concordGroup.getConcordGroupDescription());
//                return ps;
//
//        }, keyHolder);
//
//        if (rowsAffected <= 0) {
//            return null;
//        }
//
//        concordGroup.setConcordGroupId(keyHolder.getKey().intValue());
//        return concordGroup;
//    }
//
//    @Override
//    public Boolean update(ConcordGroup concordGroup) {
//        String sql = "UPDATE concord_group SET concord_group_number = ?, concord_group_description = ? " +
//                "WHERE concord_group_id = ?;";
//        return jdbcTemplate.update(sql, concordGroup.getConcordGroupNumber(), concordGroup.getConcordGroupDescription(),
//                concordGroup.getConcordGroupId()) > 0;
//    }
//
//    @Override
//    public Boolean deleteById(int concordGroupId) {
//        String sql = "DELETE FROM concord_group WHERE concord_group_id = ?;";
//        return jdbcTemplate.update(sql, concordGroupId) > 0;
//    }

    @Override
    public ConcordGroup findById(int concordGroupId) {
        String sql = "SELECT * FROM concord_group WHERE concord_group_id = ?;";
        return jdbcTemplate.query(sql, new ConcordGroupRowMapper(), concordGroupId).stream()
                .findFirst().orElse(null);
    }

    @Override
    public List<ConcordGroup> findAll() {
        String sql = "SELECT * FROM concord_group;";
        return jdbcTemplate.query(sql, new ConcordGroupRowMapper());
    }

}

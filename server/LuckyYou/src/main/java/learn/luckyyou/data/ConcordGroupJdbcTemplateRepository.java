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

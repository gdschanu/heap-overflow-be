package hanu.gdsc.domains.exercise;

import hanu.gdsc.domains.ActiveRecord;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class MemoryLimits extends ActiveRecord {
    private List<MemoryLimit> data;

    public MemoryLimits(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    protected String makeInsertQuery() {
        return null;
    }

    @Override
    protected void prepareInsertStmt(PreparedStatement stmt) throws SQLException {

    }

    @Override
    protected String makeGetByIdQuery() {
        return null;
    }
}

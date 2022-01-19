package hanu.gdsc.domains.exercise;

import hanu.gdsc.domains.ActiveRecord;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class MemoryLimits extends ActiveRecord {
    private List<MemoryLimit> data;

    @Override
    protected String makeInsertQuery() {
        return null;
    }

    @Override
    protected void prepareInsertStmt(PreparedStatement stmt) throws SQLException {

    }
}

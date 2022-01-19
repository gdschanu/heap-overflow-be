package hanu.gdsc.domains.exercise;

import hanu.gdsc.domains.ActiveRecord;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;

public class TestCases extends ActiveRecord {
    private List<TestCase> data;

    public TestCases(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    public void sortTestCasesByOrdinal() {
        data.sort(Comparator.comparingInt(x -> x.getOrdinal()));
    }

    public List<TestCase> getData() {
        return data;
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

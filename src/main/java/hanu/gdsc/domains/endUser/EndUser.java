package hanu.gdsc.domains.endUser;

import hanu.gdsc.domains.ActiveRecord;
import hanu.gdsc.domains.Email;
import hanu.gdsc.domains.Password;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EndUser extends ActiveRecord {
    private int id;
    private Email email;
    private String username;
    private Password password;

    public EndUser(JdbcTemplate jdbcTemplate) {
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

package hanu.gdsc.domains;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class DomainObject {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /*
    Save a domain object to DB and return its ID
     */
    public int insert() {
        KeyHolder kh = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement stmt = con.prepareStatement(makeInsertQuery(), Statement.RETURN_GENERATED_KEYS);
                prepareInsertStmt(stmt);
                return stmt;
            }
        });
        return kh.getKey().intValue();
    }

    protected abstract String makeInsertQuery();

    protected abstract void prepareInsertStmt(PreparedStatement stmt) throws SQLException;
}

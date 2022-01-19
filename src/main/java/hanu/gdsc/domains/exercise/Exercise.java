package hanu.gdsc.domains.exercise;

import hanu.gdsc.domains.ActiveRecord;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Entity
public class Exercise extends ActiveRecord {
    @Id
    private int id;
    private String name;
    private String description;
    private int author;
    private int ACsCount;
    private int submissionsCount;
    private ExerciseDifficulty difficulty;


    @Override
    protected String makeInsertQuery() {
        return "INSERT INTO hanuoj.exercise " +
                "(name," +
                "description," +
                "author," +
                "ACsCount," +
                "submissionsCount," +
                "difficulty) VALUES (?,?,?,?,?)";
    }

    @Override
    protected void prepareInsertStmt(PreparedStatement stmt) throws SQLException {
        stmt.setString(1, name);
        stmt.setString(2, description);
        stmt.setInt(3, author);
        stmt.setInt(4, ACsCount);
        stmt.setInt(5, submissionsCount);
        stmt.setString(6, difficulty.toString());
    }
}

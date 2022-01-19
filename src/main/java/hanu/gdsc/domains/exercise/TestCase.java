package hanu.gdsc.domains.exercise;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TestCase {
    @Id
    private int id;
    private int exerciseId;
    private String input;
    private String output;
    private int ordinal;
    private boolean isSample;
    private String description;
}

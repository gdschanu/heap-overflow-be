package hanu.gdsc.domains.exercise;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MemoryLimit {
    @Id
    public int id;
    public int exerciseId;
    public ProgrammingLanguage language;
    public int memoryLimitInKB;
}

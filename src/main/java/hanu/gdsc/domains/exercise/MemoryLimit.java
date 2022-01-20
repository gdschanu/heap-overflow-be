package hanu.gdsc.domains.exercise;

import hanu.gdsc.domains.ID;
import hanu.gdsc.domains.KB;

public class MemoryLimit {
    private ID id;
    private ProgrammingLanguage programmingLanguage;
    private KB memoryLimit;

    public ID getId() {
        return id;
    }

    public ProgrammingLanguage getProgrammingLanguage() {
        return programmingLanguage;
    }

    public KB getMemoryLimit() {
        return memoryLimit;
    }
}

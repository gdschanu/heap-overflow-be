package hanu.gdsc.coreProblem.domains;

public class MemoryLimit {
    private ProgrammingLanguage programmingLanguage;
    private KB memoryLimit;

    public MemoryLimit(ProgrammingLanguage programmingLanguage, KB memoryLimit) {
        this.programmingLanguage = programmingLanguage;
        this.memoryLimit = memoryLimit;
    }

    public ProgrammingLanguage getProgrammingLanguage() {
        return programmingLanguage;
    }

    public KB getMemoryLimit() {
        return memoryLimit;
    }

    public void setProgrammingLanguage(ProgrammingLanguage programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
    }

    public void setMemoryLimit(KB memoryLimit) {
        this.memoryLimit = memoryLimit;
    }
}

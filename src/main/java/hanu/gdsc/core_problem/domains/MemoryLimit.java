package hanu.gdsc.core_problem.domains;


import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.domains.IdentifiedDomainObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

public class MemoryLimit extends IdentifiedDomainObject {
    private ProgrammingLanguage programmingLanguage;
    private KB memoryLimit;

    private MemoryLimit(Id id, ProgrammingLanguage programmingLanguage, KB memoryLimit) {
        super(id);
        this.programmingLanguage = programmingLanguage;
        this.memoryLimit = memoryLimit;
    }

    @Getter
    @AllArgsConstructor
    public static class CreateInput {
        public ProgrammingLanguage programmingLanguage;
        public KB memoryLimit;
    }

    public static MemoryLimit create(CreateInput input) {
        return new MemoryLimit(
                Id.generateRandom(),
                input.programmingLanguage,
                input.memoryLimit
        );
    }

    public ProgrammingLanguage getProgrammingLanguage() {
        return programmingLanguage;
    }

    public KB getMemoryLimit() {
        return memoryLimit;
    }

    public void setMemoryLimit(KB memoryLimit) {
        this.memoryLimit = memoryLimit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemoryLimit that = (MemoryLimit) o;
        return programmingLanguage == that.programmingLanguage;
    }

    @Override
    public int hashCode() {
        return Objects.hash(programmingLanguage);
    }
}

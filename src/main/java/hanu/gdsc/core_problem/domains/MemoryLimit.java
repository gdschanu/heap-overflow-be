package hanu.gdsc.core_problem.domains;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.domains.IdentifiedDomainObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

public class MemoryLimit {
    private ProgrammingLanguage programmingLanguage;
    private KB memoryLimit;

    private MemoryLimit(ProgrammingLanguage programmingLanguage, KB memoryLimit) {
        this.programmingLanguage = programmingLanguage;
        this.memoryLimit = memoryLimit;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Schema(title = "create")
    public static class CreateInputML {
        @Schema(description = "", example = "JAVA")
        public ProgrammingLanguage programmingLanguage;
        @Schema(description = "", example = "1000")
        public KB memoryLimit;
    }

    public static MemoryLimit create(CreateInputML input) {
        return new MemoryLimit(
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

    @Override
    public String toString() {
        return memoryLimit.toString();
    }
}

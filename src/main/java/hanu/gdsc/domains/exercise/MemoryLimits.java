package hanu.gdsc.domains.exercise;

import java.util.List;

public class MemoryLimits {
    private List<MemoryLimit> data;

    MemoryLimit getMemoryLimitByLanguage(ProgrammingLanguage language) {
        for (MemoryLimit memoryLimit : data) {
            if (memoryLimit.getLanguage().equals(language))
                return memoryLimit;
        }
        return null;
    }
}

package hanu.gdsc.domains.exercise;

import java.util.List;

public class TimeLimits {
    private List<TimeLimit> data;

    TimeLimit getMemoryLimitByLanguage(ProgrammingLanguage language) {
        for (TimeLimit timeLim : data) {
            if (timeLim.getLanguage().equals(language))
                return timeLim;
        }
        return null;
    }
}

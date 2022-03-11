package hanu.gdsc.practiceProblem.services.practiceProblem;

import hanu.gdsc.share.domains.Id;
import lombok.Builder;

public interface SearchPracticeProblemService {
    @Builder
    public static class Output {
        public hanu.gdsc.practiceProblem.domains.Problem practiceProblem;
        public hanu.gdsc.coreProblem.domains.Problem coreProblem;
    }

    public Output getById(Id practiceProblemId);
}

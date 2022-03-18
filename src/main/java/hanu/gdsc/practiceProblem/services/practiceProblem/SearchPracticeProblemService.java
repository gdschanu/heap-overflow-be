package hanu.gdsc.practiceProblem.services.practiceProblem;

import hanu.gdsc.coreProblem.services.problem.SearchProblemService.OutputCoreProblem;
import hanu.gdsc.share.domains.Id;
import lombok.Builder;

public interface SearchPracticeProblemService {
    @Builder
    public static class OutputPraticeProblem {
        public String id;
        public long version;
        public String coreProlemId;
        public long likeCount;
        public long dislikeCount;
        public String category;
    }
    @Builder
    public static class Output {
        public OutputPraticeProblem practiceProblem;
        public OutputCoreProblem coreProblem;
    }

    public Output getById(Id practiceProblemId);
}

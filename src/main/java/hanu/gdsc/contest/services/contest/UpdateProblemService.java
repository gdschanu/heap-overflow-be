package hanu.gdsc.contest.services.contest;

import hanu.gdsc.share.domains.Id;
import lombok.Builder;

public interface UpdateProblemService {
    @Builder
    public static class AddProblemInput {
        public Id contestId;
        public int ordinal;
        public int score;
        public Id coreProblemId;
    }
    public void addProblem(AddProblemInput input);

    public void removeProblem(Id contestId, Id coreProblemId);
}

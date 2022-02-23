package hanu.gdsc.contest.services.contestProblem;

import hanu.gdsc.problem.domains.ID;

import java.util.List;

public interface GetContestProblemService {
    public static class ContestProblemDTO {
        public ID id;
        public int ordinal;
        public ID problemId;
        public double score;
    }

    public List<ContestProblemDTO> getContestProblemsOfContests(List<ID> contests);
}

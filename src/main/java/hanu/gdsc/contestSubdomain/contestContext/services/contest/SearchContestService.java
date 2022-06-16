package hanu.gdsc.contestSubdomain.contestContext.services.contest;

import hanu.gdsc.share.domains.DateTime;
import hanu.gdsc.share.domains.Id;
import lombok.Builder;

import java.util.List;

public interface SearchContestService {

    @Builder
    public static class OutputProblem {
        public int ordinal;
        public Id coreProblemId;
        public int score;
    }

    @Builder
    public static class OutputContest {
        public Id id;
        public String name;
        public String description;
        public DateTime startAt;
        public DateTime endAt;
        public Id createdBy;
        public List<OutputProblem> problems;
        public long version;
    }

    public OutputContest getById(Id contestId);

    public List<OutputContest> get(int page, int perPage);
}

package hanu.gdsc.contest.services.contest;

import hanu.gdsc.problem.domains.ID;

import java.time.ZonedDateTime;
import java.util.List;

public interface GetContestService {
    public static enum VisiblityDTO {
        PUBLIC, PRIVATE
    }

    public static class ContestDTO {
        public ID id;
        public String name;
        public String description;
        public ZonedDateTime startAt;
        public ZonedDateTime endAt;
        public ID author;
        public VisiblityDTO visiblity;
    }

    public List<ContestDTO> getAllRunningContest();
}

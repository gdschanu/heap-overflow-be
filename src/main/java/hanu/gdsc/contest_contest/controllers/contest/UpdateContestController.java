package hanu.gdsc.contest_contest.controllers.contest;

import hanu.gdsc.contest_contest.services.contest.UpdateContestService;

import java.util.List;

public class UpdateContestController {
    public static class Input {
        public String name;
        public String description;
        public String startAt;
        public String endAt;
        public List<UpdateContestService.UpdateProblemInput> problems;
    }
}

package hanu.gdsc.contestSubdomain.contestContext.services.contest;

import hanu.gdsc.share.domains.DateTime;
import hanu.gdsc.share.domains.Id;
import lombok.Builder;

public interface UpdateContestService {
    @Builder
    public static class Input {
        public Id contestId;
        public String name;
        public String description;
        public DateTime startAt;
        public DateTime endAt;
    }

    public void execute(Input input);
}

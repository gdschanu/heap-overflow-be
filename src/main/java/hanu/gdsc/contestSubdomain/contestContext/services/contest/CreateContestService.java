package hanu.gdsc.contestSubdomain.contestContext.services.contest;

import hanu.gdsc.share.domains.DateTime;
import hanu.gdsc.share.domains.Id;
import lombok.Builder;

public interface CreateContestService {
    @Builder
    public static class Input {
        public String name;
        public String description;
        public DateTime startAt;
        public DateTime endAt;
        public Id createdBy;
    }

    public Id create(Input input);
}

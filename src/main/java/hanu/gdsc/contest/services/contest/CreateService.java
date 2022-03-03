package hanu.gdsc.contest.services.contest;

import hanu.gdsc.share.domains.DateTime;
import hanu.gdsc.share.domains.Id;
import lombok.Builder;

import java.util.Date;

public interface CreateService {
    @Builder
    public static class Input {
        public String name;
        public String description;
        public DateTime startAt;
        public DateTime endAt;
        public Id author;
    }

    public Id create(Input input);
}

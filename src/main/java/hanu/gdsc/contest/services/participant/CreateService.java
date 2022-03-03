package hanu.gdsc.contest.services.participant;

import hanu.gdsc.share.domains.Id;
import lombok.Builder;

public interface CreateService {
    @Builder
    public static class Input {
        public Id coderId;
        public Id contestId;
    }

    public void execute(Input input);
}

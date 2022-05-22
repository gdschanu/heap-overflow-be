package hanu.gdsc.coreProblem.services.testCasePing;

import hanu.gdsc.share.domains.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

public interface TestCasePingService {
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Payload {
        public int currentTestCase;
        public int totalTestCase;
        public Id submissionId;
    }
    public void ping(Id coderId, Payload payload);
}

package hanu.gdsc.practiceProblem_problemDiscussion.services.core_comment;

import hanu.gdsc.share.domains.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;

public interface CreateCoreCommentService {
    @AllArgsConstructor
    @Getter
    public static class Input {
        private Id author;
        private String content;
        private Id parentCommentId;
    }

    public Id create(Input input);
}

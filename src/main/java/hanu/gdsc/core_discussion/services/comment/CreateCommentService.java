package hanu.gdsc.core_discussion.services.comment;

import hanu.gdsc.share.domains.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;

public interface CreateCommentService {
    @AllArgsConstructor
    @Getter
    public static class Input {
        private Id author;
        private String content;
        private Id parentCommentId;
        private String serviceToCreate;
        private Id postId;
    }
    public Id create(Input input);

}

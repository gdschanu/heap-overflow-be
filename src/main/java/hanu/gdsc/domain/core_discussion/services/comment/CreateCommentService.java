package hanu.gdsc.domain.core_discussion.services.comment;

import hanu.gdsc.domain.share.models.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

public interface CreateCommentService {
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Input {
        public Id author;
        public String content;
        public Id parentCommentId;
        public String serviceToCreate;
        public Id postId;
    }
    public Id create(Input input);

}

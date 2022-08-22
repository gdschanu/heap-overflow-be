package hanu.gdsc.core_discussion.services.comment;

import hanu.gdsc.share.domains.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
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

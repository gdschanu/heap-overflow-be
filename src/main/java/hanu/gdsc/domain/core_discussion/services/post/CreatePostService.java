package hanu.gdsc.domain.core_discussion.services.post;

import hanu.gdsc.domain.share.models.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

public interface CreatePostService {
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Input {
        public String title;
        public Id author;
        public String content;
        public String serviceToCreate;
    }

    public Id execute(Input input);
}

package hanu.gdsc.core_discussion.services.post;

import hanu.gdsc.share.domains.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
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

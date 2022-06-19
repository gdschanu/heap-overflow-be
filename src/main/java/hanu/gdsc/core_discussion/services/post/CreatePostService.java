package hanu.gdsc.core_discussion.services.post;

import hanu.gdsc.share.domains.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;

public interface CreatePostService {
    @AllArgsConstructor
    @Getter
    public static class Input {
        private String title;
        private Id author;
        private String content;
        private String serviceToCreate;
    }

    public Id execute(Input input);
}

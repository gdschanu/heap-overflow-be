package hanu.gdsc.practiceProblem_problemDiscussion.services.core_post;

import hanu.gdsc.share.domains.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;

public interface CreateCorePostService {
    @Getter
    @AllArgsConstructor
    public static class Input {
        private String title;
        private Id author;
        private String content;
    }

    public Id create(Input input);
}

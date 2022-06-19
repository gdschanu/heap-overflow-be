package hanu.gdsc.practiceProblem_problemDiscussion.services.core_post;

import hanu.gdsc.share.domains.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;

public interface UpdateCorePostService {
    @AllArgsConstructor
    @Getter
    public static class Input {
        private Id id;
        private String title;
        private String content;
    }

    public void execute(Input input);
}
